package test.com.h2rd.refactoring.unit;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class UserDaoUnitTest {

    private UserDao userDao;


    @Test
    public void saveUserTest() {

        userDao = UserDao.getUserDao();


        User user = new User();
        user.setName("Fake Name");
        user.setEmail("fake@email.com");
        user.setRoles(Arrays.asList("admin", "master"));
        userDao.saveUser(user);

        Assert.assertEquals(user.getEmail(),userDao.getUsers().get(user.getEmail()).getEmail());



        //save without a role
        User user2 = new User();
        user2.setName("Fake Name");
        user2.setEmail("fake@email.com");
        user2.setRoles(new ArrayList<String>());
        int res2 = userDao.saveUser(user2);

        Assert.assertEquals(1,res2);



    }

    @Test
    public void deleteUserTest() {

        userDao = UserDao.getUserDao();

        User user = new User();
        user.setName("Fake Name");
        user.setEmail("fake@emaiel.com");
        user.setRoles(Arrays.asList("admin", "master"));
        userDao.saveUser(user);

        User del = new User();
        del.setName("Fake Name");
        del.setEmail("fake@emaiel.com");
        del.setRoles(Arrays.asList("admin", "master"));

        User u = userDao.deleteUser(del.getEmail());

        Assert.assertEquals(user.getEmail(), u.getEmail());

    }


    @Test
    public void updateUser() {
        userDao = UserDao.getUserDao();

        User user = new User();
        user.setName("Fake Name");
        user.setEmail("fake@email.com");
        user.setRoles(Arrays.asList("admin", "master"));
        userDao.saveUser(user);


        User update = new User();
        update.setName("Fake Name New");
        update.setEmail("fake@email.com");
        update.setRoles(Arrays.asList("admin", "master"));
        userDao.updateUser(update);


        Assert.assertEquals(update.getName(),userDao.getUsers().get(user.getEmail()).getName());
    }

    @Test
    public void findUser() {
        userDao = UserDao.getUserDao();

        User user = new User();
        user.setName("Fake Name New");
        user.setEmail("fake@emailll.com");
        user.setRoles(Arrays.asList("admin", "master"));

        userDao.saveUser(user);

        User found = userDao.findUser(user.getEmail());


        Assert.assertEquals(user.getName(),found.getName());




    }




}