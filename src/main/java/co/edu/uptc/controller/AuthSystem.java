package co.edu.uptc.controller;

import co.edu.uptc.model.User;
import co.edu.uptc.persistence.JSONPersistence;

import java.util.List;

public class AuthSystem {
    private List<User> users;

    public AuthSystem() {
        this.users = JSONPersistence.loadUsers();
    }

    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public boolean signup(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        User newUser = new User(username, password, "volunteer");
        users.add(newUser);
        JSONPersistence.saveUsers(users);
        return true;
    }

    public boolean isAdmin(User user) {
        return user.getRole().equals("admin");
    }

    public List<User> getUsers() {
        return users;
    }
}
