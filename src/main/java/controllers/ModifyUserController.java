package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Utilisateurs;
import Services.CrudUtilisateurs;

public class ModifyUserController {

    private Utilisateurs user;

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField roleField;

    private final CrudUtilisateurs crudUtilisateurs = new CrudUtilisateurs();

    public void initialize() {
        // Initialize the input fields with current user details
        if (user != null) {
            nomField.setText(user.getNom());
            prenomField.setText(user.getPrenom());
            emailField.setText(user.getEmail());
            roleField.setText(user.getRole());
        }
    }

    public void setUser(Utilisateurs user) {
        this.user = user;
    }

    @FXML
    private void saveChanges() {
        // Update the user with the new details
        if (user != null) {
            user.setNom(nomField.getText());
            user.setPrenom(prenomField.getText());
            user.setEmail(emailField.getText());
            user.setRole(roleField.getText());
            modificationSuccessful = true;

            // Update user in the database
            crudUtilisateurs.updateEntity(user);
            // Close the window
            Stage stage = (Stage) nomField.getScene().getWindow();
            stage.close();
        }
    }
    private boolean modificationSuccessful = false;

    // Method to set the modification status
    public void setModificationSuccessful(boolean successful) {
        this.modificationSuccessful = successful;
    }

    // Method to check if modification was successful
    public boolean isModificationSuccessful() {
        return modificationSuccessful;
    }



}
