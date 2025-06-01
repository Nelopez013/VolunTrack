package co.edu.uptc.model;

import java.util.Objects;

public class User {
    private String username;
    private String password;
    private String role; // "admin" or "volunteer"

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role.toLowerCase();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "User{username='" + username + "', role='" + role + "'}";
    }
}