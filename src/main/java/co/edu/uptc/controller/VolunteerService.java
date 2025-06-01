package co.edu.uptc.controller;

import co.edu.uptc.model.*;
import co.edu.uptc.persistence.JSONPersistence;

import java.util.ArrayList;
import java.util.List;

public class VolunteerService {
    private List<Activity> activities;
    private List<User> users;
    private JSONPersistence persistence;

    public VolunteerService() {
        persistence = new JSONPersistence();
        this.activities = persistence.loadActivities();
        this.users = persistence.loadUsers();

        // Ensure default admin exists
        boolean adminExists = false;
        for (User user : users) {
            if (user.getUsername().equals("admin") && user.getRole().equals("admin")) {
                adminExists = true;
                break;
            }
        }
        if (!adminExists) {
            User admin = new User("admin", "admin123", "admin");
            users.add(admin);
            saveData();
            System.out.println("Default admin user created: username=admin, password=admin123");
        }
    }

    public void saveData() {
        persistence.saveActivities(activities);
        persistence.saveUsers(users);
    }

    public void registerUser(User user) {
        users.add(user);
        saveData();
    }

    public User authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public Activity getActivityByName(String name) {
        for (Activity a : activities) {
            if (a.getName().equalsIgnoreCase(name)) {
                return a;
            }
        }
        return null;
    }

    public void createActivity(Activity activity) {
        activities.add(activity);
        saveData();
    }

    public void deleteActivity(Activity activity) {
        activities.remove(activity);
        saveData();
    }

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
}