package controles;

import java.net.URL;
import java.util.ResourceBundle;

import entities.AlertHelper;
import entities.Injury;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import services.IncidentServices;
import services.InjuryServices;

public class DeleteInjury implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Injury> InjuryToDelete;

    @FXML
    private Button DeleteInjury;
    @FXML
    private Button back;

    @FXML
    void DeleteInjury(ActionEvent event) {

        // Get the selected injury to delete
        Injury selectedInjury = InjuryToDelete.getValue();

        if (selectedInjury == null) {
            // No injury selected, show an error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select an injury to delete.");
            alert.show();
            return;
        }else {
            DeleteInjury.setOnAction(event1 -> {
                String confirmationMessage = "Are you sure you want to delete this injury?";
                boolean confirmed = AlertHelper.showConfirmationAlert(confirmationMessage);
                if (confirmed) {

                    InjuryServices injuryServices = new InjuryServices();
                    try {
                        injuryServices.deleteEntity(selectedInjury);
                        // Show success message after successful deletion
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Injury deleted successfully");
                        alert.show();

                        // Reload the display after deletion
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/displayInjury.fxml"));
                        // Load the new scene or update the existing one as needed

                    } catch (Exception e) {
                        // Show error message if deletion fails
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Error deleting injury: " + e.getMessage());
                        alert.show();
                        e.printStackTrace();
                    }
                } else {
                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setContentText("Delete operation cancelled.");
                    alert2.show();
                }
            });
        }

        // Attempt to delete the selected injury
    }


    @FXML
    void initialize() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        InjuryToDelete.getItems().addAll(new InjuryServices().getAllData());
        back.setOnMouseClicked(event -> {
            Stage primaryStage = (Stage) back.getScene().getWindow(); // Get primaryStage
            Home.switchScene(primaryStage, "/displayInjury.fxml");
        });

    }
}

