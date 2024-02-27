package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.scene.control.*;
import entities.ride;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import services.rideService;

public class listRide {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TableView<ride> list_ride;
    @FXML
    private TextField rideid_delete;
    @FXML
    private TableColumn<ride, String> ride_drivername;
    @FXML
    private TableColumn<ride, String> ride_endlocation;
    @FXML
    private TableColumn<ride, Integer> ride_id;
    @FXML
    private TableColumn<ride, Integer> ride_seats;
    @FXML
    private TableColumn<Integer, String> ride_startlocation;
    @FXML
    private TableColumn<ride, String> ride_time;
    @FXML
    private TextField id_update;


    rideService rideService = new rideService();

    @FXML
    void go_to_addRide(ActionEvent event) throws IOException {
        mainController.loadFXML("/addRide.fxml");
    }
    @FXML
    void go_to_AddRequest(ActionEvent event) throws IOException{
        mainController.loadFXML("/addRequest.fxml");
    }

    @FXML
    void initialize() {
        ObservableList<ride> list = FXCollections.observableList(rideService.getallData());
        //ride_id.setCellValueFactory(new PropertyValueFactory<>("Id_driver"));
        ride_drivername.setCellValueFactory(new PropertyValueFactory<>("Driver"));
        ride_startlocation.setCellValueFactory(new PropertyValueFactory<>("StartLocation"));
        ride_endlocation.setCellValueFactory(new PropertyValueFactory<>("EndLocation"));
        ride_time.setCellValueFactory(new PropertyValueFactory<>("DepartureTime"));
        ride_seats.setCellValueFactory(new PropertyValueFactory<>("Availableseats"));
        list_ride.setItems(list);

        TableColumn<ride, Void> actionButtonColumn = new TableColumn<>("Actions");
        actionButtonColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");
            private final Button updateButton = new Button("Update");

            {
                deleteButton.setOnAction(event -> {
                    ride ride = getTableView().getItems().get(getIndex());

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Delete Ride");
                    alert.setContentText("Are you sure you want to delete this ride?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        rideService.deleteEntity(ride.getId_driver());
                        list_ride.getItems().remove(ride);
                    }
                });

                updateButton.setOnAction(event -> {
                    ride ride = getTableView().getItems().get(getIndex());
                    try {
                        FXMLLoader loader = mainController.loadFXML("/updateRide.fxml");
                        updateRide updateController = loader.getController();
                        updateController.retrievedata(ride);
                        System.out.println("Selected");

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    // Implement the logic to navigate to the UpdateNews window

                });

            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox buttons = new HBox(deleteButton, updateButton);
                    setGraphic(buttons);
                }
            }
        });

        list_ride.getColumns().add(actionButtonColumn);
    }
    public void refreshlist(){
        ObservableList<ride> updatedList = FXCollections.observableList(rideService.getallData());
        list_ride.setItems(updatedList);
    }

    public void update_ride(ActionEvent actionEvent) throws IOException {
            int id_update1 = Integer.parseInt(id_update.getText());
            ride ride = rideService.getride(id_update1);
            FXMLLoader loader = mainController.loadFXML("/updateRide.fxml");
            updateRide updateController = loader.getController();

            updateController.setride(ride);

    }


}
