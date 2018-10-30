package com.h2rd.refactoring.usermanagement;

import java.util.HashMap;

public class UserDao {

    //O(1) add/delete/search
    private static HashMap<String, User> users;

    private static UserDao userDao;

    private UserDao() {

    }

    public synchronized static UserDao getUserDao() {
        if (userDao == null) {
                userDao = new UserDao();

        }
        return userDao;
    }


    public synchronized int saveUser(User user) {
        if (users == null) {
            users = new HashMap<String, User>();
        }
        if (user.getRoles() == null || user.getRoles().isEmpty()){
            return 1;
        }
        if (user.getEmail() == null){
            return 2;
        }
        if (user.getName() == null){
            return 3;
        }
        if (!users.containsKey(user.getEmail())) {
            users.put(user.getEmail(), user);
            return 0;

        }
        return 4;
    }

    public HashMap<String, User> getUsers() {
        try {
            return users;
        } catch (Throwable e) {
            System.out.println("error");
            return null;
        }
    }

    public User deleteUser(String email) {
        if (users.containsKey(email)) {
            return users.remove(email);
        }
        else
            return null;
    }

    public synchronized int updateUser(User userToUpdate) {
        //check if email exists
        if (!users.containsKey(userToUpdate.getEmail())){
            return -1;
        }
        // no change in email
        else {
            if (userToUpdate.getName() != null && userToUpdate.getName().length()!= 0) {
                users.get(userToUpdate.getEmail()).setName(userToUpdate.getName());
            }
            if (!userToUpdate.getRoles().isEmpty()) {
                users.get(userToUpdate.getEmail()).setRoles(userToUpdate.getRoles());
            }
            return 0;
        }
    }

    public User findUser(String email) {
        if (users.containsKey(email)){
            return users.get(email);
        }
        return null;
    }
}
