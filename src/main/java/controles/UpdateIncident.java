package controles;

import entities.Incident;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.IncidentServices;

public class UpdateIncident {
    @FXML
    private TextField typeTextField;

    @FXML
    private TextField placeTextField;

    @FXML
    private TextField hourTextField;

    @FXML
    private TextField descriptionTextField;
    @FXML
    private Label IncidentId;

    @FXML
    private void updateButtonClicked() {
        String type = typeTextField.getText();
        String place = placeTextField.getText();
        String hour = hourTextField.getText();
        String description = descriptionTextField.getText();

        IncidentServices incidentServices = new IncidentServices();
        Incident incident = new Incident(type,place,hour,description);

        try {
            incidentServices.updateEntity(incident);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Incident updated successfully");
            alert.show();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/displayIncident.fxml"));



        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }
    public void setData(int incidentId, String type, String place, String hour, String description) {
        IncidentId.setText(String.valueOf(incidentId));
        typeTextField.setText(type);
        placeTextField.setText(place);
        hourTextField.setText(hour);
        descriptionTextField.setText(description);
    }

}

