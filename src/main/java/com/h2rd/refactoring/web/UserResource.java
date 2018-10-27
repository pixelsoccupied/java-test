package com.h2rd.refactoring.web;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Path("/users")
@Repository
public class UserResource{

    private UserDao userDao;

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

        userDao.saveUser(user);

        System.out.println("User total #" + userDao.getUsers().size());
        return Response.ok().entity(user).build();
    }

    @GET
    @Path("update/")
    public Response updateUser(@QueryParam("name") String name,
                               @QueryParam("email") String email,
                               @QueryParam("role") List<String> roles) {

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setRoles(roles);

        if (userDao == null) {
            userDao = UserDao.getUserDao();
        }

        userDao.updateUser(user);
        return Response.ok().entity(user).build();
    }

    @GET
    @Path("delete/")
    public Response deleteUser(@QueryParam("name") String name,
                               @QueryParam("email") String email,
                               @QueryParam("role") List<String> roles) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setRoles(roles);

        if (userDao == null) {
            userDao = UserDao.getUserDao();
        }

        userDao.deleteUser(email);
        return Response.ok().entity(user).build();
    }

    @GET
    @Path("find/")
    public Response getUsers() {

        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
    		"classpath:/application-config.xml"
    	});

    	userDao = context.getBean(UserDao.class);

    	ConcurrentHashMap<String, User> users = userDao.getUsers();
    	if (users == null) {
    		users = new ConcurrentHashMap<String, User>();
        }
        try {
            GenericEntity<ConcurrentHashMap<String, User>> usersEntity =
                    new GenericEntity<ConcurrentHashMap<String, User>>(users) {};
            return Response.ok().entity(usersEntity).build();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Something wrong").build();
    }

    @GET
    @Path("search/")
    public Response findUser(@QueryParam("name") String name) {

        if (userDao == null) {
            userDao = UserDao.getUserDao();
        }

        User user = userDao.findUser(name);
        return Response.ok().entity(user).build();
    }
}
