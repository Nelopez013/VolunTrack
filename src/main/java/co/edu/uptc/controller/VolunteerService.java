package co.edu.uptc.controller;

import co.edu.uptc.model.*;
import co.edu.uptc.persistence.JSONPersistence;
import java.util.*;

public class VolunteerService {

    private List<Activity> activities;
    private Map<String, List<User>> usersByRole;
    private static VolunteerService instance;
    private JSONPersistence persistence;

    public VolunteerService() {

        persistence = new JSONPersistence();
        this.activities = persistence.loadActivities();
        this.usersByRole = persistence.loadUsersByRole();

        usersByRole.putIfAbsent("admins", new ArrayList<>());
        usersByRole.putIfAbsent("volunteers", new ArrayList<>());

        if (usersByRole.get("admins").stream().noneMatch(u -> u.getUsername().equals("admin"))) {
            User admin = new User("Freddy","Alarcon","admin",34,"34@gmail.com", "admin123", "admin");
            usersByRole.get("admins").add(admin);
            saveData();
            System.out.println("Default admin user created: username=admin, password=admin123");
        }
    }

    
    // Método estático para obtener la instancia única (singleton)
    public static VolunteerService getInstance() {
        if (instance == null) {
            instance = new VolunteerService();
        }
        return instance;
    }

    public void saveData() {
        persistence.saveActivities(activities);
        persistence.saveUsersByRole(usersByRole);
    }

    public void registerUser(User user) {
        String roleKey = user.getRole().equalsIgnoreCase("admin") ? "admins" : "volunteers";
        usersByRole.get(roleKey).add(user);
        saveData();
    }

    public User authenticateUser(String username, String password) {
        return usersByRole.values().stream()
                .flatMap(List::stream)
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public Activity getActivityByName(String name) {
        return activities.stream()
                .filter(a -> a.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public void createActivity(Activity activity) {
        activities.add(activity);
        saveData();
    }

    public void deleteActivity(Activity activity) {
        activities.remove(activity);
        saveData();
    }

    public void cancelEnrollment(String activityName, User user) {
        Activity activity = getActivityByName(activityName);
        if (activity == null) {
            System.out.println("Activity not found.");
            return;
        }
        if (activity.getVolunteers().remove(user)) {
            saveData();
            System.out.println("Enrollment cancelled.");
        } else {
            System.out.println("User was not enrolled.");
        }
    }

    public void viewVolunteerActivities(User user) {
        List<Activity> enrolled = new ArrayList<>();
        for (Activity a : activities) {
            if (a.getVolunteers().contains(user)) {
                enrolled.add(a);
            }
        }
        if (enrolled.isEmpty()) {
            System.out.println("No enrolled activities.");
        } else {
            System.out.println("Enrolled in:");
            for (Activity a : enrolled) {
                System.out.println("- " + a.getName());
            }
        }
    }

    public User getUserByUsername(String username) {
        return usersByRole.values().stream()
                .flatMap(List::stream)
                .filter(u -> u.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElse(null);
    }

    public boolean isUsernameTaken(String username) {
    return usersByRole.values().stream()
            .flatMap(List::stream)
            .anyMatch(u -> u.getUsername().equalsIgnoreCase(username));
    }

    
    public void deleteUser(User user) {
        String roleKey = user.getRole().equalsIgnoreCase("admin") ? "admins" : "volunteers";

        // Eliminar usuario de la lista según su rol
        List<User> users = usersByRole.get(roleKey);
        if (users != null && users.remove(user)) {
            // Quitar al usuario de todas las actividades en que esté inscrito
            for (Activity activity : activities) {
                if (activity.getVolunteers().remove(user)) {
                    System.out.println("Removed user from activity: " + activity.getName());
                }
            }
            saveData();
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    // Método para inscribir voluntario en una actividad (ya tienes, solo lo repaso)
    public void enrollVolunteer(String activityName, User user) {
        Activity activity = getActivityByName(activityName);
        if (activity == null) {
            System.out.println("Activity not found.");
            return;
        }
        if (activity.getVolunteers().contains(user)) {
            System.out.println("Already enrolled.");
            return;
        }
        if (activity.getVolunteers().size() >= activity.getMaxCapacity()) {
            System.out.println("Activity is full.");
            return;
        }
        activity.getVolunteers().add(user);
        saveData();
        System.out.println("Enrolled successfully.");
    }
}







