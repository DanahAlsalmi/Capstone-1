package com.example.capstone1.Service;

import com.example.capstone1.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    //Array for Users
    ArrayList<User> users = new ArrayList<>();

    //Get users
    public ArrayList<User> getUsers() {
        return users;
    }

    //Add User
    public void addUser(User user) {
        users.add(user);
    }

    //Update user
    public boolean updateUser(int id ,User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.set(i, user);
                return true;
            }
        }
        return false;
    }

    //Delete user
    public boolean deleteUser(int id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.remove(i);
                return true;
            }
        }
        return false;
    }

}
