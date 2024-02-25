package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Services.CrudUtilisateurs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Utilisateurs;

public class Authentication {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginButton;

    @FXML
    private TextField password;

    @FXML
    private TextField email;

    @FXML
    void login(ActionEvent event) {

        String userEmail = email.getText();
        String pass = password.getText();
        if (!isValidEmail(userEmail)) {
            showAlert(Alert.AlertType.ERROR, "Invalid Email", "Please enter a valid email address.");
            return;
        }

        // Fetch user data from the database
        Utilisateurs user = new CrudUtilisateurs().getUserByEmail(userEmail);

        // Check if the user exists and the password matches
        if (user != null && user.getMotDePasse().equals(pass)) {
            // Navigate to the next screen or perform any other action
            // For example:
            // switchScene();
            // Or show a success message
            switchScene("/AjouterUser.fxml", event);
            showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome " + user.getPrenom() + "!");
        } else {
            // Show error message
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid email or password!");
        }

    }
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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

    @FXML
    void initialize() {

    }

}
