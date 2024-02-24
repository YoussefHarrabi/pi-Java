package controles;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import entities.Incident;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
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
    private TextField IncidentHour;

    @FXML
    private TextField Description;

    @FXML
    private Button AddIncident;


    @FXML
    void initialize() {

    }
    public void onStartup() throws IOException{
        String[] words = {"ariana", "sokra","manzah"};
        TextFields.bindAutoCompletion(IncidentPlace,words);
    }

    public void AddIncident(javafx.event.ActionEvent actionEvent) {


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
                displayincident.setPlaceTextField(IncidentPlace.getText());
                displayincident.setHourTextField(IncidentHour.getText());
                displayincident.setDescTextField(incidentType.getSelectionModel().getSelectedItem()+ " : " + Description.getText());

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

    }

}
