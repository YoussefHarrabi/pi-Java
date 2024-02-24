package controles;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import entities.Incident;
import entities.Injury;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import services.IncidentServices;
import services.InjuryServices;

import static java.lang.Math.round;

public class AddInjury implements Initializable {

    String [] type = { "Fracture",
            "Sprain",
            "Burn",
            "Cut",
            "Bruise",
            "Concussion",
            "Whiplash",
            "Laceration",
            "Abrasions",
            "Internal bleeding"};

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Incident> incidID;

    @FXML
    private ComboBox<String> Type;

    @FXML
    private Slider severity ;

    String sev;

    @FXML
    private Button AddInjury;



    @FXML
    void AddInjury(ActionEvent event) {


        Injury injury = new Injury(incidID.getSelectionModel().getSelectedItem().getIncidentId(), Type.getSelectionModel().getSelectedItem(),sev);
        InjuryServices injuryServices = new InjuryServices();
        try {
            injuryServices.addEntity(injury);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Injury added successfully");
            alert.show();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/displayInjury.fxml"));

            try {
                Parent root = loader.load();
                displayInjury displayInjury = loader.getController();
                displayInjury.setIncident(incidID.getSelectionModel().getSelectedItem().getDescription());
                displayInjury.setType(Type.getSelectionModel().getSelectedItem());
                displayInjury.setSeverity(sev);

                Type.getScene().setRoot(root);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }

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

        severity.valueProperty().addListener((observable, oldValue, newValue) -> {
            // newValue contains the updated value of the slider
            double sliderValue = newValue.doubleValue();

            if (sliderValue <= 30) {
                sev = "Low";
            } else if (sliderValue <= 60) {
                sev = "Medium";
            } else {
                sev = "High";
            }
        });

        Type.getItems().addAll(type);
        incidID.getItems().addAll(new IncidentServices().getAllData());

    }
}
