package controles;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;

import entities.AutoCompleteTextField;
import entities.Incident;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import services.IncidentServices;

public class addIncident implements Initializable {
    String [] Type = { "Collision",
            "Vehicle Breakdown",
            "Road Debris",
            "Pothole Damage",
            "Vehicle Fire",
            "Flooding",
            "Pedestrian Accident",
            "Animal Crossing",
            "Road Construction",
            "Traffic Congestion"};
    private static final String[] PLACES = {
            "Place 1",
            "Place 2",
            "Place 3",
            // Add more places as needed
    };

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> incidentType;

    @FXML
    private TextField IncidentPlace;

    @FXML
    private TextField IncidentHour;

    @FXML
    private TextField Description;

    @FXML
    private Button AddIncident;

    @FXML
    private Button back;

    @FXML
    private MenuButton menu;

    private AutoCompleteTextField autoCompleteTextField;
    @FXML
    private void handleMenuItemAction(ActionEvent event) {
        MenuItem selectedItem = (MenuItem) event.getSource();


        for (MenuItem item : menu.getItems()) {
            item.setStyle("-fx-text-fill: black;");
        }


        selectedItem.setStyle("-fx-text-fill: white;");
    }




    @FXML
    void initialize() {

    }


    public void AddIncident(javafx.event.ActionEvent actionEvent) {

        if (!validateInput()) {
            return; // Exit method if input validation fails
        }

        Incident incident = new Incident(incidentType.getSelectionModel().getSelectedItem(),IncidentPlace.getText(), IncidentHour.getText(),Description.getText());
        IncidentServices incidentServices = new IncidentServices();
        try {
            incidentServices.addEntity(incident);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Incident added successfully");
            alert.show();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/displayIncident.fxml"));

            try {
                Parent root = loader.load();
                displayIncident displayincident = loader.getController();


                IncidentPlace.getScene().setRoot(root);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        incidentType.getItems().addAll(Type);

        back.setOnMouseClicked(event -> {
            Stage primaryStage = (Stage) back.getScene().getWindow(); // Get primaryStage
            Home.switchScene(primaryStage, "/displayIncident.fxml");
        });

    }
    public boolean validateInput() {
        // Check if any field is null or empty
        if (incidentType.getSelectionModel().isEmpty() || IncidentPlace.getText().isEmpty() ||
                IncidentHour.getText().isEmpty() || Description.getText().isEmpty()) {
            showAlert("Please fill in all fields.");
            return false;
        }

        // Check if Hour is in the correct format (HH:mm)
        String hourInput = IncidentHour.getText();

// Validate the format of the input
        if (!Pattern.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$", hourInput.trim())) {
            showAlert("Hour must be between 00:00 and 23:59");
            return false;
        }
// Split the hour and minute parts
      /*  String[] parts = hourInput.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);

// Validate the hour and minute values
        if (hour > 23 || minute > 59) {
            showAlert("Hour must be between 00:00 and 23:59.");
            return false;
        }*/




        // All validation checks passed
        return true;
    }

    // Utility method to show an alert dialog
    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }


}

