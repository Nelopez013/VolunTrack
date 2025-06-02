package co.edu.uptc.controller;

import co.edu.uptc.model.User;
import co.edu.uptc.persistence.JSONPersistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AuthSystem {
    private Map<String, List<User>> usersByRole;

    public AuthSystem() {
        this.usersByRole = JSONPersistence.loadUsersByRole();
        if (this.usersByRole == null) {
            // Inicializar con listas vacías si no existe archivo
            this.usersByRole = Map.of(
                "admin", new ArrayList<>(),
                "volunteer", new ArrayList<>()
            );
        }
    }

    public User login(String username, String password) {
        if (usersByRole == null) return null;

        for (List<User> userList : usersByRole.values()) {
            for (User user : userList) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    return user;
                }
            }
        }
        return null;
    }

    public boolean signup(String name, String lastname, String username, int age, String email, String password) {
        if (usersByRole == null) return false;

        List<User> volunteers = usersByRole.get("volunteer");
        if (volunteers == null) {
            volunteers = new ArrayList<>();
            usersByRole.put("volunteer", volunteers);
        }

        // Verificar si ya existe usuario con ese username
        for (List<User> userList : usersByRole.values()) {
            for (User user : userList) {
                if (user.getUsername().equals(username)) {
                    return false; // Usuario ya existe
                }
            }
        }

        User newUser = new User(name,lastname,username,age,email,password, "volunteer");
        volunteers.add(newUser);

        // Guardar cambios en JSON
        JSONPersistence.saveUsersByRole(usersByRole);
        return true;
    }

    public boolean isAdmin(User user) {
        return user != null && "admin".equals(user.getRole());
    }

    public Map<String, List<User>> getUsersByRole() {
        return usersByRole;
    }
}

