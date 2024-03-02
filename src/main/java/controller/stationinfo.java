package controller;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import services.StationServices;
import services.ticketServices;
import entities.Station;
public class stationinfo {

    @FXML
    private TableView<Station> liststat;

    @FXML
    private TableColumn<?, ?> ID;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private TableColumn<?, ?> address;

    @FXML
    private TableColumn<Station, Void> actions;

    @FXML
    private TextField tckverif;

    @FXML
    StationServices statServices = new StationServices();

    @FXML
    ticketServices tckServices = new ticketServices();

    @FXML
    void initialize() {

        ObservableList<Station> list = FXCollections.observableList(StationServices.getAllData1());
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        address.setCellValueFactory(new PropertyValueFactory<>("Address"));

        liststat.setItems(list);

        actions.setCellFactory(param -> new TableCell<Station, Void>() {

            final javafx.scene.control.Button deleteButton = new javafx.scene.control.Button("Delete");
            final javafx.scene.control.Button updateButton = new javafx.scene.control.Button("Update");

            private void deletestat(Station stat) {
                statServices.deleteEntity(stat);
                liststat.getItems().remove(stat);
            }

            {
                deleteButton.setOnAction((ActionEvent event) -> {
                    Station stat1 = getTableView().getItems().get(getIndex());
                    if (confirmAction("Confirmation", "Voulez-vous vraiment supprimer cette ligne ?")) {
                        deletestat(stat1);
                    }
                });
                // Ajoutez d'autres actions au besoin...
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Station cmt = getTableView().getItems().get(getIndex());
                    if (cmt != null) {
                        HBox buttons = new HBox(deleteButton, updateButton);
                        setGraphic(buttons);
                    }
                }
            }
        });
    }

    @FXML
    void verification(ActionEvent event) {
        try {
            // Récupérer le ticket_id à partir du champ tckverif
            int ticketId = Integer.parseInt(tckverif.getText());

            // Obtenir le temps de départ et la durée du trajet à partir de votre service ou base de données
            // Supposons que vous avez une méthode dans votre service de tickets pour obtenir ces informations
            LocalTime departureTime = tckServices.getDepartureTime(ticketId).toLocalTime();
            LocalTime Traject_duration = tckServices.getTrajetDuration(ticketId).toLocalTime();

            Time duration = tckServices.getTrajetDuration(ticketId);

            // Vérifier si le temps de départ est dans les plages horaires spécifiées
            if ((departureTime.isAfter(LocalTime.of(7, 0)) && departureTime.isBefore(LocalTime.of(9, 0))) ||
                    (departureTime.isAfter(LocalTime.of(13, 0)) && departureTime.isBefore(LocalTime.of(14, 0)))) {
                // Ajuster la durée du trajet
                duration = Time.valueOf(Traject_duration.plusMinutes(15));


                // Afficher une alerte pour informer l'utilisateur du possible retard
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Take in your consideration there will be a delay due to traffic, the new traject time is : "+duration);
                alert.showAndWait();
            }

            // Autres traitements...

        } catch (NumberFormatException e) {
            // Gérer l'exception si le ticket_id n'est pas un entier valide
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez entrer un ticket_id valide.");
            alert.showAndWait();
        }
    }

    private boolean confirmAction(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    public void back_to_add(ActionEvent actionEvent) throws IOException {
        Home.loadFXML("/ajouterstation.fxml");
    }

    // Ajoutez d'autres méthodes au besoin...
}
