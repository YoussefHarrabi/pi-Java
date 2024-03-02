package controles;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import entities.Incident;
import entities.Injury;
import entities.RestCalculator;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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
    private TableColumn<Injury, Integer> RestDays;

    @FXML
    private TextField rechercheTextField;

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

        RestDays.setCellValueFactory(data -> {
            String injuryType = data.getValue().getType(); // Assuming you get the injury type from typeColumn
            String severity = data.getValue().getSeverity(); // Assuming you get the severity from severityColumn

            // Calculate rest days using RestCalculator
            int restDays = RestCalculator.calculateRestDays(injuryType, severity);

            // Wrap restDays in a SimpleIntegerProperty for display
            return new SimpleIntegerProperty(restDays).asObject();
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

    public void Search(ActionEvent actionEvent) {

        String termeRecherche = rechercheTextField.getText().trim();
        InjuryServices inj = new InjuryServices();

        // Appeler la méthode de recherche avec le terme spécifié
        List<Injury> resultats = inj.rechercherParMotCle(termeRecherche);

        // Imprimer les résultats dans la console pour le débogage
        System.out.println("searched result : " + resultats);

        // Actualiser la TableView avec les résultats de la recherche
        InjuryTab.setItems(FXCollections.observableArrayList(resultats));
        InjuryTab.refresh();
    }
}
