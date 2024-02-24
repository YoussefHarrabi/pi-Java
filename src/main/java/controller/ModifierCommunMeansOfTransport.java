package controller;

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
        cmt.setRegistration_number(Integer.parseInt(registration_input.getText()));
        cmts.updateEntity(cmt);
    }

    @FXML
    void initialize() {

    }
}
