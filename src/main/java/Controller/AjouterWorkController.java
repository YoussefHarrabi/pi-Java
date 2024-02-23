package Controller;

import Entities.Work;
import Services.WorkDAO;
import Utiles.MyConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

public class AjouterWorkController {

    @FXML
    private TextField locationField;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TextField descriptionArea;

    @FXML
    private CheckBox isActiveCheckbox;

    @FXML
    private Button ajouterButton;

    // Méthode appelée lorsque le bouton "Ajouter" est cliqué
    @FXML
    void ajouterButtonClicked(ActionEvent event) {
        // Récupérer les valeurs des champs depuis l'interface utilisateur
        String location = locationField.getText();
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        String description = descriptionArea.getText();
        boolean isActive = isActiveCheckbox.isSelected();





        // Créer un nouvel objet Work avec les valeurs récupérées
        Work newWork = new Work();
        newWork.setLocation(location);
        newWork.setStartdate(startDate);
        newWork.setEnddate(endDate);
        newWork.setDescription(description);
        newWork.setActive(isActive);

        // Ajouter le travail à la base de données en utilisant le WorkDAO
        WorkDAO workDAO = new WorkDAO();
        workDAO.addWork(newWork);
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("le travau a eté ajouté avec succés");
        alert.show();

        // Après avoir ajouté le travail, affichez les informations dans WorkInfo
        showWorkInfo(newWork.getLocation(), newWork.getStartdate(), newWork.getEnddate(), newWork.getDescription(), newWork.isActive());

        configureAutoCompletion();
    }

    private boolean isValidDate(LocalDate date) {
        // Vérifier si la date est dans le passé
        LocalDate currentDate = LocalDate.now();
        return date != null && !date.isBefore(currentDate);
    }
    // Méthode pour afficher les informations sur le travail dans WorkInfo
    private void showWorkInfo(String location, LocalDate startDate, LocalDate endDate, String description, boolean isActive) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/WorkInfo.fxml"));
            Parent root = loader.load();

            WorkInfoController workInfoController = loader.getController();
            workInfoController.initializeWorkInfo(location, startDate, endDate, description, isActive);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Work Information");
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Méthode pour obtenir dynamiquement la liste des suggestions depuis la base de données
    private List<String> fetchDataFromDatabase() {
        List<String> suggestions = new ArrayList<>();

        try (Connection connection = MyConnection.getConnection()) {
            String query = "SELECT DISTINCT location AS location FROM work";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String location = resultSet.getString("location");
                        suggestions.add(location);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer les exceptions appropriées
        }

        return suggestions;
    }



    // Méthode pour configurer l'autocomplétion
    private void configureAutoCompletion() {
        assert locationField != null : "locationField is not injected!";

        List<String> suggestions = fetchDataFromDatabase();
        TextFields.bindAutoCompletion(locationField, suggestions);
    }



}
