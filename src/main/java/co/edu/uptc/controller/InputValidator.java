package co.edu.uptc.controller;

import java.util.Scanner;

@FunctionalInterface
public interface InputValidator {
    int validateChoice(Scanner scanner, int min, int max);
}
