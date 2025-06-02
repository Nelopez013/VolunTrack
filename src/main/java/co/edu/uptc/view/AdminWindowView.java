package co.edu.uptc.view;

import co.edu.uptc.model.User;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminWindowView extends Application {

    private User user;

    // Constructor para recibir el usuario
    public AdminWindowView() {
        // vacío para el launcher si se usa launch()
    }

    public AdminWindowView(User user) {
        this.user = user;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Admin Panel");

        Label welcomeLabel = new Label("Welcome, Admin " + (user != null ? user.getUsername() : ""));
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        VBox root = new VBox(welcomeLabel);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

