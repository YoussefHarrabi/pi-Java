package Controller;

import Entities.Equipe;
import Entities.Work;
import Services.EquipeDao;
import Services.WorkDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.util.List;

public class AjouterEquipeController {

    @FXML
    ComboBox<Work> listOfWorks;
    @FXML
    private TextField numberOfPersonsField;

    @FXML
    private TextField budgetField;

    @FXML
    private Button ajouterButton;


    public void initialize() {
        // Assuming you have a WorkDAO instance
        WorkDAO workDAO = new WorkDAO();
        List<Work> allWorks = workDAO.getAllWorks();

        // Assuming Work class has a meaningful toString() method
        ObservableList<Work> worksList = FXCollections.observableArrayList(allWorks);
        listOfWorks.setItems(worksList);
    }
    @FXML
     public void ajouterButtonClickedd(ActionEvent event) {
        // Récupérer les valeurs des champs depuis l'interface utilisateur
        Work selectedWork = listOfWorks.getSelectionModel().getSelectedItem();

        // Créer un nouvel objet Team avec les valeurs récupérées
        Equipe newTeam = new Equipe(1, "budget", 1,selectedWork);
        System.out.println("team"+newTeam);
         // Associate the selected work with the team

        // Ajouter l'équipe à la base de données en utilisant le TeamDAO
        EquipeDao teamDAO = new EquipeDao();
        teamDAO.addEquipe(newTeam);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("L'équipe a été ajoutée avec succès");
        alert.show();

        // Vous pouvez également effectuer d'autres actions après l'ajout de l'équipe si nécessaire
    }
    @FXML
    public void ajouterButtonClickedd(javafx.event.ActionEvent actionEvent) {
        // Récupérer les valeurs des champs depuis l'interface utilisateur
        Work selectedWork = listOfWorks.getSelectionModel().getSelectedItem();
        String relationTo = budgetField.getText();
        if (numberOfPersonsField.getText().isEmpty()) {
            showAlert("Champ manquant", "Veuillez saisir le nombre de personnes.");
            return;
        }
        int nbrPersonne = Integer.parseInt(numberOfPersonsField.getText()); // Replace with the actual field name
        if (nbrPersonne<=0){
            showAlert("il faut qu'il etre supreriur a 0","verifier votre information");
            return;
        }
        // Vérifier si les champs sont correctement remplis
        if (selectedWork == null) {
            showAlert("Champ manquant", "Veuillez sélectionner un travail.");
            return;
        }

        if (relationTo.isEmpty()) {
            showAlert("Champ manquant", "Veuillez saisir une valeur pour la relation.");
            return;
        }

        if (numberOfPersonsField.getText().isEmpty()) {
            showAlert("Champ manquant", "Veuillez saisir le nombre de personnes.");
            return;
        }
        // Créer un nouvel objet Team avec les valeurs récupérées
        Equipe newTeam = new Equipe(1,relationTo, nbrPersonne, selectedWork);
        System.out.println(newTeam);

        // Associate the selected work with the team

        // Ajouter l'équipe à la base de données en utilisant le TeamDAO
        EquipeDao teamDAO = new EquipeDao();

        teamDAO.addEquipe(newTeam);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("L'équipe a été ajoutée avec succès");
        alert.show();


    }



    /*public void ajouterButtonClickedd(javafx.event.ActionEvent actionEvent) {
        // Récupérer les valeurs des champs depuis l'interface utilisateur
        Work selectedWork = listOfWorks.getSelectionModel().getSelectedItem();

        // Créer un nouvel objet Team avec les valeurs récupérées
        System.out.println("aaaaa"+selectedWork.getWorkID());
        Equipe newTeam = new Equipe(1, "budget", 1,selectedWork);
        System.out.println(newTeam);
        // Associate the selected work with the team

        // Ajouter l'équipe à la base de données en utilisant le TeamDAO
        EquipeDao teamDAO = new EquipeDao();

        teamDAO.addEquipe(newTeam);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("L'équipe a été ajoutée avec succès");
        alert.show();

    }*/
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
