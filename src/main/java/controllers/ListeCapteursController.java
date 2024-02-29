package controllers;

import entities.Capteurs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.CapteursServices;

import java.io.IOException;
import java.util.List;

public class ListeCapteursController {
    @FXML
    public Button modifierButton;

    @FXML
    private Button rechercherButton;

    @FXML
    private Button supprimerButton;

    @FXML
    private TableView<Capteurs> capteursTableView;


    @FXML
    private TableColumn<Capteurs, String> nomColumn;

    @FXML
    private TableColumn<Capteurs, String> typeColumn;

    @FXML
    private TableColumn<Capteurs, Float> latitudeColumn;

    @FXML
    private TableColumn<Capteurs, Float> longitudeColumn;

    @FXML
    private TableColumn<Capteurs, String> dateInstallationColumn;

    private final CapteursServices capteursService;





    public ListeCapteursController() {
        this.capteursService = new CapteursServices(); // ou initialisez votre service de la manière appropriée
    }

    @FXML
    public void initialize() {
        // Configurez les colonnes de la TableView ici
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        latitudeColumn.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        longitudeColumn.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        dateInstallationColumn.setCellValueFactory(new PropertyValueFactory<>("dateInstallation"));

        // Ajouter un écouteur de changement de propriété pour la propriété sceneProperty de la TableView
        capteursTableView.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                //ajoutez le CSS
                String css = this.getClass().getResource("/style.css").toExternalForm();
                newScene.getStylesheets().add(css);
            }
        });


        actualiserTableView();
    }




    @FXML
    private void afficherListeCapteurs() {
        // Récupérer la liste des capteurs depuis le service
        List<Capteurs> capteursList = capteursService.getAllData();

        // Définir les capteurs dans la TableView
        capteursTableView.setItems(FXCollections.observableArrayList(capteursList));
    }



    @FXML
    public void supprimerCapteur(ActionEvent event) {
        // Récupérer le capteur sélectionné
        Capteurs capteurSelectionne = capteursTableView.getSelectionModel().getSelectedItem();

        // Vérifier si un capteur est sélectionné avant de supprimer
        if (capteurSelectionne != null) {
            // Appeler la méthode de suppression du service
            capteursService.deleteEntity(capteurSelectionne);

            // Rafraîchir la TableView après la suppression
            capteursTableView.getItems().clear();
            afficherListeCapteurs();
        } else {
            // Afficher un message d'erreur ou demander à l'utilisateur de sélectionner un capteur
            System.out.println("Veuillez sélectionner un capteur à supprimer.");
        }
    }


    @FXML
    public void modifierCapteur() {
        // Récupérer le capteur sélectionné dans la TableView
        Capteurs capteurSelectionne = capteursTableView.getSelectionModel().getSelectedItem();

        if (capteurSelectionne != null) {
            // Ouvrir une nouvelle interface pour la modification
            ouvrirInterfaceModification(capteurSelectionne);

            // Fermer la fenêtre actuelle
            Stage fenetreActuelle = (Stage) capteursTableView.getScene().getWindow();
            fenetreActuelle.close();
        } else {
            // Aucun capteur sélectionné, affichez un message d'erreur ou faites quelque chose d'autre
            System.out.println("Aucun capteur sélectionné pour la modification.");
        }
    }


    @FXML
    private void ouvrirInterfaceModification(Capteurs capteur) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateCapteur.fxml"));
            Parent root = loader.load();

            // Passer le capteur et l'instance de ListeCapteursController à la nouvelle interface de modification
            UpdateCapteur modificationController = loader.getController();
            modificationController.initData(capteur, this);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
            // Gérer l'exception
        }
    }


    @FXML
    void actualiserTableView() {


        // Récupérer la liste des capteurs actuelle
        ObservableList<Capteurs> capteursList = capteursTableView.getItems();

        // Effacer la liste actuelle
        capteursList.clear();

        // Ajouter la nouvelle liste de capteurs (vous devez obtenir la nouvelle liste à partir de votre service)
        afficherListeCapteurs();
    }


    //afficher une boîte de dialogue
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private TextField rechercheTextField;

    @FXML
    private void rechercherCapteurs() {
        // Récupérer le texte de recherche
        String termeRecherche = rechercheTextField.getText().trim();

        // Appeler la méthode de recherche avec le terme spécifié
        List<Capteurs> resultats = capteursService.rechercherParNom(termeRecherche);

        // Imprimer les résultats dans la console pour le débogage
        System.out.println("Résultats de la recherche : " + resultats);

        // Actualiser la TableView avec les résultats de la recherche
        capteursTableView.setItems(FXCollections.observableArrayList(resultats));
        capteursTableView.refresh();
    }


    public void afficherAjoutCapteur(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CapteursController.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage ajoutCapteurStage = new Stage();
            ajoutCapteurStage.setTitle("Add Sonsor");
            ajoutCapteurStage.setScene(scene);

            // Fermer la fenêtre actuelle
            Stage fenetreActuelle = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            fenetreActuelle.close();


            ajoutCapteurStage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}





