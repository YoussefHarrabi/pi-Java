package controllers.Ticket;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Price {
    @FXML
    private Label transportLabel;

    @FXML
    private Label fromStationLabel;

    @FXML
    private Label toStationLabel;

    @FXML
    private Label priceLabel;

    public void setPrice(int price, String transport, String fromStation, String toStation) {
        transportLabel.setText(transport);
        fromStationLabel.setText(fromStation);
        toStationLabel.setText(toStation);
        priceLabel.setText(String.valueOf(price)+" points");
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
