package co.edu.uptc.view;

import co.edu.uptc.controller.InputUtils;
import co.edu.uptc.controller.InputValidator;
import co.edu.uptc.controller.VolunteerService;
import co.edu.uptc.model.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static VolunteerService volunteerService = new VolunteerService();
    static InputValidator validator = InputUtils::validateChoice;

    public static void main(String[] args) {
        while (true) {
            MenuPrinter.printMainMenu();
            int choice = getValidatedChoice(1, 3);
            switch (choice) {
                case 1 -> login();
                case 2 -> register();
                case 3 -> {
                    volunteerService.saveData();
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                }
            }
        }
    }

    private static int getValidatedChoice(int min, int max) {
        int choice;
        while (true) {
            choice = validator.validateChoice(scanner, min, max);
            if (choice == InputUtils.ErrorIsNotANumber) {
                System.out.println("Error: Please enter a number.");
            } else if (choice == InputUtils.ErrorOutOfRange) {
                System.out.println("Error: Number must be between " + min + " and " + max + ".");
            } else {
                break;
            }
            System.out.print("Try again: ");
        }
        return choice;
    }

    private static void login() {
        scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = volunteerService.authenticateUser(username, password);
        if (user == null) {
            System.out.println("Invalid username or password.");
            return;
        }

        if (user.getRole().equals("admin")) {
            adminMenu(user);
        } else {
            volunteerMenu(user);
        }
    }

    private static void register() {
        scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter lastname: ");
        String lastname = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();

        System.out.print("Are you registering as a volunteer? (yes/no): ");
        String answer = scanner.nextLine().trim().toLowerCase();

        if (answer.equals("yes")) {
            scanner.nextLine();
            System.out.print("Enter email: ");
            String email = scanner.nextLine();
            Volunteer volunteer = new Volunteer(name, lastname, username ,age, email,password,"volunteer");
            volunteerService.registerUser(volunteer);
        } else {
            System.out.println("Only the default admin is allowed. You will be registered as a volunteer.");
            Volunteer volunteer = new Volunteer(name, lastname, username,18, username + "@mail.com",password,"volunteer");
            volunteerService.registerUser(volunteer);
        }

        System.out.println("User registered successfully!");
    }

    private static void adminMenu(User admin) {
        while (true) {
            MenuPrinter.printAdminMenu();
            int choice = getValidatedChoice(1, 4);
            switch (choice) {
                case 1 -> createActivity();
                case 2 -> listActivities();
                case 3 -> deleteActivity();
                case 4 -> {
                    System.out.println("Logging out...");
                    return;
                }
            }
        }
    }

    private static void volunteerMenu(User volunteer) {
        while (true) {
            MenuPrinter.printVolunteerMenu();
            int choice = getValidatedChoice(1, 4);
            switch (choice) {
                case 1 -> enrollInActivity(volunteer);
                case 2 -> viewEnrolledActivities(volunteer);
                case 3 -> cancelEnrollment(volunteer);
                case 4 -> {
                    System.out.println("Logging out...");
                    return;
                }
            }
        }
    }

    private static void createActivity() {
        scanner.nextLine();
        System.out.print("Enter activity name: ");
        String name = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        Date date;
        while (true) {
            System.out.print("Enter date (yyyy-MM-dd): ");
            String dateStr = scanner.nextLine();
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
                break;
            } catch (Exception e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }

        System.out.print("Enter max capacity: ");
        int maxCapacity = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter activity type (1 for In-Person, 2 for Virtual): ");
        int type = scanner.nextInt();
        scanner.nextLine();

        Activity activity;
        if (type == 1) {
            System.out.print("Enter location: ");
            String location = scanner.nextLine();
            activity = new InPersonActivity(name, description, date, maxCapacity, location);
        } else {
            System.out.print("Enter platform: ");
            String platform = scanner.nextLine();
            activity = new VirtualActivity(name, description, date, maxCapacity, platform);
        }

        volunteerService.createActivity(activity);
        System.out.println("Activity created successfully!");
    }

    private static void listActivities() {
        System.out.println("\n===== Activities =====");
        for (Activity activity : volunteerService.getActivities()) {
            System.out.println(activity.getName() + " - " + activity.getType());
        }
    }

    private static void deleteActivity() {
        scanner.nextLine();
        System.out.print("Enter activity name to delete: ");
        String name = scanner.nextLine();
        Activity activity = volunteerService.getActivityByName(name);
        if (activity == null) {
            System.out.println("Activity not found.");
            return;
        }
        volunteerService.deleteActivity(activity);
        System.out.println("Activity deleted successfully.");
    }

    private static void enrollInActivity(User user) {
        scanner.nextLine();
        System.out.print("Enter activity name to enroll: ");
        String name = scanner.nextLine();
        volunteerService.enrollVolunteer(name, user);
    }

    private static void viewEnrolledActivities(User user) {
        volunteerService.viewVolunteerActivities(user);
    }

    private static void cancelEnrollment(User user) {
        scanner.nextLine();
        System.out.print("Enter activity name to cancel enrollment: ");
        String name = scanner.nextLine();
        volunteerService.cancelEnrollment(name, user);
    }
}
