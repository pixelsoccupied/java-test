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
        ArrayList<String> roles = new ArrayList<String>();
        roles.add("Developer");
        user.setRoles(roles);
        System.out.println("Here");
        userDao.saveUser(user);

        //Missing role should fail

        Response response = userResource.getUsers();
        Assert.assertEquals(200, response.getStatus());
    }
}
