package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Services.CrudUtilisateurs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Utilisateurs;
import org.mindrot.jbcrypt.BCrypt;

public class Registre {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker agePicker;

    @FXML
    private TextField emailField;

    @FXML
    private TextField nomField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField prenomField;

    private final CrudUtilisateurs crudUtilisateurs = new CrudUtilisateurs();

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @FXML
    void register(ActionEvent event) {
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        LocalDate selectedDate = agePicker.getValue(); // Assuming age is stored as a String
        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || password.isEmpty() || selectedDate == null) {
            // Display an alert if any of the fields are empty
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("All fields are required");
            alert.show();
            return;
        }
        if (!isValidEmail(email)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Invalid email format!!");
            alert.show();
            return;
        }

        // Check if the email already exists
        if (new CrudUtilisateurs().isEmailExistsInDatabase(email)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Email already exists");
            alert.show();
            return;
        }
        // Hash the password
        String hashedPassword = hashPassword(password);


        String age;

        age = selectedDate.toString();
        System.out.println(age);

        // Create a new Utilisateurs object
        Utilisateurs newUser = new Utilisateurs(nom, prenom, email, hashedPassword,"User", age);

        // Add the new user to the database
        crudUtilisateurs.addEntity(newUser);
    }

    @FXML
    void initialize() {

    }
    private void switchScene(String fxmlFile, ActionEvent event) {
        try {
            System.out.println("fxml:"+ fxmlFile);

            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void login(ActionEvent event) {
        switchScene("/Authentication.fxml", event);
    }
}
