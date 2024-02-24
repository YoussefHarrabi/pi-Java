package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import entities.request;
import entities.ride;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import services.requestService;
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
requestService requestService = new requestService();
    @FXML
    void add_request(ActionEvent event)throws IOException {
        if(validateInputs()) {
            int seats = Integer.parseInt(request_seats.getText());
            request request = new request(request_startLocation.getText(),request_endLocation.getText(),request_departureTime.getText(),seats);
            requestService.addRequest(request);
            listRide l = mainController.loadFXML("/listRequest").getController();
            l.refreshlist();

        }}
    @FXML
    void go_back(ActionEvent event) throws IOException {
        mainController.loadFXML("/listRide.fxml");
    }

    @FXML
    void list_request(ActionEvent event) throws IOException {
        mainController.loadFXML("/listRequest.fxml");
    }


    private boolean validateInputs() {
        String startLocation = request_startLocation.getText();
        String endLocation = request_endLocation.getText();
        String time = request_departureTime.getText();
        String seatsText = request_seats.getText();

        if( startLocation.isEmpty() || endLocation.isEmpty() || time.isEmpty() || seatsText.isEmpty()) {
            showAlert("All fields are required.");
            return false;
        }

        try {
            int seats = Integer.parseInt(seatsText);
            if(seats <= 0 || seats >= 8 ) {
                showAlert("Number of seats must be a positive integer and <8.");
                return false;
            }
        } catch(NumberFormatException e) {
            showAlert("Number of seats must be a valid integer.");
            return false;
        }



        return true;
    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void initialize() {
        assert request_departureTime != null : "fx:id=\"request_departureTime\" was not injected: check your FXML file 'addRequest.fxml'.";
        assert request_endLocation != null : "fx:id=\"request_endLocation\" was not injected: check your FXML file 'addRequest.fxml'.";
        assert request_seats != null : "fx:id=\"request_seats\" was not injected: check your FXML file 'addRequest.fxml'.";
        assert request_startLocation != null : "fx:id=\"request_startLocation\" was not injected: check your FXML file 'addRequest.fxml'.";

    }

}
