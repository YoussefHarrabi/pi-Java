package controllers.Ticket;

import Services.StationServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Ticket implements Initializable {

    @FXML
    private ComboBox<String> transportComboBox;

    @FXML
    private ComboBox<String> fromComboBox;

    @FXML
    private ComboBox<String> toComboBox;

    private final StationServices stationServices = new StationServices();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateComboBoxes();
    }

    private void populateComboBoxes() {
        // Populate transport combo box
        ObservableList<String> transportOptions = FXCollections.observableArrayList("Train", "Bus", "Metro");
        transportComboBox.setItems(transportOptions);

        // Populate from and to combo boxes with station names
        StationServices x = new StationServices();
        List<String> stationNames = x.getAllStationNames();
        ObservableList<String> stationOptions = FXCollections.observableArrayList(stationNames);
        fromComboBox.setItems(stationOptions);
        toComboBox.setItems(stationOptions);
    }

    public void buyTicket(ActionEvent event) {
        // Calculate price based on distance
        String fromStation = fromComboBox.getValue();
        String toStation = toComboBox.getValue();
        String transport = transportComboBox.getValue();
        int distance = stationServices.calculateDistance(fromStation, toStation);
        int price = distance * 2; // Assuming each 2km costs 2 points

        // Load the FXML file for the price interface
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ticket/price.fxml"));
            Parent root = loader.load();

            // Get the controller
            Price priceController = loader.getController();

            // Set the values
            priceController.setPrice(price, transport, fromStation, toStation);

            // Create a new stage for the price interface
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void logout(ActionEvent actionEvent) {
        // Clear any user session data
        // For example:
        // UserSession.clear();

        // Close the current window
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();

        // Navigate to the login screen
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Authentication.fxml"));
            Scene scene = new Scene(root);
            Stage loginStage = new Stage();
            loginStage.setScene(scene);
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Show an alert if there's an error navigating to the login screen
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while logging out. Please try again.");
            alert.showAndWait();
        }
    }



}
