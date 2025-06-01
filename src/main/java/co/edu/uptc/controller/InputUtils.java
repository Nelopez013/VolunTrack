package co.edu.uptc.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtils {
    public static final int ErrorIsNotANumber = -1;
    public static final int ErrorOutOfRange = -2;

    public static int validateChoice(Scanner scanner, int min, int max) {
        try {
            int choice = scanner.nextInt();
            if (choice < min || choice > max) {
                return ErrorOutOfRange;
            }
            return choice;
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Clear invalid input
            return ErrorIsNotANumber;
        }
    }
}
