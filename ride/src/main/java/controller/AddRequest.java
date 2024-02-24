package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddRequest {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField request_departureTime;

    @FXML
    private TextField request_endLocation;

    @FXML
    private TextField request_seats;

    @FXML
    private TextField request_startLocation;

    @FXML
    void add_request(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert request_departureTime != null : "fx:id=\"request_departureTime\" was not injected: check your FXML file 'addRequest.fxml'.";
        assert request_endLocation != null : "fx:id=\"request_endLocation\" was not injected: check your FXML file 'addRequest.fxml'.";
        assert request_seats != null : "fx:id=\"request_seats\" was not injected: check your FXML file 'addRequest.fxml'.";
        assert request_startLocation != null : "fx:id=\"request_startLocation\" was not injected: check your FXML file 'addRequest.fxml'.";

    }

}
