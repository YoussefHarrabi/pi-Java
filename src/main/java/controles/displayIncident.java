package controles;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import entities.Incident;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.IncidentServices;

public class displayIncident implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField placeTextField;

    @FXML
    private TextField hourTextField;

    @FXML
    private TextField descTextField;
    @FXML
    private TableView<Incident> IncidentTab;

    @FXML
    private TableColumn<Incident, String> DescriptionColumn;

    @FXML
    private TableColumn<Incident, String> HourColumn;

    @FXML
    private TableColumn<Incident, String> PlaceColumn;
    @FXML
    private TableColumn<Incident, String> typeColumn;


    @FXML
    void initialize() {

    }
    public void setPlaceTextField(String placeTextField) {
        this.placeTextField.setText(placeTextField);
    }

    public void setHourTextField(String hourTextField) {
        this.hourTextField.setText(hourTextField);
    }

    public void setDescTextField(String descTextField) {
        this.descTextField.setText(descTextField);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        HourColumn.setCellValueFactory(new PropertyValueFactory<>("Hour"));
        PlaceColumn.setCellValueFactory(new PropertyValueFactory<>("Place"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
        // Fetch all injuries from the database
        IncidentServices injuryServices = new IncidentServices();
        List<Incident> incidents = injuryServices.getAllData();




        // Populate the table with injuries
        IncidentTab.getItems().addAll(incidents);

        // Listen for selection changes in the TableView
        IncidentTab.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                int IncidentId = newSelection.getIncidentId();
                String type = newSelection.getType();
                String place = newSelection.getPlace();
                String hour = newSelection.getHour();
                String description = newSelection.getDescription();

                // Now, you can switch to the UpdateIncident interface and set the data
                IncidentTab.setOnMouseClicked(event -> {
                    Stage primaryStage = (Stage) IncidentTab.getScene().getWindow(); // Get primaryStage
                    Home.switchSceneWithData(primaryStage, "/UpdateIncident.fxml",IncidentId, type, place, hour, description);
                });
            }
        });




    }
}
