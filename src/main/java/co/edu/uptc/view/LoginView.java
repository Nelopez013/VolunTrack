package co.edu.uptc.view;

import co.edu.uptc.controller.VolunteerService;
import co.edu.uptc.model.User;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.effect.DropShadow;
import javafx.stage.Stage;

public class LoginView extends Application {

    private VolunteerService volunteerService = new VolunteerService();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Log in");

        // Left: logo circular
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/co/edu/uptc/Images/LogoType.jpg")));
        imageView.setFitWidth(250);
        imageView.setFitHeight(250);
        Circle clip = new Circle(125, 125, 125);
        imageView.setClip(clip);

        VBox leftBox = new VBox(imageView);
        leftBox.setAlignment(Pos.CENTER);
        leftBox.setStyle("-fx-background-color: #0B1320;");
        leftBox.setPrefWidth(300);

        Label titleLabel = new Label("Log in");
        titleLabel.setStyle("""
           -fx-font-weight: bold;
           -fx-font-size: 38px;
           -fx-font-family: 'Tahoma';
           -fx-text-fill:rgb(1, 2, 4);
           -fx-padding: 10px 20px;
        """);

        // Input: username
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setStyle("""
            -fx-font-size: 14px;
            -fx-font-family: 'Tahoma';
            -fx-text-fill:rgb(0, 0, 0);
            -fx-background-radius: 10px;
            -fx-border-radius: 10px;
            -fx-border-color:rgb(65, 67, 71);
            -fx-border-width: 2px;
            -fx-padding: 8px;
        """);

        Label usernameHint = new Label("• Enter your registered username");
        usernameHint.setTextFill(Color.BLACK);
        usernameHint.setStyle("-fx-font-family: 'Tahoma';");
        VBox.setMargin(usernameHint, new Insets(0, 310, 0, 0));

        // Input: password
        PasswordField passField = new PasswordField();
        passField.setPromptText("Password");
        passField.setStyle("""
            -fx-font-family: 'Tahoma';
            -fx-font-size: 14px;
            -fx-text-fill:rgb(0, 0, 0);
            -fx-background-radius: 10px;
            -fx-border-radius: 10px;
            -fx-border-color:rgb(65, 67, 71);
            -fx-border-width: 2px;
            -fx-padding: 8px;
        """);

        Label passHint = new Label("• Your password is case-sensitive");
        passHint.setTextFill(Color.BLACK);
        passHint.setStyle("-fx-font-family: 'Tahoma';");
        VBox.setMargin(passHint, new Insets(0, 310, 0, 0));

        // Botón "Log in" con difuminado
        Button loginButton = new Button("Log in");
        loginButton.setStyle("-fx-text-fill: white;-fx-background-color: #1C1C3C;-fx-background-radius: 20px; -fx-font-size: 14px; -fx-padding: 8px 30px;");
        DropShadow buttonShadow = new DropShadow(12, Color.rgb(0, 0, 0, 0.5));
        loginButton.setEffect(buttonShadow);

        HBox centeredButton = new HBox(loginButton);
        centeredButton.setAlignment(Pos.CENTER);

        Label messageLabel = new Label();
        messageLabel.setTextFill(Color.YELLOW);

        VBox formBox = new VBox(12, titleLabel, usernameField, usernameHint, passField, passHint, centeredButton, messageLabel);
        formBox.setPadding(new Insets(30));
        formBox.setAlignment(Pos.CENTER);
        formBox.setStyle("-fx-background-color:rgb(240, 240, 231);");
        formBox.setPrefWidth(600);

        // Layout principal
        HBox root = new HBox(leftBox, formBox);

        loginButton.setOnAction(e -> {
    String username = usernameField.getText();
    String password = passField.getText();
    boolean valid = true;

    if (!username.matches("[a-zA-Z0-9]+")) {
        usernameHint.setText("• Only letters and numbers allowed");
        usernameHint.setTextFill(Color.BLUEVIOLET);
        valid = false;
    } else {
        usernameHint.setText("• Enter your registered username");
        usernameHint.setTextFill(Color.BLUEVIOLET);
    }

    if (!password.matches("[a-zA-Z0-9]+")) {
        passHint.setText("• Only letters and numbers allowed");
        passHint.setTextFill(Color.BLUEVIOLET);
        valid = false;
    } else {
        passHint.setText("• Your password is case-sensitive");
        passHint.setTextFill(Color.BLUEVIOLET);
    }

    if (!valid) {
        messageLabel.setText("Please fix the fields above");
        messageLabel.setTextFill(Color.BLUEVIOLET);
        return;
    }

    User user = volunteerService.authenticateUser(username, password);
    if (user != null) {
        messageLabel.setText("Welcome, " + user.getUsername() + " (" + user.getRole() + ")");
        messageLabel.setTextFill(Color.LIGHTGREEN);

        // Abrir ventana según rol
        Stage currentStage = (Stage) loginButton.getScene().getWindow();

        if (user.getRole().equalsIgnoreCase("admin")) {
            AdminWindowView adminView = new AdminWindowView(user); // Pasa user si quieres
            try {
                adminView.start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            currentStage.close();
        } else if (user.getRole().equalsIgnoreCase("volunteer")) {
            VolunteerWindowView volunteerView = new VolunteerWindowView(user);
            try {
                volunteerView.start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            currentStage.close();
                } else {
                    messageLabel.setText("Unknown role");
                    messageLabel.setTextFill(Color.RED);
                }
            } else {
            messageLabel.setText("Invalid credentials");
            messageLabel.setTextFill(Color.RED);
                }
            });
        Scene scene = new Scene(root, 900, 750);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/co/edu/uptc/Images/LogoType.jpg")));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
