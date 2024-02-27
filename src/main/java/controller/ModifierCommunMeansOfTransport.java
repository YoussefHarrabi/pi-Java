/*package controller;

import java.net.URL;
import java.util.ResourceBundle;

import entities.Commun_means_of_transport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import services.Commun_means_of_transportServices;

public class ModifierCommunMeansOfTransport {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField registration_input;

    @FXML
    private TextField type_input;
    private Commun_means_of_transport cmt;
    Commun_means_of_transportServices cmts = new Commun_means_of_transportServices();
    public void set_transport(Commun_means_of_transport cmt){
        this.cmt = cmt;
        type_input.setText(cmt.getType());
        String reg = String.valueOf(cmt.getRegistration_number());
        registration_input.setText(reg);
    }
    @FXML
    void update(ActionEvent event) {
        cmt.setType(type_input.getText());
        System.out.println(cmt);
        cmts.updateEntity(cmt);
    }

    @FXML
    void initialize() {

    }
}
*/
package controller;

import java.net.URL;
import java.util.ResourceBundle;

import entities.Commun_means_of_transport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import services.Commun_means_of_transportServices;

public class ModifierCommunMeansOfTransport {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField registration_input;

    @FXML
    private TextField type_input;

    private Commun_means_of_transport cmt;
    Commun_means_of_transportServices cmts = new Commun_means_of_transportServices();

    public void set_transport(Commun_means_of_transport cmt) {
        this.cmt = cmt;
        type_input.setText(cmt.getType());
        String reg = String.valueOf(cmt.getRegistration_number());
        registration_input.setText(reg);
    }

    @FXML
    void update(ActionEvent event) {
        String registrationNumberText = registration_input.getText();

        // Vérifier si registrationNumberText est composé de chiffres uniquement
        if (registrationNumberText.matches("\\d{3}TU\\d{4}")) {
            // Convertir en entier uniquement si la vérification réussit
            cmt.setRegistration_number(registrationNumberText);

            cmt.setType(type_input.getText());

            if (!cmts.isRegistrationNumberExists(cmt.getRegistration_number())) {
                cmts.updateEntity(cmt);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Updated successfully");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("A registration number already exists.");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please enter a valid registration number.");
            alert.show();
        }
    }

    @FXML
    void initialize() {

    }
}
