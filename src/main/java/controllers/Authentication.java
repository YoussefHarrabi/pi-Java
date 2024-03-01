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
import org.mindrot.jbcrypt.BCrypt;

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
        CrudUtilisateurs x = new CrudUtilisateurs();

        // Check if the user exists and the password matches
        if (user != null && BCrypt.checkpw(pass, user.getMotDePasse()) ) {
            String userRole = x.getUserRoleByEmail(userEmail);
            if (userRole != null && userRole.equals("User")) {
                // Navigate to the ticket screen
                switchScene("/Ticket/Ticket.fxml", event);
                showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome " + user.getPrenom() + "!");
            } else {
                // Redirect to another page for users with roles other than "User"
                switchScene("/AjouterUser.fxml", event); // Redirect to the admin page, for example
                showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome Admin!");
            }
        } else {
            // Show error message for invalid email or password
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

    @FXML
    void forgotPassword(ActionEvent event) {
        switchScene("/ForgotPassword.fxml", event);
    }

    public void register(ActionEvent event) {
        switchScene("/Registre.fxml", event);
    }
}
