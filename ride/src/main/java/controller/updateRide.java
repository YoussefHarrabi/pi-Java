package controller;

import java.net.URL;
import java.util.ResourceBundle;

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
    public void setride(ride ride){
        this.ride = ride;
        ride_name.setText(ride.getDriver());
    }
    @FXML
    void addRider(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }

}
