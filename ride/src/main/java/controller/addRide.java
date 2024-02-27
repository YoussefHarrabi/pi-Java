package controller;
import javafx.scene.control.Alert;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.control.Alert.AlertType;
import entities.ride;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import services.rideService;

public class addRide {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField ride_endlocation;

    @FXML
    private TextField ride_name;


    @FXML
    private TextField ride_seats;

    @FXML
    private TextField ride_startlocation;

    @FXML
    private TextField ride_time;

    rideService rideService = new rideService();
    @FXML
    void addRider(ActionEvent event) throws IOException, SQLException {
        if(validateInputs()) {
        int seats = Integer.parseInt(ride_seats.getText());
        ride ride = new ride(ride_name.getText(),ride_startlocation.getText(),ride_endlocation.getText(),ride_time.getText(),seats);
        rideService.addEntity(ride);
        listRide l = mainController.loadFXML("/listRide").getController();
        l.refreshlist();
            mainController.loadFXML("/listRide.fxml");
    }}
    private boolean validateInputs() {
        String name = ride_name.getText();
        String startLocation = ride_startlocation.getText();
        String endLocation = ride_endlocation.getText();
        String time = ride_time.getText();
        String seatsText = ride_seats.getText();

        if(name.isEmpty() || startLocation.isEmpty() || endLocation.isEmpty() || time.isEmpty() || seatsText.isEmpty()) {
            showAlert("All fields are required.");
            return false;
        }

        try {
            int seats = Integer.parseInt(seatsText);
            if(seats <= 0) {
                showAlert("Number of seats must be a positive integer.");
                return false;
            }
        } catch(NumberFormatException e) {
            showAlert("Number of seats must be a valid integer.");
            return false;
        }



        return true;
    }
    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    void initialize() {

    }

    public void back_to_list(ActionEvent actionEvent) throws IOException {
        mainController.loadFXML("/listRide.fxml");

    }
}
