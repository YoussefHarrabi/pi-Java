package controllers;

import entities.Capteurs;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.CapteursServices;

import java.awt.*;

public class UpdateCapteur {
    public Button sauvegarderButton;
    @FXML
    private TextField nomField;

    @FXML
    private TextField typeField;

    @FXML
    private TextField latitudeField;

    @FXML
    private TextField longitudeField;

    @FXML
    private TextField dateInstallationField;



    private Capteurs capteurAModifier;
    private ListeCapteursController listeCapteursController;

    public void initData(Capteurs capteur, ListeCapteursController listeCapteursController) {
        this.capteurAModifier = capteur;
        this.listeCapteursController = listeCapteursController;

        // Initialiser les champs avec les valeurs actuelles du capteur
        nomField.setText(capteur.getNom());
        typeField.setText(capteur.getType());
        latitudeField.setText(String.valueOf(capteur.getLatitude()));
        longitudeField.setText(String.valueOf(capteur.getLongitude()));
        dateInstallationField.setText(capteur.getDateInstallation());
    }



    @FXML
    private void sauvegarderModification() {
        listeCapteursController.actualiserTableView();

        // Mettez à jour le capteur avec les nouvelles valeurs
        capteurAModifier.setNom(nomField.getText());
        capteurAModifier.setType(typeField.getText());
        capteurAModifier.setLatitude(Float.parseFloat(latitudeField.getText()));
        capteurAModifier.setLongitude(Float.parseFloat(longitudeField.getText()));
        capteurAModifier.setDateInstallation(dateInstallationField.getText());

        // Enregistrez les modifications dans votre service
        CapteursServices capteursServices = new CapteursServices();
        capteursServices.updateEntity(capteurAModifier);

        listeCapteursController.actualiserTableView();

        // Fermez la fenêtre de modification
        Stage stage = (Stage) nomField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void initialize() {
        // Autre initialisation

        // Récupérer la scène
        Scene scene = nomField.getScene();

        // Vérifier si la scène est nulle
        if (scene != null) {
            // La scène n'est pas nulle, ajouter le CSS au contrôleur
            String css = getClass().getResource("/style.css").toExternalForm();
            scene.getStylesheets().add(css);
        } else {
            // La scène est nulle, ajouter un écouteur pour le changement de propriété
            nomField.sceneProperty().addListener((observable, oldScene, newScene) -> {
                if (newScene != null) {
                    // La nouvelle scène est disponible, ajouter le CSS
                    String css = getClass().getResource("/style.css").toExternalForm();
                    newScene.getStylesheets().add(css);
                }
            });
        }
    }


}
