package co.edu.uptc.view;

public class MenuPrinter {

    public static void printMainMenu() {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    public static void printAdminMenu() {
        System.out.println("\n=== ADMIN MENU ===");
        System.out.println("1. Create Activity");
        System.out.println("2. List Activities");
        System.out.println("3. Delete Activity");
        System.out.println("4. Logout");
        System.out.print("Enter your choice: ");
    }

    public static void printVolunteerMenu() {
        System.out.println("\n=== VOLUNTEER MENU ===");
        System.out.println("1. Enroll in Activity");
        System.out.println("2. View Enrolled Activities");
        System.out.println("3. Cancel Enrollment");
        System.out.println("4. Logout");
        System.out.print("Enter your choice: ");
    }
}
