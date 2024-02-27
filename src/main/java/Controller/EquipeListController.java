package Controller;

import Entities.Equipe;
import Entities.Work;
import Services.EquipeDao;
import Services.WorkDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;  // Importez la classe ActionEvent

import java.io.IOException;
import java.util.EventObject;
import java.util.List;
import java.util.Optional;

public class EquipeListController {

    @FXML
    private TableView<Equipe> equipeTable;

    @FXML
    private TableColumn<Equipe, String> relationToColumn;

    @FXML
    private TableColumn<Equipe, Integer> nbrPersonneColumn;

    @FXML
    private TableColumn<Equipe, String> workColumn;

    private EquipeDao equipeDao;

    public EquipeListController() {
        this.equipeDao = new EquipeDao();
    }

    @FXML
    private void initialize() {
        initializeColumns();
        loadEquipes();
    }

    private void initializeColumns() {
        relationToColumn.setCellValueFactory(cellData -> cellData.getValue().relationToProperty());
        nbrPersonneColumn.setCellValueFactory(cellData -> cellData.getValue().nbrPersonneProperty().asObject());
        workColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getWorke().getLocation()));
    }


    private void loadEquipes() {
        List<Equipe> allEquipes = equipeDao.getAllEquipes();
        ObservableList<Equipe> equipeObservableList = FXCollections.observableArrayList(allEquipes);
        equipeTable.setItems(equipeObservableList);
    }
    @FXML
    void addWorkButtonClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterEquipe.fxml"));
            Parent root = loader.load();

            AjouterEquipeController ajouterWorkController = loader.getController();
            // Ajoutez d'autres initialisations si nécessaire

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add Team");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            // Actualisez la liste des travaux après l'ajout
            refreshTable();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void showModifyEquipeDialog(Equipe equipe) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierEquipe.fxml"));
            Parent root = loader.load();

            // Accédez au contrôleur de l'interface de modification d'équipe
            ModifierEquipeController modifierEquipeController = loader.getController();

            // Passez l'équipe à modifier au contrôleur de l'interface de modification
            modifierEquipeController.setEquipeToModify(equipe);
            modifierEquipeController.setEquipeListController(this);

            // Créez une nouvelle scène
            Scene scene = new Scene(root);

            // Créez une nouvelle fenêtre pour l'interface de modification d'équipe
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Modify Equipe");

            // Affichez la fenêtre de modification d'équipe de manière modale (bloque l'accès aux autres fenêtres)
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer l'exception (journalisation, message utilisateur, etc.)
            showAlert("Erreur", "Erreur lors de l'ouverture de la fenêtre de modification.");
        }
    }
    public void handleModifierButton(ActionEvent actionEvent) {
        // Récupérez l'équipe que vous souhaitez modifier depuis la TableView
        Equipe equipeAModifier = equipeTable.getSelectionModel().getSelectedItem();

        // Vérifiez si une équipe a été sélectionnée
        if (equipeAModifier != null) {
            // Utilisez la nouvelle méthode pour afficher la fenêtre de modification d'équipe
            showModifyEquipeDialog(equipeAModifier);
        } else {
            // Aucune équipe sélectionnée, affichez un message d'alerte
            showAlert("Aucune équipe sélectionnée", "Veuillez sélectionner une équipe à modifier.");
        }
    }


// Méthode utilitaire pour afficher une alerte
private void showAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
}

    public void updateEquipeList() {
        loadEquipes();
    }
    private boolean showConfirmationDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    /*private void showModifyWorkDialog(Work work) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/EquipeList.fxml"));

            Parent root = loader.load();

            // Accédez au contrôleur de l'interface de modification
            ModifierWorkController modifierWorkController = loader.getController();

            // Passez le travail à modifier au contrôleur de l'interface de modification
            modifierWorkController.setWork(work);

            // Créez une nouvelle scène
            Scene scene = new Scene(root);

            // Créez une nouvelle fenêtre pour l'interface de modification
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Modify Work");

            // Affichez la fenêtre de modification
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


    @FXML
    void supprimerButtonClicked(ActionEvent event) {
        Equipe selectedWork = equipeTable.getSelectionModel().getSelectedItem();
        if (selectedWork != null) {
            if (showConfirmationDialog("Delete Work", "Are you sure you want to delete the selected work?")) {
                try {
                    equipeDao.deleteTeam(selectedWork.getId());
                    refreshTable();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } else {
            showAlert("No work selected", "Please select a work to delete.");
        }
    }
    private void refreshTable() {
        try {
            List<Equipe> allWorks = equipeDao.getAllEquipes();
            ObservableList<Equipe> equipeObservableList = FXCollections.observableArrayList(allWorks);
            equipeTable.setItems(equipeObservableList);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void showWorkButtonClicked(ActionEvent actionEvent) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/WorkList.fxml"));
        Parent root = loader.load();

        WorkListController ajouterWorkController = loader.getController();
        // Ajoutez d'autres initialisations si nécessaire

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add Team");
        stage.setScene(new Scene(root));
        stage.showAndWait();

        // Actualisez la liste des travaux après l'ajout
        refreshTable();

    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}
