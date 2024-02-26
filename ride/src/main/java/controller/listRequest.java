package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import services.requestService;
import entities.request;
import javafx.scene.control.TextField;


public class listRequest {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<request> list_request;

    @FXML
    private TableColumn<?, ?> request_endlocation;

    @FXML
    private TableColumn<?, ?> request_id;

    @FXML
    private TableColumn<?, ?> request_seats;

    @FXML
    private TableColumn<?, ?> request_startlocation;

    @FXML
    private TableColumn<?, ?> request_time;
    @FXML
    private TextField requestid_delete;
    @FXML
    private TextField id_update;
requestService requestService = new requestService();
    @FXML
    void back_home(ActionEvent event) throws IOException {
        mainController.loadFXML("/addRequest.fxml");

    }

    @FXML
    void delete_request(ActionEvent event) {
        int id_delete = Integer.parseInt(requestid_delete.getText());

        // Create a confirmation dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Delete request");
        alert.setContentText("Are you sure you want to delete this request?");

        // Show the confirmation dialog
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // If user confirms, proceed with deletion
                requestService.deleteRequest(id_delete);
                refreshlist();
            }
        });

    }

    @FXML
    void update_request(ActionEvent event) {

    }

    @FXML
    void initialize() {
        ObservableList<request> list = FXCollections.observableList(requestService.getallrequest());
        request_id.setCellValueFactory(new PropertyValueFactory<>("Id_request"));
        request_startlocation.setCellValueFactory(new PropertyValueFactory<>("StartLocation"));
        request_endlocation.setCellValueFactory(new PropertyValueFactory<>("EndLocation"));
        request_time.setCellValueFactory(new PropertyValueFactory<>("DepartureTime"));
        request_seats.setCellValueFactory(new PropertyValueFactory<>("Availableseats"));

        list_request.setItems(list);
    }
    public void refreshlist(){
        ObservableList<request> updatedList = FXCollections.observableList(requestService.getallrequest());
        list_request.setItems(updatedList);
    }

}

