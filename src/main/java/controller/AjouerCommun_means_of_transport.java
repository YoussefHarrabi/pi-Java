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
            if (!reg.getText().matches("\\d{3}TU\\d{4}$")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Le champ 'registration_number' doit être au format ***TU**** où les étoiles sont des entiers.");
                alert.show();
                return;
            }

            // Convertir le texte dans reg en entier
            int regist = Integer.parseInt(reg.getText());

            // Vérifier si le même registration number existe déjà
            Commun_means_of_transportServices moyenServices = new Commun_means_of_transportServices();
            if (moyenServices.isRegistrationNumberExists(reg.getText())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("A same registration number already exists.");
                alert.show();
                return;
            }

            // Vérifier le type
            String allowedType1 = "train";
            String allowedType2 = "bus";
            String enteredType = type.getText().toLowerCase();

            if (!allowedType1.equals(enteredType) && !allowedType2.equals(enteredType)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Please enter 'train' or 'bus' for the 'type' field.");
                alert.show();
                return;
            }

            // Créer un nouvel objet Commun_means_of_transport
            Commun_means_of_transport moyen = new Commun_means_of_transport(reg.getText(), enteredType);
            Commun_means_of_transportServices.addEntity2(moyen);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Added successfully");
            alert.show();
        } catch (NumberFormatException e) {
            // Gérer le cas où le texte dans reg n'est pas un entier valide
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter a valid integer for the 'registration number' field.");
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
*/ package controller;

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
            if (!reg.getText().matches("\\d{3}TU\\d{4}$")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Le champ 'registration_number' doit être au format ***TU**** où les étoiles sont des entiers.");
                alert.show();
                return;
            }

            // Convertir le texte dans reg en entier

            // Vérifier si le même registration number existe déjà
            Commun_means_of_transportServices moyenServices = new Commun_means_of_transportServices();
            if (moyenServices.isRegistrationNumberExists(reg.getText())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("A same registration number already exists.");
                alert.show();
                return;
            }

            // Vérifier le type
            String allowedType1 = "train";
            String allowedType2 = "bus";
            String enteredType = type.getText().toLowerCase();

            if (!allowedType1.equals(enteredType) && !allowedType2.equals(enteredType)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Please enter 'train' or 'bus' for the 'type' field.");
                alert.show();
                return;
            }

            // Créer un nouvel objet Commun_means_of_transport
            Commun_means_of_transport moyen = new Commun_means_of_transport(reg.getText(), enteredType);
            Commun_means_of_transportServices.addEntity2(moyen);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Added successfully");
            alert.show();
        } catch (NumberFormatException e) {
            // Laissez cet espace vide, sans afficher d'alerte ou imprimer le message d'erreur
        }
    }

    @FXML
    void initialize() {
    }

    public void showlist(ActionEvent actionEvent) throws IOException {
        Home.loadFXML("/Commun_means_of_transportInfo.fxml");
    }
}
