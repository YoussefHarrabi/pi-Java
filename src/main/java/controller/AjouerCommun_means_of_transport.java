package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import entities.Commun_means_of_transport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.Commun_means_of_transportServices;

public class AjouerCommun_means_of_transport {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField reg;

    @FXML
    private TextField type;

    @FXML
    void addEntity2(ActionEvent event) throws IOException {

       /* Commun_means_of_transport mean = new Commun_means_of_transport(regist, type.getText());
        Commun_means_of_transportServices meanService= new   Commun_means_of_transportServices();

            meanService.addEntity2(mean);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("added !");
            alert.show();
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/Commun_means_of_transportInfo.fxml"));
        try {
            Parent root = loader.load();
            CommunMeansOfTransportInfo meanInfo = loader.getController();
            meanInfo.setReginfo(reg.getText());
            meanInfo.setTypeinfo(type.getText());

            reg.getScene().setRoot(root);




        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/


            try {
                // Convert the text in priceProd to an integer
                int  regist = Integer.parseInt(reg.getText());

                // Create a new Product object using the converted price
                Commun_means_of_transport moyen = new Commun_means_of_transport(regist, type.getText());
                Commun_means_of_transportServices moyenServices= new Commun_means_of_transportServices();
                Commun_means_of_transportServices.addEntity2(moyen);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("successfully added");
                alert.show();
                // Use the 'product' object as needed (e.g., save to a database)
            } catch (NumberFormatException e) {
                // Handle the case where the text in priceProd is not a valid integer
                System.err.println(  e.getMessage());
            }


        // Rest of your code
    }

    @FXML
    void initialize() {

    }

    public void showlist(ActionEvent actionEvent) throws IOException {
        Home.loadFXML("/Commun_means_of_transportInfo.fxml");

    }

}
