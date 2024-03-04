package controller;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Optional;

import entities.SMSsender;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
    private TextField searchField;


    @FXML
    StationServices statServices = new StationServices();

    @FXML
    ticketServices tckServices = new ticketServices();

    @FXML
    private TextField TelField;

    @FXML
    void initialize() {


        ObservableList<Station> list = FXCollections.observableList(StationServices.getAllData1());
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        address.setCellValueFactory(new PropertyValueFactory<>("Address"));

        liststat.setItems(list);

        actions.setCellFactory(param -> new TableCell<Station, Void>() {

            final Button deleteButton = new Button("Delete");
            final Button updateButton = new Button("Update");

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
        FilteredList<Station> filteredStations = new FilteredList<>(FXCollections.observableList(StationServices.getAllData1()), p -> true);


        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredStations.setPredicate(st -> {       System.out.println("Name: " + st.getName() + ", Address: " + st.getAddress());
                System.out.println("Filter: " + newValue);
                // If filter text is empty, display all users.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare user name with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (st.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches name.
                } else if (st.getAddress().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches surname.
                }
                return false; // Does not match.
            });
        });

        // Wrap the FilteredList in a SortedList.
        SortedList<Station> sortedData = new SortedList<>(filteredStations);

        // Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(liststat.comparatorProperty());

        // Add sorted (and filtered) data to the table.
        liststat.setItems(sortedData);


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


                /* Afficher une alerte pour informer l'utilisateur du possible retard
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Take in your consideration there will be a delay due to traffic, the new traject time is : "+duration);
                alert.showAndWait(); */
                String tel1 = TelField.getText();
               // String messageBody = "Votre message dynamique ici"+duration;

                // Appeler la méthode SMSSender de SmsSender avec le numéro de téléphone récupéré
               // SMSsender.sendSMS(tel1);

                // Créer une instance de SMSsender en fournissant le corps du message
                SMSsender smsSender = new SMSsender("Take in your consideration there will be a delay due to traffic, the new traject time is"+duration);

                // Appeler la méthode sendSMS sur l'instance créée avec le numéro de téléphone récupéré
                smsSender.sendSMS(tel1);
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

    public void openmaps(ActionEvent actionEvent) throws IOException {
        Home.loadFXML("/map.fxml");
    }

    @FXML
    void searchStation(ActionEvent actionEvent) {
        String searchTerm = searchField.getText().trim();
        if (!searchTerm.isEmpty()) {
            ObservableList<Station> filteredList = FXCollections.observableArrayList();

            for (Station station : liststat.getItems()) {
                if (station.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                    filteredList.add(station);
                }
            }

            liststat.setItems(filteredList);
        } else {
            // If the search field is empty, display all stations
            liststat.setItems(FXCollections.observableList(StationServices.getAllData1()));
        }
    }

    // Ajoutez d'autres méthodes au besoin...


}
