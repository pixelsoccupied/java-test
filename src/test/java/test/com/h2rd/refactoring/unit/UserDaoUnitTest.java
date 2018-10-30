package test.com.h2rd.refactoring.unit;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class UserDaoUnitTest {

    private UserDao userDao;

    @Test
    public void saveUserTest() {
        System.out.println("INIT");

        userDao = UserDao.getUserDao();


        User user = new User();
        user.setName("Fake Name");
        user.setEmail("fake@email.com");
        user.setRoles(Arrays.asList("admin", "master"));
        int res = userDao.saveUser(user);

        System.out.println(userDao);

        Assert.assertEquals(Integer.valueOf(0),Integer.valueOf(res));
    }

    @Test
    public void deleteUserTest() {
        System.out.println("INIT");

        userDao = UserDao.getUserDao();

        User user = new User();
        user.setName("Fake Name");
        user.setEmail("fake@email.com");
        user.setRoles(Arrays.asList("admin", "master"));

        Assert.assertEquals(user.getEmail(), userDao.deleteUser(user.getEmail()).getEmail());

    }
}