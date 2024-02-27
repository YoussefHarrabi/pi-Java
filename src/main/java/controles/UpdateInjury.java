package controles;

import entities.AlertHelper;
import entities.Incident;
import entities.Injury;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import services.IncidentServices;
import services.InjuryServices;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateInjury implements Initializable {
    String [] type = { "Fracture",
            "Sprain",
            "Burn",
            "Cut",
            "Bruise",
            "Concussion",
            "Whiplash",
            "Laceration",
            "Abrasions",
            "Internal bleeding"};

    @FXML
    private Label id;

    @FXML
    private TextField incidID;

    @FXML
    private ComboBox<String> Type;

    @FXML
    private TextField number_pers;

    @FXML
    private ComboBox<String> severity;
    @FXML
    private Button back;

    @FXML
    private Button UInjury;

    @FXML
    private void UpdateInjury() {
        if (!validateInputInjury()) {
            return; // Exit method if validation fails
        }
        // Get values from UI components
        int id1 = Integer.parseInt(id.getText());
        int incidentId = Integer.parseInt(incidID.getText());
        String type = Type.getSelectionModel().getSelectedItem().toString();
        int numberOfPersons = Integer.parseInt(number_pers.getText());
        String sev = severity.getSelectionModel().getSelectedItem(); // Get selected severity

        // Create Injury object
        Injury injury = new Injury(incidentId, type, numberOfPersons, sev);
        UInjury.setOnAction(event -> {
            String confirmationMessage = "Are you sure you want to update this injury?";
            boolean confirmed = AlertHelper.showConfirmationAlert(confirmationMessage);
            if (confirmed) {
                // Update Injury entity
                InjuryServices injuryServices = new InjuryServices();
                try {
                    injuryServices.updateEntity(injury,id1);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Injury updated successfully");
                    alert.show();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/displayInjury.fxml"));

                } catch (Exception e) {
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

    @FXML
    private void handleTypeSelection() {
        // Handle the event when a type is selected from the ComboBox
        String selectedType = Type.getSelectionModel().getSelectedItem();
        if (selectedType != null) {
            // You can perform any additional actions here
            System.out.println("Selected Type: " + selectedType);
        }
    }

    public void setData(int idI, int inc, String type, String seve, int numberOfPersons) {
        id.setText(String.valueOf(idI));
        this.incidID.setText(String.valueOf(inc));
        Type.getSelectionModel().select(type);
        number_pers.setText(String.valueOf(numberOfPersons));
        severity.getSelectionModel().select(seve); // Set selected severity
    }



    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
        Type.getItems().addAll(type);
        // Add event handler for type selection
        Type.setOnAction(e -> handleTypeSelection());

        // Populate the ComboBox with a list of incidents
        IncidentServices incidentServices = new IncidentServices();
        severity.getItems().addAll("Low", "Medium", "High");
        /*List<Incident> incidents = incidentServices.getAllData();
        for (Incident incident : incidents) {
            incidID.getItems().add(incident);
        }*/
        back.setOnMouseClicked(event -> {
            Stage primaryStage = (Stage) back.getScene().getWindow(); // Get primaryStage
            Home.switchScene(primaryStage, "/displayInjury.fxml");
        });


    }
    private boolean validateInputInjury() {
        // Check for null or empty values in input fields
        if (incidID.getText().isEmpty() || Type.getSelectionModel().isEmpty() || number_pers.getText().isEmpty() || severity.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("All fields are required.");
            alert.show();
            return false; // Validation failed
        }

        // Validate incidentId and numberOfPersons as integers
        try {
            int incidentId = Integer.parseInt(incidID.getText());
            int numberOfPersons = Integer.parseInt(number_pers.getText());

            // Validate positive number of persons
            if (numberOfPersons <= 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Number of persons must be a positive integer.");
                alert.show();
                return false; // Validation failed
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Incident ID and number of persons must be valid integers.");
            alert.show();
            return false; // Validation failed
        }

        return true; // Validation passed
    }


}
