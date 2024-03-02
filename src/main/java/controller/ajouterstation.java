/* package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entities.Commun_means_of_transport;
import entities.Station;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import services.StationServices;


import javax.xml.namespace.QName;





 public class ajouterstation {

     @FXML
     private ResourceBundle resources;

     @FXML
     private URL location;

     @FXML
     private TextField Name;

     @FXML
     private TextField Address;

     @FXML
     void ajouterstation (ActionEvent event)  {




             Station s = new Station (Name.getText(), Address.getText());

             StationServices ser = new StationServices();
             StationServices.addEntity2(s);

             Alert alerte = new Alert(Alert.AlertType.INFORMATION);
             alerte.setContentText("Added successfuly");
             alerte.show();



         // Reste de votre code
     }




    @FXML
    void initialize() {

    }

     public void consulter_liste(ActionEvent actionEvent) throws IOException {
         Home.loadFXML("/stationinfo.fxml");

     }

     public void back_to_means_list(ActionEvent actionEvent) throws IOException {
         Home.loadFXML("/Commun_means_of_transportInfo.fxml");

     }
 }
 */

package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import services.StationServices;

import java.io.IOException;

public class ajouterstation {

    @FXML
    private TextField Name;

    @FXML
    private TextField Address;

    @FXML
    void ajouterstation(ActionEvent event) {
        String name = Name.getText();
        String address = Address.getText();

        if (name.isEmpty() || address.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please fill in all fields.");
            alert.show();
            return;
        }

        StationServices ser = new StationServices();
        ser.addEntity2(new entities.Station(name, address));

        Alert alerte = new Alert(Alert.AlertType.INFORMATION);
        alerte.setContentText("Added successfully");
        alerte.show();
    }

    @FXML
    void initialize() {
    }

    public void consulter_liste(ActionEvent actionEvent) throws IOException {
        Home.loadFXML("/stationinfo.fxml");
    }

    public void back_to_means_list(ActionEvent actionEvent) throws IOException {
        Home.loadFXML("/Commun_means_of_transportInfo.fxml");
    }
}