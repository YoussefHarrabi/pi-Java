package controllers;

import entities.Capteurs;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
        String latitudeText = latitudeField.getText();
        String longitudeText = longitudeField.getText();
        String dateInstallation = dateInstallationField.getText();

        // Vérifier si les champs sont vides
        if (nom.isEmpty() || type.isEmpty() || latitudeText.isEmpty() || longitudeText.isEmpty() || dateInstallation.isEmpty()) {
            // Afficher une alerte indiquant que tous les champs doivent être remplis
            afficherNotification("Veuillez remplir tous les champs.");
            return; // Sortir de la méthode si des champs sont vides
        }

        // Convertir les champs de latitude et de longitude en nombres flottants
        float latitude, longitude;
        try {
            latitude = Float.parseFloat(latitudeText);
            longitude = Float.parseFloat(longitudeText);
        } catch (NumberFormatException e) {
            // Afficher une alerte indiquant que les valeurs de latitude et de longitude doivent être des nombres valides
            afficherNotification("Les valeurs de latitude et de longitude doivent être des nombres valides.");
            return; // Sortir de la méthode en cas d'erreur de conversion
        }

        // Créer un objet Capteurs avec les valeurs
        Capteurs nouveauCapteur = new Capteurs(nom, type, latitude, longitude, dateInstallation);

        // Appeler la méthode d'ajout du service
        capteursService.addEntity(nouveauCapteur);

        // Effacer les champs après l'ajout
        clearFields();
    }

    @FXML
    public void afficherNotification(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notification");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
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
