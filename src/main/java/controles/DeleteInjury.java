package controles;

import java.net.URL;
import java.util.ResourceBundle;

import entities.Injury;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    void DeleteInjury(ActionEvent event) {
        InjuryServices injuryServices = new InjuryServices();
        try {
            injuryServices.deleteEntity(InjuryToDelete.getValue());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Injury deleted successfully");
            alert.show();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/displayInjury.fxml"));



        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }

    }

    @FXML
    void initialize() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        InjuryToDelete.getItems().addAll(new InjuryServices().getAllData());

    }
}

