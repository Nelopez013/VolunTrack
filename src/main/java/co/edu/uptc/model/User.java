package co.edu.uptc.model;

import java.util.Objects;

public class User {

    private String name,lastname;
    private String username;
    private int age;
    private String email;
    private String password;
    private String role; // "admin" or "volunteer"
    
    public User(String name, String lastname, String username, int age, String email, String password, String role) {
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.age = age;
        this.email = email;
        this.password = password;
        this.role = role.toLowerCase();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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