package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Utilisateurs;
import Services.CrudUtilisateurs;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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

    @FXML
    private DatePicker agePicker; // Add agePicker field

    private final CrudUtilisateurs crudUtilisateurs = new CrudUtilisateurs();

    public void initialize() {
        // Initialize the input fields with current user details
        if (user != null) {
            nomField.setText(user.getNom());
            prenomField.setText(user.getPrenom());
            emailField.setText(user.getEmail());
            roleField.setText(user.getRole());
            // Parse age string to LocalDate and set in agePicker
            if (user.getAge() != null) {
                agePicker.setValue(LocalDate.parse(user.getAge()));
            }
        }
    }
    private DisplayUser displayUserController;

    public void setDisplayUserController(DisplayUser displayUserController) {
        this.displayUserController = displayUserController;
    }



    public void setUser(Utilisateurs user) {
        this.user = user;
        // Populate the fields with user data
        if (user != null) {
            nomField.setText(user.getNom());
            prenomField.setText(user.getPrenom());
            emailField.setText(user.getEmail());
            roleField.setText(user.getRole());

            // Parse the age string into a LocalDate object
            LocalDate age = null;
            try {
                age = LocalDate.parse(user.getAge());
            } catch (DateTimeParseException e) {
                System.out.println("Error parsing age: " + e.getMessage());
            }

            // Set the parsed age to the datepicker field
            if (age != null) {
                agePicker.setValue(age);
            }
        }
    }

    @FXML
    private void saveChanges() {
        // Update the user with the new details
        if (user != null) {
            user.setNom(nomField.getText());
            user.setPrenom(prenomField.getText());
            user.setEmail(emailField.getText());
            user.setRole(roleField.getText());
            // Get the selected date from agePicker and convert to string
            LocalDate selectedDate = agePicker.getValue();
            if (selectedDate != null) {
                user.setAge(selectedDate.toString());
            } else {
                user.setAge(null);
            }
            modificationSuccessful = true;

            // Update user in the database
            crudUtilisateurs.updateEntity(user);
            // Close the window
            Stage stage = (Stage) nomField.getScene().getWindow();
            stage.close();
            if (displayUserController != null) {
                displayUserController.refreshTable(); // Call refreshTable() in DisplayUserController
            }
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
