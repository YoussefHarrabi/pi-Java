package controles;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.javafx.charts.Legend;
import entities.Incident;
import entities.Injury;
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
import services.IncidentServices;

public class displayIncident implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AddButton;

    @FXML
    private Button DeleteButton;

    @FXML
    private Button injury;

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
    private TableColumn<Incident, Date> DateColumn;

    @FXML
    private TextField rechercheTextField;
    @FXML
    private void SearchType() {
        // Récupérer le texte de recherche

        String termeRecherche = rechercheTextField.getText().trim();
        IncidentServices inc = new IncidentServices();

        // Appeler la méthode de recherche avec le terme spécifié
        List<Incident> resultats = inc.rechercherParMotCle(termeRecherche);

        // Imprimer les résultats dans la console pour le débogage
        System.out.println("searched result : " + resultats);

        // Actualiser la TableView avec les résultats de la recherche
        IncidentTab.setItems(FXCollections.observableArrayList(resultats));
        IncidentTab.refresh();
    }



    @FXML
    void initialize() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        HourColumn.setCellValueFactory(new PropertyValueFactory<>("Hour"));
        PlaceColumn.setCellValueFactory(new PropertyValueFactory<>("Place"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        // Fetch all injuries from the database
        IncidentServices incidentServices = new IncidentServices();
        List<Incident> incidents = incidentServices.getAllData();

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
        AddButton.setOnMouseClicked(event -> {
            Stage primaryStage = (Stage) IncidentTab.getScene().getWindow(); // Get primaryStage
            Home.switchScene(primaryStage, "/addIncident.fxml");
        });
        DeleteButton.setOnMouseClicked(event -> {
            Stage primaryStage = (Stage) IncidentTab.getScene().getWindow(); // Get primaryStage
            Home.switchScene(primaryStage, "/DeleteIncident.fxml");
        });
        injury.setOnMouseClicked(event -> {
            Stage primaryStage = (Stage) IncidentTab.getScene().getWindow(); // Get primaryStage
            Home.switchScene(primaryStage, "/displayInjury.fxml");
        });




    }


    @FXML
    void AddButton(ActionEvent event) {

    }

    @FXML
    void DeleteButton(ActionEvent event) {

    }
}
