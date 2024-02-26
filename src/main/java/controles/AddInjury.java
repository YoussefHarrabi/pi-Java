package controles;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import entities.Incident;
import entities.Injury;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.stage.Stage;
import services.IncidentServices;
import services.InjuryServices;

import static java.lang.Math.round;

public class AddInjury implements Initializable {

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
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Incident> incidID;

    @FXML
    private ComboBox<String> Type;

    @FXML
    private Slider severity ;
    @FXML
    private TextField number_pers;
    @FXML
    private Button back;

    String sev;


    @FXML
    private Button AddInjury;




    @FXML
    void AddInjury(ActionEvent event) {
        if (!validateInput()) {
            return; // Exit method if input validation fails
        }


        Injury injury = new Injury(incidID.getSelectionModel().getSelectedItem().getIncidentId(), Type.getSelectionModel().getSelectedItem(),stringToInt(number_pers.getText()),sev);
        InjuryServices injuryServices = new InjuryServices();
        try {
            injuryServices.addEntity(injury);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Injury added successfully");
            alert.show();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/displayInjury.fxml"));

            try {
                Parent root = loader.load();
                displayInjury displayInjury = loader.getController();



                Type.getScene().setRoot(root);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }

    }

    @FXML
    void initialize() {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        severity.valueProperty().addListener((observable, oldValue, newValue) -> {
            // newValue contains the updated value of the slider
            double sliderValue = newValue.doubleValue();

            if (sliderValue <= 30) {
                sev = "Low";
            } else if (sliderValue <= 60) {
                sev = "Medium";
            } else {
                sev = "High";
            }
        });

        Type.getItems().addAll(type);
        incidID.getItems().addAll(new IncidentServices().getAllData());
        back.setOnMouseClicked(event -> {
            Stage primaryStage = (Stage) back.getScene().getWindow(); // Get primaryStage
            Home.switchScene(primaryStage, "/displayInjury.fxml");
        });

    }
    public static int stringToInt(String str) {
        try {
            // Use Integer.parseInt() to convert string to integer
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            // Handle the case where the string is not a valid integer
            // You can choose to return a default value, throw an exception, or handle it differently
            System.err.println("Error: Invalid integer format");
            return 0; // Default value
        }
    }
    public  boolean validateInput() {
        // Check if any field is null or empty
        if (incidID.getSelectionModel().isEmpty() || Type.getSelectionModel().isEmpty() ||
                number_pers.getText().isEmpty()) {
            showAlert("Please fill in all fields.");
            return false;
        }

        // Check if number of people contains only numbers and is positive
        String numPeople = number_pers.getText();
        if (!numPeople.matches("\\d+") || Integer.parseInt(numPeople) <= 0) {
            showAlert("Number of people must be a positive integer.");
            return false;
        }
        if (severity.getValue() == severity.getMin()) {
            showAlert("Please drag the severity slider to set the severity.");
            return false;
        }

        // All validation checks passed
        return true;
    }
    // Utility method to show an alert dialog
    public void showAlert(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(message);
            alert.show();
        });
    }




}
