package test.com.h2rd.refactoring.unit;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;
import com.h2rd.refactoring.web.UserResource;
import junit.framework.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;

public class UserResourceUnitTest {

    private UserResource userResource;
    private UserDao userDao;

    @Test
    public void getUsersTest() {

        userResource = new UserResource();
        userDao = UserDao.getUserDao();

        User user = new User();
        user.setName("fake user");
        user.setEmail("fake@user.com");
        user.setRoles(Arrays.asList("admin", "master"));
        userDao.saveUser(user);

        Response response = userResource.getUsers();
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void addUsersTest() {

        userResource = new UserResource();
        userDao = UserDao.getUserDao();

        User user = new User();
        user.setName("fake user");
        user.setEmail("fake@user.com");
        user.setRoles(new ArrayList<String>());

        Response res = userResource.addUser(user.getName(),user.getEmail(),user.getRoles());
        Assert.assertEquals(404, res.getStatus());

        //correct user entry
        User userC = new User();
        userC.setName("fake user");
        userC.setEmail("fake@userr.com");
        userC.setRoles(Arrays.asList("admin", "master"));

        Response resC = userResource.addUser(userC.getName(),userC.getEmail(),userC.getRoles());
        Assert.assertEquals(200, resC.getStatus());

    }

    @Test
    public void deleteUsersTest() {

        userResource = new UserResource();
        userDao = UserDao.getUserDao();

        String email = "fake@userr.com";
        Response deletedUser = userResource.deleteUser(email);
        Assert.assertEquals(200, deletedUser.getStatus());

    }

    @Test
    public void updateUsersTest() {

        userResource = new UserResource();
        userDao = UserDao.getUserDao();

        User user = new User();
        user.setName("fake user");
        user.setEmail("fake@userrrr.com");
        user.setRoles(Arrays.asList("admin", "master"));
        userDao.saveUser(user);
        Response updateUser = userResource.updateUser("Real User", "fake@userrrr.com", Arrays.asList("admin", "master"));
        Assert.assertEquals(200, updateUser.getStatus());

    }





}
