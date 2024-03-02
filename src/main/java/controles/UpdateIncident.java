package controles;

import entities.AlertHelper;
import entities.Incident;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import services.IncidentServices;

import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;
import java.util.function.IntBinaryOperator;
import java.util.regex.Pattern;

public class UpdateIncident implements Initializable {
    String[] types = {
            "Collision",
            "Vehicle Breakdown",
            "Road Debris",
            "Pothole Damage",
            "Vehicle Fire",
            "Flooding",
            "Pedestrian Accident",
            "Animal Crossing",
            "Road Construction",
            "Traffic Congestion"
    };
    @FXML
    private ComboBox<String> type;

    @FXML
    private TextField placeTextField;

    @FXML
    private TextField hourTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private Label IncidentId;

    @FXML
    private Button back;

    @FXML
    private Button updateButton;

    @FXML
    private void updateButtonClicked() {
        if (!validateInput()) {
            return; // Exit method if validation fails
        }
        int idIn = Integer.parseInt(IncidentId.getText());
        String type1 = type.getSelectionModel().getSelectedItem().toString();
        String place = placeTextField.getText();
        Time hour = Time.valueOf(hourTextField.getText());
        String description = descriptionTextField.getText();

        IncidentServices incidentServices = new IncidentServices();
        Incident incident = new Incident(type1,place,hour,description);

        updateButton.setOnAction(event -> {
            String confirmationMessage = "Are you sure you want to update this incident?";
            boolean confirmed = AlertHelper.showConfirmationAlert(confirmationMessage);
            if (confirmed) {
                try {
                    incidentServices.updateEntity(incident,idIn);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Incident updated successfully");
                    alert.show();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/displayIncident.fxml"));



                }catch (Exception e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(e.getMessage());
                    alert.show();
                }

            } else {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setContentText("Update operation cancelled.");
                alert2.show();
            }
        });


    }
    public void setData(int incidentId, String typess, String place, String hour, String description) {
        IncidentId.setText(String.valueOf(incidentId));
        type.getSelectionModel().select(typess);
        placeTextField.setText(place);
        hourTextField.setText(hour);
        descriptionTextField.setText(description);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        type.getItems().addAll(types);
        back.setOnMouseClicked(event -> {
            Stage primaryStage = (Stage) back.getScene().getWindow(); // Get primaryStage
            Home.switchScene(primaryStage, "/displayIncident.fxml");
        });

    }
    public boolean validateInput() {
        // Check if any field is null or empty
        if (type.getSelectionModel().isEmpty() || placeTextField.getText().isEmpty() ||
                hourTextField.getText().isEmpty() || descriptionTextField.getText().isEmpty()) {
            showAlert("Please fill in all fields.");
            return false;
        }

        String hourInput = hourTextField.getText();

// Validate the format of the input
        if (!Pattern.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$", hourInput)) {
            showAlert("Hour must be in the format HH:mm.");
            return false;
        }

// Split the hour and minute parts
        String[] parts = hourInput.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);

// Validate the hour and minute values
        if (hour > 24 || minute > 59) {
            showAlert("Hour must be between 00:00 and 24:00.");
            return false;
        }



        // All validation checks passed
        return true;

    }
    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

