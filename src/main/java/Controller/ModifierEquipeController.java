package Controller;

import Entities.Equipe;
import Entities.Work;
import Services.EquipeDao;
import Services.WorkDAO;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class ModifierEquipeController {

    @FXML
    private TextField numberOfPersonsField;

    @FXML
    private TextField budgetField; // Ajout du TextField pour AppartientA

    @FXML
    private ComboBox<Work> listOfWorks;

    @FXML
    private Button saveButton;

    private Equipe equipeToModify;
    private EquipeDao equipeDao;
    private EquipeListController equipeListController;

    public void setEquipeListController(EquipeListController equipeListController) {
        this.equipeListController = equipeListController;
    }

    @FXML
    private void initialize() {
        equipeDao = new EquipeDao();
        loadWorks();
    }

    public  void setEquipeToModify(Equipe equipe) {
        this.equipeToModify = equipe;

        // Remplir les champs avec les données actuelles de l'équipe
        numberOfPersonsField.setText(String.valueOf(equipe.getNbrPersonne()));
        budgetField.setText(equipe.getRelationTo()); // Remplir le champ AppartientA
        listOfWorks.getSelectionModel().select(equipe.getWorke());
    }

    @FXML
    private void modifierButtonClicked(ActionEvent actionEvent) {
        if (equipeToModify == null) {
            // Gérer le cas où equipeToModify est null
            System.err.println("equipeToModify est null");
            return;
        }

        // Vérifier si tous les champs sont remplis
        if (validateFields()) {
            // Mettre à jour les propriétés de l'équipe
            equipeToModify.setNbrPersonne(new SimpleIntegerProperty(Integer.parseInt(numberOfPersonsField.getText())));
            equipeToModify.setRelationTo(new SimpleStringProperty(budgetField.getText()));
            equipeToModify.setWorke(listOfWorks.getValue());

            // Appeler la méthode de modification dans EquipeDao
            equipeDao.modifierEquipe(equipeToModify);
            // Afficher une alerte de réussite
            showAlert("Équipe modifiée", "L'équipe a été modifiée avec succès.");
            // Modifier l'appel pour passer un objet ActionEvent
            if (equipeListController != null) {
                equipeListController.updateEquipeList();
            }
            // Afficher une alerte de réussite
            showAlert("Équipe modifiée", "L'équipe a été modifiée avec succès.");

            // Fermer la fenêtre de modification
            closeWindow();
        }
    }

    private void loadWorks() {
        WorkDAO workDAO = new WorkDAO();
        List<Work> allWorks = workDAO.getAllWorks();
        listOfWorks.getItems().addAll(allWorks);
    }

    private boolean validateFields() {
        // Vous pouvez ajouter des validations supplémentaires si nécessaire
        return !numberOfPersonsField.getText().isEmpty() && !budgetField.getText().isEmpty() && listOfWorks.getValue() != null;
    }

    private void closeWindow() {
        // Récupérer la scène actuelle et la fermer
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
