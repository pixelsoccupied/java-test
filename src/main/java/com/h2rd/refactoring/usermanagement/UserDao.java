package com.h2rd.refactoring.usermanagement;

import java.util.concurrent.ConcurrentHashMap;

public class UserDao {

    //Thread safe and O(1) time
    private static ConcurrentHashMap <String, User> users;
    private static UserDao userDao;

    public static UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDao();

        }
        return userDao;
    }


    public void saveUser(User user) {
        if (users == null) {
            users = new ConcurrentHashMap<String, User>();
        }
        users.put(user.getEmail(), user);
    }

    public ConcurrentHashMap<String, User> getUsers() {
        try {
            return users;
        } catch (Throwable e) {
            System.out.println("error");
            return null;
        }
    }

    public int deleteUser(String email) {
        if (users.containsKey(email)) {
            users.remove(email);
            return 0;
        }
        else
            return 1;
    }

    public int updateUser(User userToUpdate) {
        // no change in email
        if (users.get(userToUpdate.getEmail()).getEmail().equals(userToUpdate.getEmail())) {
            users.get(userToUpdate.getEmail()).setName(userToUpdate.getName());
            users.get(userToUpdate.getEmail()).setRoles(userToUpdate.getRoles());
            return 0;
        }
        else
            return 1;
    }

    public User findUser(String email) {
        if (users.containsKey(email)){
            return users.get(email);
        }
        return null;
    }

    //for debug
    public void init_method(){
        System.out.println("Init bean");
    }
}
