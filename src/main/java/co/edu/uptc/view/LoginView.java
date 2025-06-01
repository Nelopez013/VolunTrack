package co.edu.uptc.view;

import co.edu.uptc.controller.VolunteerService;
import co.edu.uptc.model.User;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginView extends Application {

    private VolunteerService volunteerService = new VolunteerService();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login");

        // UI Elements
        Label userLabel = new Label("Username:");
        TextField userField = new TextField();

        Label passLabel = new Label("Password:");
        PasswordField passField = new PasswordField();

        Button loginButton = new Button("Login");
        Label messageLabel = new Label();

        // Layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(userLabel, 0, 0);
        grid.add(userField, 1, 0);
        grid.add(passLabel, 0, 1);
        grid.add(passField, 1, 1);
        grid.add(loginButton, 1, 2);
        grid.add(messageLabel, 1, 3);

        loginButton.setOnAction(e -> {
            String username = userField.getText();
            String password = passField.getText();
            User user = volunteerService.authenticateUser(username, password);

            if (user != null) {
                messageLabel.setText("Welcome, " + user.getUsername() + " (" + user.getRole() + ")");
                // Aquí podrías abrir otra ventana dependiendo del rol:
                // if (user.getRole().equals("admin")) openAdminDashboard();
                // else openVolunteerDashboard();
            } else {
                messageLabel.setText("Invalid credentials");
            }
        });

        primaryStage.setScene(new Scene(grid, 900, 500));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/co/edu/uptc/Images/LogoType.jpg")));
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}