package test.com.h2rd.refactoring.integration;

import java.util.ArrayList;

import javax.ws.rs.core.Response;

import junit.framework.Assert;

import org.junit.Test;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.web.UserResource;



public class UserIntegrationTest {
	
	@Test
	public void createUserTest() {
		UserResource userResource = new UserResource();
		
		User integration = new User();
        integration.setName("integration");
        integration.setEmail("initial@integrattion.com");
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("Dev");
        integration.setRoles(arrayList);

        //verify if the user exists first
        Response userExists = userResource.findUser(integration.getEmail());

        Response response = userResource.addUser(integration.getName(), integration.getEmail(), integration.getRoles());
        //check if the user exists


        //if 200 for is exits that means add response fails
        if (userExists.getStatus() == 200) Assert.assertEquals(404, response.getStatus());
        else Assert.assertEquals(200, response.getStatus());

	}

    @Test
    public void updateUserTest() {
        UserResource userResource = new UserResource();

        createUserTest();

        User updated = new User();
        updated.setName("integration");
        updated.setEmail("initial@integrattion.com");
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("Developer");
        updated.setRoles(arrayList);

        Response response = userResource.updateUser(updated.getName(), updated.getEmail(), updated.getRoles());

        Assert.assertEquals(200, response.getStatus());

    }


    @Test
    public void findUserTest() {
        UserResource userResource = new UserResource();

        createUserTest();

        User updated = new User();
        updated.setName("integration");
        updated.setEmail("initial@integrattion.com");
        updated.setRoles(new ArrayList<String>());

        Response response = userResource.findUser(updated.getEmail());

        Assert.assertEquals(200, response.getStatus());

    }

    @Test
    public void deleteUserTest() {
        UserResource userResource = new UserResource();

        createUserTest();

        User updated = new User();
        updated.setName("integration");
        updated.setEmail("initial@integrattion.com");
        updated.setRoles(new ArrayList<String>());

        Response response = userResource.deleteUser(updated.getEmail());

        Assert.assertEquals(200, response.getStatus());


        //try to delete a user that doesn't exist
        User updatedRetry = new User();
        updatedRetry.setName("integration");
        updatedRetry.setEmail("initial@integrattion.com");
        updatedRetry.setRoles(new ArrayList<String>());

        Response responseRetry = userResource.deleteUser(updated.getEmail());
        Assert.assertEquals(404, responseRetry.getStatus());
    }







}
