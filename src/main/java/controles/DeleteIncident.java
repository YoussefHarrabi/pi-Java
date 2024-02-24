package controles;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entities.Incident;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    void initialize() {

    }
    @FXML
    void DeleteIncident(ActionEvent event) {
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
    }
}
