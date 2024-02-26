package controles;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import entities.Incident;
import entities.Injury;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.InjuryServices;

public class displayInjury implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AddBut;

    @FXML
    private Button DeleteBut;

    @FXML
    private Button back;

    @FXML
    private TableView<Injury> InjuryTab;

    @FXML
    private TableColumn<Injury, Integer> incidentIdColumn;

    @FXML
    private TableColumn<Injury, String> typeColumn;

    @FXML
    private TableColumn<Injury, String> severityColumn;

    @FXML
    private TableColumn<Injury, Integer> Numberpers;

    @FXML
    void initialize() {

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set cell value factories for table columns
        incidentIdColumn.setCellValueFactory(new PropertyValueFactory<>("incidentId"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        severityColumn.setCellValueFactory(new PropertyValueFactory<>("severity"));
        Numberpers.setCellValueFactory(new PropertyValueFactory<>("Number_pers"));

        // Fetch all injuries from the database
        InjuryServices injuryServices = new InjuryServices();
        List<Injury> injuries = injuryServices.getAllData();

        // Populate the table with injuries
        InjuryTab.getItems().addAll(injuries);

        // Listener for selecting an item in the table
        InjuryTab.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                int id = newSelection.getId();
                int incidentId = newSelection.getIncidentId();
                String type = newSelection.getType();
                int Number_pers = newSelection.getNumber_pers();
                String severity = newSelection.getSeverity();

                // Switch scene to update injury interface
                Stage primaryStage = (Stage) InjuryTab.getScene().getWindow();
                Home.switchSceneWithDataInjury(primaryStage, "/UpdateInjury.fxml", id, incidentId, type, severity, Number_pers);
            }
        });

        // Event handler for the Add button
       AddBut.setOnMouseClicked(event -> {
            Stage primaryStage = (Stage) InjuryTab.getScene().getWindow();
            Home.switchScene(primaryStage, "/AddInjury.fxml");
        });

        // Event handler for the Delete button
        DeleteBut.setOnMouseClicked(event -> {
            Stage primaryStage = (Stage) InjuryTab.getScene().getWindow();
            Home.switchScene(primaryStage, "/DeleteInjury.fxml");
        });

        back.setOnMouseClicked(event -> {
            Stage primaryStage = (Stage) back.getScene().getWindow(); // Get primaryStage
            Home.switchScene(primaryStage, "/displayIncident.fxml");
        });
    }

}
