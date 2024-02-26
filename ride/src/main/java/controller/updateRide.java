package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import services.rideService;
import entities.ride;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class updateRide {

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
    private ride ride;

    rideService rideService = new rideService();
    public void setride(ride ride){
        this.ride = ride;
        ride_name.setText(ride.getDriver());
    }


    void retrievedata(ride ride) {
        //int seats = Integer.parseInt(ride_seats.getText());
        this.ride = ride;
        if (ride != null) {
            ride_name.setText(ride.getDriver());
            ride_startlocation.setText(ride.getStartLocation());
            ride_endlocation.setText(ride.getEndLocation());
            ride_time.setText(ride.getDepartureTime());
            String nbr = String.valueOf(ride.getAvailableseats());
            ride_seats.setText(nbr);
        }
    }
    @FXML
    void update_data(ActionEvent event) throws IOException {
        ride.setDriver(ride_name.getText());
        if(validateInputs()) {
        int Seats = Integer.parseInt(ride_seats.getText());
        System.out.println(Seats);
        ride.setAvailableseats(Seats);
        ride.setStartLocation(ride_startlocation.getText());
        ride.setEndLocation(ride_endlocation.getText());
        ride.setDepartureTime(ride_time.getText());
        //String nbr = String.valueOf(ride.getAvailableseats());
        System.out.println(ride);


        rideService.updateEntity(ride, ride.getId_driver());
    }
    }

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



    @FXML
    void back_home(ActionEvent event) throws IOException {
        mainController.loadFXML("/listRide.fxml");

    }
    @FXML
    void addRider(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
