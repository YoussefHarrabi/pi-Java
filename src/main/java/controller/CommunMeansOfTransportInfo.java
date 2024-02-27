/*package controller;

import entities.Commun_means_of_transport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import services.Commun_means_of_transportServices;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CommunMeansOfTransportInfo {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Commun_means_of_transport, Void> DeleteColumn;
    @FXML
    private TableView<Commun_means_of_transport> listMoy;

    @FXML
    private TableColumn<?, ?> id_moy;

    @FXML
    private TableColumn<?, ?> reg_moy;

    @FXML
    private TableColumn<?, ?> type_moy;
    @FXML
    Commun_means_of_transportServices moyenServices = new Commun_means_of_transportServices();

    @FXML
    void initialize() {
        ObservableList<Commun_means_of_transport> list = FXCollections.observableList(moyenServices.getAllData());
        id_moy.setCellValueFactory(new PropertyValueFactory<>("id"));
        reg_moy.setCellValueFactory(new PropertyValueFactory<>("registration_number"));
        type_moy.setCellValueFactory(new PropertyValueFactory<>("type"));


        DeleteColumn.setCellFactory(param -> new TableCell<Commun_means_of_transport, Void>() {
            private final javafx.scene.control.Button deleteButton = new javafx.scene.control.Button("Delete");
            private final javafx.scene.control.Button updatebutton = new javafx.scene.control.Button("Update");

            {
                deleteButton.setOnAction((ActionEvent event) -> {
                    Commun_means_of_transport moyen = getTableView().getItems().get(getIndex());
                    deleteMoy(moyen);
                });
            }
            {
                updatebutton.setOnAction((ActionEvent event) -> {
                    Commun_means_of_transport moyen = getTableView().getItems().get(getIndex());
                    try {
                        FXMLLoader loader = Home.loadFXML("/ModifierCommun_means_of_transport.fxml");
                        ModifierCommunMeansOfTransport mcmt = loader.getController();
                        mcmt.set_transport(moyen);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

            private void deleteMoy(Commun_means_of_transport moyen){
                moyenServices.deleteEntity(moyen);
                listMoy.getItems().remove(moyen);
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Commun_means_of_transport cmt = getTableView().getItems().get(getIndex());
                    if(cmt != null){
                        HBox buttons = new HBox(deleteButton, updatebutton);
                        setGraphic(buttons);
                    }

                }
            }
        });
        listMoy.setItems(list);




    }
} */



package controller;

import entities.Commun_means_of_transport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import services.Commun_means_of_transportServices;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CommunMeansOfTransportInfo {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Commun_means_of_transport, Void> DeleteColumn;
    @FXML
    private TableView<Commun_means_of_transport> listMoy;

    @FXML
    private TableColumn<?, ?> id_moy;

    @FXML
    private TableColumn<?, ?> reg_moy;

    @FXML
    private TableColumn<?, ?> type_moy;
    @FXML
    Commun_means_of_transportServices moyenServices = new Commun_means_of_transportServices();

    @FXML
    void initialize() {
        ObservableList<Commun_means_of_transport> list = FXCollections.observableList(moyenServices.getAllData());
        id_moy.setCellValueFactory(new PropertyValueFactory<>("id"));
        reg_moy.setCellValueFactory(new PropertyValueFactory<>("registration_number"));
        type_moy.setCellValueFactory(new PropertyValueFactory<>("type"));

        DeleteColumn.setCellFactory(param -> new TableCell<Commun_means_of_transport, Void>() {
            private final javafx.scene.control.Button deleteButton = new javafx.scene.control.Button("Delete");
            private final javafx.scene.control.Button updateButton = new javafx.scene.control.Button("Update");

            {
                deleteButton.setOnAction((ActionEvent event) -> {
                    Commun_means_of_transport moyen = getTableView().getItems().get(getIndex());
                    if (confirmAction("Confirmation", "Voulez-vous vraiment supprimer cette ligne ?")) {
                        deleteMoy(moyen);
                    }
                });
            }
            {
                updateButton.setOnAction((ActionEvent event) -> {
                    Commun_means_of_transport moyen = getTableView().getItems().get(getIndex());
                    if (confirmAction("Confirmation", "Voulez-vous vraiment modifier cette ligne ?")) {
                        try {
                            FXMLLoader loader = Home.loadFXML("/ModifierCommun_means_of_transport.fxml");
                            ModifierCommunMeansOfTransport mcmt = loader.getController();
                            mcmt.set_transport(moyen);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }

            private void deleteMoy(Commun_means_of_transport moyen) {
                moyenServices.deleteEntity(moyen);
                listMoy.getItems().remove(moyen);
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Commun_means_of_transport cmt = getTableView().getItems().get(getIndex());
                    if (cmt != null) {
                        HBox buttons = new HBox(deleteButton, updateButton);
                        setGraphic(buttons);
                    }
                }
            }
        });
        listMoy.setItems(list);
    }

    private boolean confirmAction(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    public void go_to_station(ActionEvent actionEvent) throws IOException {
        Home.loadFXML("/ajouterstation.fxml");
    }

    public void back_to_add_mean(ActionEvent actionEvent) throws IOException {
        Home.loadFXML("/AjouerCommun_means_of_transport.fxml");
    }
}
