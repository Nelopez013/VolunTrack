package co.edu.uptc.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class WelcomeView extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Welcome to VolunTrack");

        // Imagen de fondo
        Image imageBackgrd = new Image(getClass().getResourceAsStream("/co/edu/uptc/Images/Volunteers.jpg"));
        ImageView backgroundView = new ImageView(imageBackgrd);
        backgroundView.setFitWidth(1040);
        backgroundView.setFitHeight(750);
        backgroundView.setOpacity(0.5);

        // Logo con fondo semitransparente para mayor visibilidad
        Image logoImage = new Image(getClass().getResourceAsStream("/co/edu/uptc/Images/LogoType.jpg"));
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitWidth(280);
        
        logoView.setPreserveRatio(true);
        logoView.setSmooth(true);

        Rectangle logoBg = new Rectangle(320, 320);
        logoBg.setArcWidth(30);
        logoBg.setArcHeight(30);
        logoBg.setFill(Color.rgb(0, 0, 0, 0.4));

        StackPane logoPane = new StackPane(logoBg, logoView);
        logoPane.setAlignment(Pos.CENTER);

        // Botones principales
        Button loginButton = new Button("Login");
        Button registerButton = new Button("Sign up");
        styleMainButton(loginButton);
        styleMainButton(registerButton);

        // Acciones
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

        // Viñeta
        Label signupLink = new Label("• If you don't have an account, click here to sign up.");
        signupLink.setStyle("-fx-text-fill: black; -fx-font-size: 13px; -fx-underline: true; -fx-cursor: hand;");
        signupLink.setOnMouseClicked(e -> registerButton.fire());
        VBox bulletBox = new VBox(5, signupLink);
        bulletBox.setAlignment(Pos.CENTER);

        // Botón salir
        Button exitButton = new Button("Exit");
        exitButton.setStyle("""
            -fx-font-family: 'Tahoma';
            -fx-font-size: 12px;
            -fx-background-color: rgb(13, 49, 97);
            -fx-text-fill: white;
            -fx-border-radius: 8px;
            -fx-background-radius: 8px;
            -fx-padding: 6px 12px;
        """);
        exitButton.setEffect(new DropShadow(10, Color.rgb(0, 0, 0, 0.5)));
        exitButton.setOnAction(e -> primaryStage.close());

        // Centro
        VBox centerBox = new VBox(25, logoPane, loginButton, registerButton, bulletBox);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setPadding(new Insets(40));

        BorderPane content = new BorderPane();
        content.setCenter(centerBox);
        content.setBottom(exitButton);
        BorderPane.setAlignment(exitButton, Pos.BOTTOM_RIGHT);
        BorderPane.setMargin(exitButton, new Insets(10));

        StackPane root = new StackPane(backgroundView, content);

        Scene scene = new Scene(root, 1040, 750);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/co/edu/uptc/Images/LogoType.jpg")));
        primaryStage.show();
    }

    private void styleMainButton(Button button) {
        button.setPrefWidth(350);
        button.setStyle("""
            -fx-font-family: 'Tahoma';
            -fx-font-size: 18px;
            -fx-background-color: rgb(12, 50, 100);
            -fx-text-fill: white;
            -fx-border-color: rgb(1, 0, 2);
            -fx-border-width: 2px;
            -fx-border-radius: 12px;
            -fx-background-radius: 12px;
            -fx-padding: 10px 25px;
        """);

        DropShadow shadow = new DropShadow();
        shadow.setRadius(14);
        shadow.setOffsetX(0);
        shadow.setOffsetY(6);
        shadow.setColor(Color.rgb(0, 0, 0, 0.5));

        button.setEffect(shadow);

        // Hover efecto
        button.setOnMouseEntered(e -> {
            button.setStyle("""
                -fx-font-family: 'Tahoma';
                -fx-font-size: 18px;
                -fx-background-color: rgb(12, 72, 151);
                -fx-text-fill: white;
                -fx-border-color: rgb(1, 0, 2);
                -fx-border-width: 2px;
                -fx-border-radius: 12px;
                -fx-background-radius: 12px;
                -fx-padding: 10px 25px;
            """);
            shadow.setRadius(18);
            button.setEffect(shadow);
        });

        button.setOnMouseExited(e -> {
            button.setStyle("""
                -fx-font-family: 'Tahoma';
                -fx-font-size: 18px;
                -fx-background-color: rgb(13, 69, 142);
                -fx-text-fill: white;
                -fx-border-color: rgb(1, 0, 2);
                -fx-border-width: 2px;
                -fx-border-radius: 12px;
                -fx-background-radius: 12px;
                -fx-padding: 10px 25px;
            """);
            shadow.setRadius(14);
            button.setEffect(shadow);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}