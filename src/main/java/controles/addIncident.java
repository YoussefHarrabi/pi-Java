package controles;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.Pattern;

import entities.AutoCompleteTextField;
import entities.Incident;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.action.Action;
import org.controlsfx.control.textfield.TextFields;
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


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> incidentType;

    @FXML
    private TextField IncidentPlace;

    @FXML
    private Spinner<Time> IncidentHour;

    @FXML
    private TextField Description;

    @FXML
    private Button AddIncident;

    @FXML
    private Button back;



    @FXML
    private ListView<String> suggestionsListView = new ListView<>();

    @FXML
    void initialize() {

    }
    public void AddIncident(javafx.event.ActionEvent actionEvent) {

        if (!validateInput()) {
            return; // Exit method if input validation fails
        }
        LocalTime currentTime = LocalTime.now();


        if (IncidentHour.getValue().toLocalTime().isAfter(currentTime)) {
            // Time is after the current time
            // Handle this case, maybe show an error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Incident time cannot be in the future");
            alert.show();
        } else {
            // Time is not after the current time, it's valid
            Time selectedTime = IncidentHour.getValue();
            Incident incident = new Incident(
                    incidentType.getSelectionModel().getSelectedItem(),
                    IncidentPlace.getText(),
                    selectedTime, // Use the LocalTime value directly
                    Description.getText()
            );
            IncidentServices incidentServices = new IncidentServices();
            try {
                incidentServices.addEntity(incident);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Incident added successfully");
                alert.show();
                Image image=new Image("/images/attention.png");

                Notifications notifications=Notifications.create();
                notifications.graphic(new ImageView(image));
                notifications.text("Incident added");
                notifications.title("new Message");
                notifications.hideAfter(Duration.seconds(4));
                notifications.action(new Action("View", event -> {

                    // Your action code here
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/displayIncident.fxml"));
                    try {
                        Parent root = loader.load();
                        displayIncident displayincident = loader.getController();


                        incidentType.getScene().setRoot(root);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }));
                /*notifications.darkStyle();*/
                /*   notifications.position(Pos.BOTTOM_CENTER);*/
                notifications.showInformation();

            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.show();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        suggestionsListView.setVisible(false);

        // Listen for changes in the incidentPlace TextField
        IncidentPlace.textProperty().addListener((observable, oldValue, newValue) -> {
            String searchText = newValue.toLowerCase();
            ObservableList<String> filteredSuggestions = FXCollections.observableArrayList();
            for (String suggestion : Incident.getSuggestions()) {
                if (suggestion.toLowerCase().startsWith(searchText)) {
                    filteredSuggestions.add(suggestion);
                }
            }
            suggestionsListView.setItems(filteredSuggestions);

            // Show the ListView only when there are suggestions and the TextField is focused
            suggestionsListView.setVisible(!filteredSuggestions.isEmpty() && IncidentPlace.isFocused());
        });

        // Handle selection from the suggestions list
        suggestionsListView.setOnMouseClicked(event -> {
            String selectedSuggestion = suggestionsListView.getSelectionModel().getSelectedItem();
            if (selectedSuggestion != null) {
                IncidentPlace.setText(selectedSuggestion);
            }
        });

        // Close the suggestions list when clicking outside the TextField
     /*   IncidentPlace.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                suggestionsListView.setVisible(false);
            }
        });*/

        incidentType.getItems().addAll(Type);

        back.setOnMouseClicked(event -> {
            Stage primaryStage = (Stage) back.getScene().getWindow(); // Get primaryStage
            Home.switchScene(primaryStage, "/displayIncident.fxml");
        });
        SpinnerValueFactory<Time> valueFactory = new SpinnerValueFactory<Time>() {
            @Override
            public void decrement(int steps) {
                // Decrement the value by steps (if needed)
                Time currentTime = getValue();
                LocalTime localTime = currentTime.toLocalTime();
                LocalTime newTime = localTime.minusMinutes(steps); // For example, decrement by steps minutes
                setValue(Time.valueOf(newTime));
            }

            @Override
            public void increment(int steps) {
                // Increment the value by steps (if needed)
                Time currentTime = getValue();
                LocalTime localTime = currentTime.toLocalTime();
                LocalTime newTime = localTime.plusMinutes(steps); // For example, increment by steps minutes
                setValue(Time.valueOf(newTime));
            }
        };

        IncidentHour.setValueFactory(valueFactory);

        // Now you can safely set the value
        IncidentHour.getValueFactory().setValue(Time.valueOf(LocalTime.now()));

    }

    public boolean validateInput() {
        // Check if any field is null or empty
        if (incidentType.getSelectionModel().isEmpty() || IncidentPlace.getText().isEmpty() ||
                IncidentHour.getValue() ==null || Description.getText().isEmpty()) {
            showAlert("Please fill in all fields.");
            return false;
        }

        // Validate the format of the input
        if (!Pattern.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9](:[0-5][0-9])?$", IncidentHour.getEditor().getText())) {
            showAlert("Hour must be in the format HH:mm:ss or HH:mm.");
            return false;
        }

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

