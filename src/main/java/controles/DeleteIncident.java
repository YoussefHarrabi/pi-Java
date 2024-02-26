package controles;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entities.AlertHelper;
import entities.Incident;
import entities.Injury;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import services.IncidentServices;

public class DeleteIncident implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Incident> IncidentToDelete;

    @FXML
    private Button DeleteIncident;
    @FXML
    private Button back;

    @FXML
    void initialize() {

    }
    @FXML
    void DeleteIncident(ActionEvent event) {
        Incident selectedInc = IncidentToDelete.getValue();

        if (selectedInc == null) {
            // No injury selected, show an error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select an incident to delete.");
            alert.show();
            return;
        }

        IncidentServices incidentServices = new IncidentServices();
        try {
            incidentServices.deleteEntity(IncidentToDelete.getValue());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Incident deleted successfully");
            alert.show();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/displayIncident.fxml"));



        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IncidentToDelete.getItems().addAll(new IncidentServices().getAllData());
        back.setOnMouseClicked(event -> {
            Stage primaryStage = (Stage) back.getScene().getWindow(); // Get primaryStage
            Home.switchScene(primaryStage, "/displayIncident.fxml");
        });

        DeleteIncident.setOnAction(event -> {
            String confirmationMessage = "Are you sure you want to delete this incident?";
            boolean confirmed = AlertHelper.showConfirmationAlert(confirmationMessage);
            if (confirmed) {
                // Perform the delete operation
                // For example: deleteIncident();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Incident deleted successfully");
                alert.show();

            } else {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setContentText("Delete operation cancelled.");
                alert2.show();
            }
        });
    }
}
