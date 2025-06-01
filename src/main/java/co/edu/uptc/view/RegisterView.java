package co.edu.uptc.view;

import co.edu.uptc.controller.VolunteerService;
import co.edu.uptc.model.User;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class RegisterView {

    private VolunteerService volunteerService = new VolunteerService();

    public void start(Stage stage) {
        stage.setTitle("Registro de Usuario");

        // Etiquetas y campos de texto
        Label titleLabel = new Label("Registro de nuevo voluntario");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Nombre de usuario");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Contraseña");

        // Botones
        Button registerButton = new Button("Registrarse");
        Button backButton = new Button("Volver");

        // Mensaje
        Label messageLabel = new Label();

        // Acciones
        registerButton.setOnAction(e -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();

            if (username.isEmpty() || password.isEmpty()) {
                messageLabel.setText("Todos los campos son obligatorios.");
                return;
            }

            if (volunteerService.authenticateUser(username, password) != null) {
                messageLabel.setText("El usuario ya existe.");
                return;
            }

            User newUser = new User(username, password, "volunteer");
            volunteerService.registerUser(newUser);
            messageLabel.setText("¡Registro exitoso!");
        });

        backButton.setOnAction(e -> {
            WelcomeView welcome = new WelcomeView();
            welcome.start(stage);
        });

        // Layout
        VBox layout = new VBox(15, titleLabel, usernameField, passwordField, registerButton, backButton, messageLabel);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));

        Scene scene = new Scene(layout, 400, 350);
        stage.setScene(scene);
        stage.show();
    }
}
