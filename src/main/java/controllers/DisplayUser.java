package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import models.Utilisateurs;
import java.util.List;
import Services.CrudUtilisateurs;

public class DisplayUser {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Utilisateurs, String> nomColumn;

    @FXML
    private TableColumn<Utilisateurs, String> prenomColumn;

    @FXML
    private TableColumn<Utilisateurs, String> emailColumn;

    @FXML
    private TableColumn<Utilisateurs, String> roleColumn;

    @FXML
    private TableView<Utilisateurs> userTable;





    @FXML
    private void deleteUser() {
        Utilisateurs selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            // Confirm deletion
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText("Delete User");
            alert.setContentText("Are you sure you want to delete the selected user?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Delete the user from the database
                    crudUtilisateurs.deleteEntity(selectedUser);
                    // Remove the user from the TableView
                    userTable.getItems().remove(selectedUser);
                    showAlert(Alert.AlertType.INFORMATION, "User Deleted", "User has been deleted successfully.");
                }
            });
        } else {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a user to delete.");
        }
    }


    @FXML
    void modifyUser(ActionEvent event) {
        Utilisateurs selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifyUser.fxml"));
                Parent root = loader.load();
                ModifyUserController controller = loader.getController();
                controller.setUser(selectedUser);

                Stage stage = new Stage();
                stage.setTitle("Modify User");
                stage.setScene(new Scene(root));

                // Show the modification interface in a new window and wait for it to be closed
                stage.showAndWait();

                // After the modification interface is closed, check if the modification was successful
                if (controller.isModificationSuccessful()) {
                    // Show an alert indicating that the user has been modified successfully
                    showAlert(Alert.AlertType.INFORMATION, "Modification Successful", "User modified successfully.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a user to modify.");
        }

    }

    private final CrudUtilisateurs crudUtilisateurs = new CrudUtilisateurs();
    @FXML
    public void initialize() {
        // Initialize the table columns
        nomColumn.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        prenomColumn.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        roleColumn.setCellValueFactory(cellData -> cellData.getValue().roleProperty());

        // Populate the TableView with user data

        List<Utilisateurs> userData = crudUtilisateurs.getAllData();
        userTable.getItems().addAll(userData);
        refreshTable();
    }
    private void refreshTable() {
        userTable.getItems().clear();
        userTable.getItems().addAll(crudUtilisateurs.getAllData());
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
