package co.edu.uptc.view;



import co.edu.uptc.model.*;
import co.edu.uptc.controller.VolunteerService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class RegisterView extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Sign up");

        TextField nameField = new TextField();
        nameField.setPromptText("Name:");
        nameField.setPrefHeight(25);
        nameField.setPrefWidth(200);
        nameField.setStyle("""
            -fx-font-size: 10px;
            -fx-font-family: 'Tahoma';
            -fx-text-fill:rgb(0, 0, 0);
            -fx-background-radius: 10px;
            -fx-border-radius: 10px;
            -fx-border-color:rgb(65, 67, 71);
            -fx-border-width: 2px;
            -fx-padding: 4px;
        """);
        Label nameNote = new Label("Only letters (A...Z)");

        TextField lastnameField = new TextField();
        lastnameField.setPromptText("Lastname:");
        lastnameField.setPrefHeight(25);
        lastnameField.setPrefWidth(200);
        lastnameField.setStyle("""
            -fx-font-size: 10px;
            -fx-font-family: 'Tahoma';
            -fx-text-fill:rgb(0, 0, 0);
            -fx-background-radius: 10px;
            -fx-border-radius: 10px;
            -fx-border-color:rgb(65, 67, 71);
            -fx-border-width: 2px;
            -fx-padding: 4px;
        """);
        Label surnameNote = new Label("Only letters (A...Z)");

        TextField usernameField = new TextField();
        usernameField.setPromptText("User:");
        usernameField.setPrefHeight(25);
        usernameField.setPrefWidth(420);
        usernameField.setStyle("""
            -fx-font-size: 10px;
            -fx-font-family: 'Tahoma';
            -fx-text-fill:rgb(0, 0, 0);
            -fx-background-radius: 10px;
            -fx-border-radius: 10px;
            -fx-border-color:rgb(65, 67, 71);
            -fx-border-width: 2px;
            -fx-padding: 4px;
        """);
        Label usernameNote = new Label("Use letters and/or numbers");

        TextField emailField = new TextField();
        emailField.setPromptText("Email:");
        emailField.setPrefHeight(25);
        emailField.setPrefWidth(420);
        emailField.setStyle("""
            -fx-font-size: 10px;
            -fx-font-family: 'Tahoma';
            -fx-text-fill:rgb(0, 0, 0);
            -fx-background-radius: 10px;
            -fx-border-radius: 10px;
            -fx-border-color:rgb(65, 67, 71);
            -fx-border-width: 2px;
            -fx-padding: 4px;
        """);
        Label emailNote = new Label("Must include an @ followed by the domain");

        TextField ageField = new TextField();
        ageField.setPromptText("Age:");
        ageField.setPrefHeight(25);
        ageField.setPrefWidth(420);
        ageField.setStyle("""
            -fx-font-size: 10px;
            -fx-font-family: 'Tahoma';
            -fx-text-fill:rgb(0, 0, 0);
            -fx-background-radius: 10px;
            -fx-border-radius: 10px;
            -fx-border-color:rgb(65, 67, 71);
            -fx-border-width: 2px;
            -fx-padding: 4px;
        """);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password:");
        passwordField.setPrefHeight(25);
        passwordField.setPrefWidth(420);
        passwordField.setStyle("""
            -fx-font-size: 10px;
            -fx-font-family: 'Tahoma';
            -fx-text-fill:rgb(0, 0, 0);
            -fx-background-radius: 10px;
            -fx-border-radius: 10px;
            -fx-border-color:rgb(65, 67, 71);
            -fx-border-width: 2px;
            -fx-padding: 4px;
        """);
        Label passwordNote = new Label("Minimum password of 6 characters");

        Button registerBtn = new Button("Sign up");
        registerBtn.setStyle("-fx-text-fill: white;-fx-background-color: #1C1C3C;-fx-background-radius: 20px; -fx-font-size: 14px; -fx-padding: 8px 20px;");

        Button goToWelcomeBtn = new Button("Go back");
        goToWelcomeBtn.setStyle("-fx-background-color: #1C1C3C; -fx-text-fill: white;");
        goToWelcomeBtn.setOnAction(e -> {
            WelcomeView welcome = new WelcomeView();
            try {
                welcome.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        nameField.textProperty().addListener((obs, oldText, newText) -> {
            if (!newText.matches("[a-zA-Z]*")) nameField.setText(newText.replaceAll("[^a-zA-Z]", ""));
        });

        lastnameField.textProperty().addListener((obs, oldText, newText) -> {
            if (!newText.matches("[a-zA-Z]*")) lastnameField.setText(newText.replaceAll("[^a-zA-Z]", ""));
        });

        usernameField.textProperty().addListener((obs, oldText, newText) -> {
            if (!newText.matches("[a-zA-Z0-9]*")) usernameField.setText(newText.replaceAll("[^a-zA-Z0-9]", ""));
        });

        VBox leftForm = new VBox(10);
        leftForm.setPadding(new Insets(30));
        leftForm.setPrefWidth(450);
        leftForm.setStyle("-fx-background-color:rgb(240, 240, 231)");
        leftForm.setAlignment(Pos.BOTTOM_CENTER);

        VBox nameBox = new VBox(2, new HBox(10, nameField, lastnameField), new HBox(100, nameNote, surnameNote));
        VBox userBox = new VBox(2, usernameField, usernameNote);
        VBox emailBox = new VBox(2, emailField, emailNote);
        VBox ageBox = new VBox(1, ageField);
        VBox passBox = new VBox(2, passwordField, passwordNote);

        nameNote.setAlignment(Pos.BASELINE_LEFT);
        surnameNote.setAlignment(Pos.BASELINE_LEFT);
        usernameNote.setAlignment(Pos.BASELINE_LEFT);
        emailNote.setAlignment(Pos.BASELINE_LEFT);
        passwordNote.setAlignment(Pos.BASELINE_LEFT);

        nameNote.setMaxWidth(Double.MAX_VALUE);
        surnameNote.setMaxWidth(Double.MAX_VALUE);
        usernameNote.setMaxWidth(Double.MAX_VALUE);
        emailNote.setMaxWidth(Double.MAX_VALUE);
        passwordNote.setMaxWidth(Double.MAX_VALUE);

        Region topSpacer = new Region();
        HBox.setHgrow(topSpacer, Priority.ALWAYS);

        HBox bottomRight = new HBox(topSpacer, goToWelcomeBtn);
        bottomRight.setAlignment(Pos.CENTER_RIGHT);
        bottomRight.setPadding(new Insets(200, 0, 0, 0));

        leftForm.getChildren().addAll(
                topSpacer,
                title("Sign up"),
                nameBox,
                userBox,
                emailBox,
                ageBox,
                passBox,
                registerBtn,
                bottomRight
        );

        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/co/edu/uptc/Images/LogoType.jpg")));
        imageView.setFitWidth(450);
        imageView.setFitHeight(550);

        VBox imageBox = new VBox(imageView);
        imageBox.setAlignment(Pos.CENTER);
        imageBox.setStyle("-fx-background-color:  #1C1C3C");

        HBox root = new HBox(leftForm, imageBox);
        Scene scene = new Scene(root, 900, 750);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/co/edu/uptc/Images/LogoType.jpg")));
        stage.setScene(scene);
        stage.show();

        registerBtn.setOnAction(e -> {
            String name = nameField.getText();
            String lastName = lastnameField.getText();
            String username = usernameField.getText();
            String ageText = ageField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();

            if (name.isEmpty() || lastName.isEmpty() || username.isEmpty() || ageText.isEmpty() || email.isEmpty() || password.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Please fill in all fields.");
                return;
            }

            int age;
            try {
                age = Integer.parseInt(ageText);
                if (age <= 0) {
                    showAlert(Alert.AlertType.ERROR, "Age must be a positive number.");
                    return;
                }
                if (age < 18) {
                    showAlert(Alert.AlertType.ERROR, "You must be at least 18 years old to register.");
                    return;
                }
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Age must be a number.");
                return;
            }

            if (password.length() < 6) {
                showAlert(Alert.AlertType.ERROR, "Password must be at least 6 characters.");
                return;
            }

            if (!email.contains("@")) {
                showAlert(Alert.AlertType.ERROR, "Invalid email format.");
                return;
            }

            VolunteerService service = VolunteerService.getInstance();
            if (service.isUsernameTaken(username)) {
                showAlert(Alert.AlertType.ERROR, "Username already exists.");
            } else {
                Volunteer newVolunteer = new Volunteer(name, lastName, username,age,email, password,"volunteer");
                service.registerUser(newVolunteer);
                showAlert(Alert.AlertType.INFORMATION, "Registration successful!");

                nameField.clear();
                lastnameField.clear();
                usernameField.clear();
                ageField.clear();
                emailField.clear();
                passwordField.clear();
            }
        });
    }

    private Label title(String text) {
        Label title = new Label(text);
        title.setFont(Font.font("Tahoma", FontWeight.BOLD, 24));
        title.setTextFill(Color.BLACK);
        title.setStyle("-fx-border-color: transparent transparent black transparent; -fx-border-width: 0 0 2px 0; -fx-padding: 0 0 5px 0;");
        return title;
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}



