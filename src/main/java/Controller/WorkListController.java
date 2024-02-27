/*package Controller;

import Entities.Work;
import Services.WorkDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.Optional;

public class WorkListController {

    @FXML
    private TableColumn<Work, String> locationColumn;

    @FXML
    private TableColumn<Work, String> descriptionColumn;

    @FXML
    private TableColumn<Work, String> startDateColumn;

    @FXML
    private TableColumn<Work, String> endDateColumn;

    @FXML
    private TableColumn<Work, Boolean> isActiveColumn;

    @FXML
    private TableColumn<Work, String> modifyColumn;

    @FXML
    private TableView<Work> workTable;

    private WorkDAO workDAO;

    public WorkListController() {
        this.workDAO = new WorkDAO();
    }

    @FXML
    void modifyButtonClicked(ActionEvent event) {
        Work selectedWork = workTable.getSelectionModel().getSelectedItem();
        if (selectedWork != null) {
            if (showConfirmationDialog("Modify Work", "Are you sure you want to modify the selected work?")) {
                workDAO.updateWork(selectedWork);
                refreshTable();
            }
        } else {
            showAlert("No work selected", "Please select a work to modify.");
        }
    }
    private boolean showConfirmationDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }


    @FXML
    void supprimerButtonClicked(ActionEvent event) {
        // Implement the deletion logic using selected work from the table
        Work selectedWork = workTable.getSelectionModel().getSelectedItem();
        if (selectedWork != null) {
            // Display a confirmation dialog before deleting
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Confirm Deletion");
            alert.setContentText("Are you sure you want to delete the selected work?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // User clicked OK, perform deletion
                workDAO.deleteWork(selectedWork.getWorkID());
                // Refresh the table or update the view as needed
                refreshTable();
            }
        } else {
            showAlert("No work selected", "Please select a work to delete.");
        }
    }
    @FXML
    void initialize() {
        // Initialize the table columns
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startdate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("enddate"));
        isActiveColumn.setCellValueFactory(new PropertyValueFactory<>("isActive"));

        // Populate the table with data from the database
        refreshTable();
    }

    private void refreshTable() {
        // Reload data from the database and refresh the table
        List<Work> allWorks = workDAO.getAllWorks();
        ObservableList<Work> worksObservableList = FXCollections.observableArrayList(allWorks);
        workTable.setItems(worksObservableList);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}*/
package Controller;

import Entities.Work;
import Services.WorkDAO;
import Utiles.MyConnection;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.textfield.TextFields;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class WorkListController {

    @FXML
    private TableColumn<Work, String> locationColumn;

    @FXML
    private TableColumn<Work, String> descriptionColumn;

    @FXML
    private TableColumn<Work, String> startDateColumn;

    @FXML
    private TableColumn<Work, String> endDateColumn;

    @FXML
    private TableColumn<Work, Boolean> isActiveColumn;

    @FXML
    private TableColumn<Work, String> modifyColumn;

    @FXML
    private TableView<Work> workTable;

    private WorkDAO workDAO;

    public WorkListController() {
        this.workDAO = new WorkDAO();
    }
    private void showModifyWorkDialog(Work work) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierWork.fxml"));

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
    }


    @FXML
    void modifyButtonClicked(ActionEvent event) {
        Work selectedWork = workTable.getSelectionModel().getSelectedItem();
        if (selectedWork != null) {
            if (showConfirmationDialog("Modify Work", "Are you sure you want to modify the selected work?")) {
                // Affichez l'interface de modification avec le travail sélectionné
                showModifyWorkDialog(selectedWork);

                // Rafraîchissez la table après la modification (si nécessaire)
                refreshTable();
            }
        } else {
            showAlert("No work selected", "Please select a work to modify.");
        }
    }


    @FXML
    void supprimerButtonClicked(ActionEvent event) {
        Work selectedWork = workTable.getSelectionModel().getSelectedItem();
        if (selectedWork != null) {
            if (showConfirmationDialog("Delete Work", "Are you sure you want to delete the selected work?")) {
                try {
                    workDAO.deleteWork(selectedWork.getWorkID());
                    refreshTable();
                } catch (Exception e) {
                    handleDatabaseError(e);
                }
            }
        } else {
            showAlert("No work selected", "Please select a work to delete.");
        }
    }
    @FXML
    void addWorkButtonClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterWork.fxml"));
            Parent root = loader.load();

            AjouterWorkController ajouterWorkController = loader.getController();
            // Ajoutez d'autres initialisations si nécessaire

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add Work");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            // Actualisez la liste des travaux après l'ajout
            refreshTable();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void initialize() {
        // Initialize the table columns
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startdate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("enddate"));

        // Utilisez une notation de style JavaFX pour la PropertyValueFactory
        isActiveColumn.setCellValueFactory(cellData -> {
            BooleanProperty property = new SimpleBooleanProperty(cellData.getValue().isActive());
            property.addListener((obs, oldValue, newValue) -> cellData.getValue().setActive(newValue));
            return property;
        });

        // Utilisez une CheckBoxTableCell pour gérer les valeurs booléennes
        isActiveColumn.setCellFactory(CheckBoxTableCell.forTableColumn(isActiveColumn));





        // Populate the table with data from the database
        refreshTable();
    }

    private void refreshTable() {
        try {
            List<Work> allWorks = workDAO.getAllWorks();
            ObservableList<Work> worksObservableList = FXCollections.observableArrayList(allWorks);
            workTable.setItems(worksObservableList);
        } catch (Exception e) {
            handleDatabaseError(e);
        }
    }

    private void handleDatabaseError(Exception e) {
        showAlert("Database Error", "An error occurred while performing a database operation. Please try again.");
        e.printStackTrace(); // Log the exception for debugging purposes
    }

    private boolean showConfirmationDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void showTeamButtonClicked(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/EquipeList.fxml"));
            Parent root = loader.load();

            EquipeListController ajouterWorkController = loader.getController();
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
