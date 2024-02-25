package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.scene.Node;

import Services.CrudUtilisateurs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Utilisateurs;

public class AjouterUser {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField EmailField;

    @FXML
    private TextField NomField;

    @FXML
    private TextField PasswordField;

    @FXML
    private TextField PrenomField;

    @FXML
    private ChoiceBox<String> RôleField; // Specify the type of ChoiceBox

    @FXML
    private Label errorNomLabel; // Add the missing Label attribute

    @FXML
    private Label errorPrenomLabel; // Add the missing Label attribute

    @FXML
    private Label errorEmailLabel; // Add the missing Label attribute

    @FXML
    private Label errorPasswordLabel; // Add the missing Label attribute

    @FXML
    private Label errorRoleLabel; // Add the missing Label attribute

    @FXML
    void addUser(ActionEvent event) {
        String nom = NomField.getText();
        String prenom = PrenomField.getText();
        String email = EmailField.getText();
        String password = PasswordField.getText();
        String role = RôleField.getValue(); // Get the selected role

        // Validate fields
        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || password.isEmpty() || role == null) {
            errorNomLabel.setText(nom.isEmpty() ? "Nom is required" : "");
            errorPrenomLabel.setText(prenom.isEmpty() ? "Prenom is required" : "");
            errorEmailLabel.setText(email.isEmpty() ? "Email is required" : "");
            errorPasswordLabel.setText(password.isEmpty() ? "Password is required" : "");
            errorRoleLabel.setText(role == null ? "Role is required" : "");
            return;
        }

        // Clear error labels if all fields are filled
        errorNomLabel.setText("");
        errorPrenomLabel.setText("");
        errorEmailLabel.setText("");
        errorPasswordLabel.setText("");
        errorRoleLabel.setText("");

        // Proceed with user addition
        Utilisateurs user = new Utilisateurs(nom, prenom, email, password, role);
        CrudUtilisateurs userservice = new CrudUtilisateurs();
        try {
            userservice.addEntity(user);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("User added successfully");
            alert.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to add user: " + e.getMessage());
            alert.show();
        }
    }

    @FXML
    private void goToDisplayUser(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayUser.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Afficher Utilisateurs");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        RôleField.setItems(FXCollections.observableArrayList("Admin", "User"));

    }

    public void logout(ActionEvent actionEvent) {
        // Clear any user session data
        // For example:
        // UserSession.clear();

        // Close the current window
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();

        // Navigate to the login screen
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Authentication.fxml"));
            Scene scene = new Scene(root);
            Stage loginStage = new Stage();
            loginStage.setScene(scene);
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Show an alert if there's an error navigating to the login screen
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while logging out. Please try again.");
            alert.showAndWait();
        }
    }
    }

