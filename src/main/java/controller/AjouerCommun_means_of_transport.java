/* package controller;

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
   */





/*package controller;

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
        try {
            // Vérifier si les champs ne sont pas vides
            if (reg.getText().isEmpty() || type.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Veuillez remplir tous les champs.");
                alert.show();
                return;
            }

            // Vérifier le format spécifique pour le champ "regist"
            if (!reg.getText().matches("\\d{3}TU\\d{4}$")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Le champ 'registration_number' doit être au format ***TU**** où les étoiles sont des entiers.");
                alert.show();
                return;
            }

            // Convertir le texte dans reg en entier
            int regist = Integer.parseInt(reg.getText());

            // Créer un nouvel objet Commun_means_of_transport en utilisant le prix converti
            Commun_means_of_transport moyen = new Commun_means_of_transport(regist, type.getText());
            Commun_means_of_transportServices moyenServices = new Commun_means_of_transportServices();
            Commun_means_of_transportServices.addEntity2(moyen);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Ajouté avec succès");
            alert.show();
        } catch (NumberFormatException e) {
            // Gérer le cas où le texte dans reg n'est pas un entier valide
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Le champ 'regist' doit être un entier.");
            alert.show();
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void initialize() {

    }

    public void showlist(ActionEvent actionEvent) throws IOException {
        Home.loadFXML("/Commun_means_of_transportInfo.fxml");
    }
}
*/



/*package controller;

import entities.Commun_means_of_transport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import services.Commun_means_of_transportServices;

import java.io.IOException;

public class AjouerCommun_means_of_transport {

    @FXML
    private TextField reg;

    @FXML
    private TextField type;

    @FXML
    void addEntity2(ActionEvent event) throws IOException {
        try {
            // Vérifier si les champs ne sont pas vides
            if (reg.getText().isEmpty() || type.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Please you need to complete all fields.");
                alert.show();
                return;
            }

            // Vérifier le format spécifique pour le champ "regist"
           /* if (!reg.getText().matches("\\d{3}TU\\d{4}$")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Le champ 'registration_number' doit être au format ***TU**** où les étoiles sont des entiers.");
                alert.show();
                return;
            }   */

            // Convertir le texte dans reg en entier
      /*      int regist = Integer.parseInt(reg.getText());

            // Vérifier si le même registration number existe déjà
            Commun_means_of_transportServices moyenServices = new Commun_means_of_transportServices();
            if (moyenServices.isRegistrationNumberExists(regist)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("A same registration number already exists .");
                alert.show();
                return;
            }

            // Créer un nouvel objet Commun_means_of_transport en utilisant le prix converti
            Commun_means_of_transport moyen = new Commun_means_of_transport(regist, type.getText());
            Commun_means_of_transportServices.addEntity2(moyen);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Added successfully");
            alert.show();
        } catch (NumberFormatException e) {
            // Gérer le cas où le texte dans reg n'est pas un entier valide
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please this field must be un integer.");
            alert.show();
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void initialize() {
    }

    public void showlist(ActionEvent actionEvent) throws IOException {
        Home.loadFXML("/Commun_means_of_transportInfo.fxml");
    }
} */

package controller;

import entities.Commun_means_of_transport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import services.Commun_means_of_transportServices;

import java.io.IOException;

public class AjouerCommun_means_of_transport {

    @FXML
    private TextField reg;

    @FXML
    private TextField type;

    @FXML
    void addEntity2(ActionEvent event) throws IOException {
        try {
            // Vérifier si les champs ne sont pas vides
            if (reg.getText().isEmpty() || type.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Please you need to complete all fields.");
                alert.show();
                return;
            }

            // Vérifier le format spécifique pour le champ "regist"
            int regist;
            try {
                regist = Integer.parseInt(reg.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Please enter a valid integer for the 'registration number' field.");
                alert.show();
                return;
            }

            // Vérifier si le même registration number existe déjà
            Commun_means_of_transportServices moyenServices = new Commun_means_of_transportServices();
            if (moyenServices.isRegistrationNumberExists(regist)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("A same registration number already exists.");
                alert.show();
                return;
            }

            // Vérifier le type
            String allowedType1 = "train";
            String allowedType2 = "bus";
            String enteredType = type.getText().toLowerCase();  // Convertir en minuscules pour la comparaison insensible à la casse

            if (!allowedType1.equals(enteredType) && !allowedType2.equals(enteredType)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Please enter 'train' or 'bus' for the 'type' field.");
                alert.show();
                return;
            }

            // Créer un nouvel objet Commun_means_of_transport en utilisant le prix converti
            Commun_means_of_transport moyen = new Commun_means_of_transport(regist, enteredType);
            Commun_means_of_transportServices.addEntity2(moyen);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Added successfully");
            alert.show();
        } catch (NumberFormatException e) {
            // Gérer le cas où le texte dans reg n'est pas un entier valide
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please this field must be an integer.");
            alert.show();
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void initialize() {
    }

    public void showlist(ActionEvent actionEvent) throws IOException {
        Home.loadFXML("/Commun_means_of_transportInfo.fxml");
    }
}
