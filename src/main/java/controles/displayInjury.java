package controles;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import entities.Injury;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.InjuryServices;

public class displayInjury implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Incident;

    @FXML
    private TextField type;

    @FXML
    private TextField severity;
    @FXML
    private TableView<Injury> InjuryTab;
    @FXML
    private TableColumn<Injury, Integer> incidentIdColumn;

    @FXML
    private TableColumn<Injury, String> typeColumn;

    @FXML
    private TableColumn<Injury, String> severityColumn;



    @FXML
    void initialize() {

    }

    public void setIncident(String incident) {
        this.Incident.setText(incident);
    }

    public void setType(String type) {
        this.type.setText(type);
    }

    public void setSeverity(String severity) {
        this.severity.setText(severity);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        incidentIdColumn.setCellValueFactory(new PropertyValueFactory<>("incidentId"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        severityColumn.setCellValueFactory(new PropertyValueFactory<>("severity"));

        // Fetch all injuries from the database
        InjuryServices injuryServices = new InjuryServices();
        List<Injury> injuries = injuryServices.getAllData();
       /* for (Injury i :injuries
             ) {
            String inci = i.getType();
        }*/


        // Populate the table with injuries
        InjuryTab.getItems().addAll(injuries);
    }
}
