package com.h2rd.refactoring.web;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;


@Path("/users")
@Repository
public class UserResource{

    private UserDao userDao;
    private ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
            "classpath:/application-config.xml"
    });


    @GET
    @Path("add/")
    public Response addUser(@QueryParam("name") String name,
                            @QueryParam("email") String email,
                            @QueryParam("role") List<String> roles) {

         User user = new User();
         user.setName(name);
         user.setEmail(email);
         user.setRoles(roles);

         //System.out.println(userDao.getI());

        if (userDao == null) {
            userDao = UserDao.getUserDao();
        }
        int res = userDao.saveUser(user);


        if (res == 0){
            return Response.ok().entity(user).build();
        }
        if (res == 1) {
            return Response.status(404).entity("Missing roles").build();
        }
        if (res == 2) {
            return Response.status(404).entity("Missing email").build();
        }
        if (res == 3){
            return Response.status(404).entity("Missing name").build();
        }
        else
        {
            return Response.status(404).entity(user).entity("This email already exists!").build();
        }
    }

    @GET
    @Path("update/")
    public Response updateUser(@QueryParam("name") String name,
                               @QueryParam("email") String email,
                               @QueryParam("role") List<String> roles) {
        if (email == null || email.length() == 0 || !Pattern.matches("(.+)@(.+)$", email)){
            return Response.status(404).entity("Need email to locate your profile").build();
        }
        if (name == null && roles.isEmpty()){
            return Response.status(404).entity("Provide name--role to update the profile").build();
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setRoles(roles);

        if (userDao == null) {
            userDao = UserDao.getUserDao();
        }

        //check if the update was a success
        if (userDao.updateUser(user) == 0){
            return Response.ok().entity(user).build();
        }
        else
        {
            return Response.status(404).entity(user).entity("Email doesn't exist").build();
        }

    }

    @GET
    @Path("delete/")
    public Response deleteUser(@QueryParam("email") String email) {
        if (email == null){
            return Response.status(404).entity("Need email to locate your profile").build();
        }
        if (userDao == null) {
            userDao = UserDao.getUserDao();
        }
        User user = userDao.deleteUser(email);
        if (user != null){
            return Response.ok().entity(user).build();
        }
        else
            return Response.status(404).entity("Couldn't find the email").build();

    }

    @GET
    @Path("find/")
    public Response getUsers() {
    	userDao = context.getBean(UserDao.class);
    	HashMap<String, User> users = userDao.getUsers();
    	if (users == null) {
    		users = new HashMap<String, User>();
        }
        GenericEntity<HashMap<String, User>> usersEntity =
                    new GenericEntity<HashMap<String, User>>(users) {};
    	if (usersEntity.getEntity().isEmpty()){
    	    return Response.status(404).entity("List is empty").build();
    	}

    	return Response.ok().entity(usersEntity).build();
    }

    @GET
    @Path("search/")
    public Response findUser(@QueryParam("email") String email) {
        if (userDao == null) {
            userDao = UserDao.getUserDao();
        }
        User user = userDao.findUser(email);
        if (user != null){
            return Response.ok().entity(user).build();
        }
        else
            return Response.status(Response.Status.NOT_FOUND).entity("Could not find user").build();
    }
}
