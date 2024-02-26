package controllers;

import entities.Capteurs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import services.CapteursServices;

import java.io.IOException;

public class CapteursController {

    public Button afficherListeButton;
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



    @FXML
    private Button ajouterButton;

    private CapteursServices capteursService;



    public CapteursController() {
        this.capteursService = new CapteursServices(); // ou initialisez votre service de la manière appropriée
    }

    // Méthode pour injecter le service
    public void setCapteursService(CapteursServices capteursService) {
        this.capteursService = capteursService;
    }

    @FXML
    public void ajouterCapteur(ActionEvent event) {
        // Récupérer les valeurs des champs
        String nom = nomField.getText();
        String type = typeField.getText();
        float latitude = Float.parseFloat(latitudeField.getText());
        float longitude = Float.parseFloat(longitudeField.getText());
        String dateInstallation = dateInstallationField.getText();


        // Créer un objet Capteurs avec les valeurs
        Capteurs nouveauCapteur = new Capteurs(nom, type, latitude, longitude,dateInstallation );

        // Appeler la méthode d'ajout du service
        capteursService.addEntity(nouveauCapteur);

        // Effacer les champs après l'ajout
        clearFields();
    }

    @FXML
    public void afficherListeCapteurs(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ListeCapteurs.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Fermer la fenêtre actuelle si nécessaire
            Stage currentStage = (Stage) afficherListeButton.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
            // Gérer l'exception
        }
    }

    private void clearFields() {
        nomField.clear();
        typeField.clear();
        latitudeField.clear();
        longitudeField.clear();
        dateInstallationField.clear();
    }
}
