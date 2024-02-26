package entities;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertHelper {
    public static boolean showConfirmationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(message);

        // Add OK and Cancel buttons to the alert
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        // Show the alert and wait for the user's response
        alert.showAndWait();

        // Return true if the user clicks OK, false otherwise
        return alert.getResult() == ButtonType.YES;
    }
}
