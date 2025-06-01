package co.edu.uptc.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class WelcomeView extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Bienvenido a VolunTrack");

        // Imagen de fondo
        Image imageBackgrd = new Image(getClass().getResourceAsStream("/co/edu/uptc/Images/Volunteers.jpg"));
        ImageView backgroundView = new ImageView(imageBackgrd);
        backgroundView.setFitWidth(1040);
        backgroundView.setFitHeight(750);
        backgroundView.setOpacity(0.7);

        // Título
        Label titleLabel = new Label("Welcome to Voluntrack");
        titleLabel.setStyle("-fx-font-size: 22px; -fx-font-family: 'Arial Black'; -fx-text-fill:rgb(12, 19, 27);");

        // Botones principales
        Button loginButton = new Button("Login");
        Button registerButton = new Button("Sign up");
        loginButton.setPrefWidth(350);
        registerButton.setPrefWidth(350);
        loginButton.setStyle("-fx-font-size: 16px;-fx-background-color:rgb(13, 69, 142); -fx-text-fill: white;");
        registerButton.setStyle("-fx-font-size: 16px;-fx-background-color:rgb(13, 69, 143); -fx-text-fill: white;");

        // Acciones de botones
        loginButton.setOnAction(e -> {
            LoginView login = new LoginView();
            try {
                login.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

                registerButton.setOnAction(e -> {
            RegisterView register = new RegisterView();
            try {
                register.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Viñeta con acción como enlace
        Label signupLink = new Label("• If you don't have an account, click here to sign up.");
        signupLink.setStyle("-fx-text-fill: Black; -fx-font-size: 13px; -fx-underline: true; -fx-cursor: hand;");
        signupLink.setOnMouseClicked(e -> registerButton.fire());

        VBox bulletBox = new VBox(5, signupLink);
        bulletBox.setAlignment(Pos.CENTER);

        // Contenedor central
        VBox centerBox = new VBox(20, titleLabel, loginButton, registerButton, bulletBox);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setPadding(new Insets(40));

        // Botón salir
        Button exitButton = new Button("Exit");
        exitButton.setStyle("-fx-font-size: 12px; -fx-background-color:rgb(13, 69, 142); -fx-text-fill: white;");
        exitButton.setOnAction(e -> primaryStage.close());

        BorderPane content = new BorderPane();
        content.setCenter(centerBox);
        content.setBottom(exitButton);
        BorderPane.setAlignment(exitButton, Pos.BOTTOM_RIGHT);
        BorderPane.setMargin(exitButton, new Insets(10));

        // Fondo + contenido
        StackPane root = new StackPane(backgroundView, content);

        // Mostrar escena
        Scene scene = new Scene(root, 1040, 750);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/co/edu/uptc/Images/LogoType.jpg")));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}