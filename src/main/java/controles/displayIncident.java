package controles;

import java.net.URL;
import java.sql.Time;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import com.sun.javafx.charts.Legend;
import entities.Incident;
import entities.Injury;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
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
    private TableColumn<Incident, Button> report;

    @FXML
    private Button charts;

    @FXML
    private TextField rechercheTextField;
    private Map<Incident, Integer> reportCounts = new HashMap<>();
    private final String[] validReasons = {"There is no incident there","False information"};

    private Timer timer;

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
        report.setCellFactory(column -> {
            return new TableCell<>() {
                private final Button reportButton = new Button("Report");

                {
                    reportButton.setOnAction(event -> {
                        Incident incident = getTableView().getItems().get(getIndex());
                        // Handle report button click here
                        handleReportButton(incident);
                    });
                }

                @Override
                protected void updateItem(Button item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(reportButton);
                    }
                }
            };
        });
        // Fetch all injuries from the database
        IncidentServices incidentServices = new IncidentServices();
        List<Incident> incidents = incidentServices.getAllData();

        // Initialize the report counts for each incident to zero
        incidents.forEach(incident -> reportCounts.put(incident, 0));

        // Populate the table with incidents
        IncidentTab.setItems(FXCollections.observableArrayList(incidents));


        // Listen for selection changes in the TableView
        IncidentTab.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                int IncidentId = newSelection.getIncidentId();
                String type = newSelection.getType();
                String place = newSelection.getPlace();
                Time hour = newSelection.getHour();
                String description = newSelection.getDescription();

                // Now, you can switch to the UpdateIncident interface and set the data
                IncidentTab.setOnMouseClicked(event -> {
                    Stage primaryStage = (Stage) IncidentTab.getScene().getWindow(); // Get primaryStage
                    Home.switchSceneWithData(primaryStage, "/UpdateIncident.fxml",IncidentId, type, place, String.valueOf(hour), description);
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
        charts.setOnMouseClicked(event -> {
            Stage primaryStage = (Stage) IncidentTab.getScene().getWindow(); // Get primaryStage
            Home.switchScene(primaryStage, "/Charts.fxml");
        });

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateDisplayedIncidents();
            }
        }, 0, 30 * 60 * 1000); // 30 minutes in milliseconds
    }
    private void updateDisplayedIncidents() {
        // Get the current time
        Time currentTime = Time.valueOf(LocalTime.now());

        // Filter out incidents that are within 30 minutes of the current time
        List<Incident> displayedIncidents = IncidentTab.getItems().stream()
                .filter(incident -> isWithin30Minutes(incident.getHour(), currentTime))
                .collect(Collectors.toList());

        // Update the TableView with the filtered incidents
        Platform.runLater(() -> {
            IncidentTab.setItems(FXCollections.observableArrayList(displayedIncidents));
        });
    }

    private boolean isWithin30Minutes(Time incidentTime, Time currentTime) {
        long thirtyMinutesInMillis = 30 * 60 * 1000; // 30 minutes in milliseconds
        long incidentTimeMillis = incidentTime.getTime();
        long currentTimeMillis = currentTime.getTime();
        return currentTimeMillis - incidentTimeMillis <= thirtyMinutesInMillis;
    }


    @FXML
    void AddButton(ActionEvent event) {

    }

    @FXML
    void DeleteButton(ActionEvent event) {

    }
    private void handleReportButton(Incident incident) {
        ComboBox<String> reasonComboBox = new ComboBox<>(FXCollections.observableArrayList(validReasons));
        reasonComboBox.getSelectionModel().selectFirst();

// Create a GridPane to hold the ComboBox
        GridPane grid = new GridPane();
        grid.add(reasonComboBox, 0, 0);

// Show an alert with the ComboBox to select the reason
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Report Incident");
        alert.setHeaderText("Select a reason for reporting:");
        alert.getDialogPane().setContent(grid);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                String selectedReason = reasonComboBox.getValue();
                // Check if a reason was selected
                if (selectedReason != null) {
                    // Proceed with reporting the incident
                    reportIncident(incident, selectedReason);
                } else {
                    // Display an error message for not selecting a reason
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("Please select a reason for reporting.");
                    errorAlert.showAndWait();
                }
            }
        });
    }
    // Method to check if a reason is valid


    // Method to report an incident
    private void reportIncident(Incident incident, String reason) {
        // Increment the report count for the incident
        int count = reportCounts.get(incident) + 1;
        reportCounts.put(incident, count);

        // If the report count reaches three, delete the incident
        if (count == 3) {
            deleteIncident(incident);
        }
        // Perform other actions for reporting the incident, such as logging the reason.
    }
    // Method to delete an incident
    private void deleteIncident(Incident incident) {
        IncidentServices incidentServices = new IncidentServices();
        incidentServices.deleteEntity(incident);

        // Remove the incident from the TableView
        IncidentTab.getItems().remove(incident);
    }


}
