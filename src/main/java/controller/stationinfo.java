package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import entities.Commun_means_of_transport;
import entities.Station;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import services.Commun_means_of_transportServices;
import services.StationServices;


public class stationinfo {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Station> liststat;

    @FXML
    private TableColumn<?, ?> ID;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private TableColumn<?, ?> address;

    @FXML
    private TableColumn<Station, Void> actions;


    @FXML
    StationServices statServices = new StationServices();




    @FXML
    void initialize() {

        ObservableList<Station> list = FXCollections.observableList(StationServices.getAllData1());
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        address.setCellValueFactory(new PropertyValueFactory<>("Address"));

        liststat.setItems(list);
        actions.setCellFactory(param -> new TableCell<Station, Void>() {

            final javafx.scene.control.Button deleteButton = new javafx.scene.control.Button("Delete");
            final javafx.scene.control.Button updateButton = new javafx.scene.control.Button("Update");

            private void deletestat(Station stat) {
                statServices.deleteEntity(stat);
                liststat.getItems().remove(stat);
            }

            {
                deleteButton.setOnAction((ActionEvent event) -> {
                    Station stat1 = getTableView().getItems().get(getIndex());
                    if (confirmAction("Confirmation", "Voulez-vous vraiment supprimer cette ligne ?")) {
                        deletestat(stat1);
                    }
                });
                liststat.setItems(list);
            }


            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Station cmt = getTableView().getItems().get(getIndex());
                    if (cmt != null) {
                        HBox buttons = new HBox(deleteButton, updateButton);
                        setGraphic(buttons);
                    }
                }
            }
        });

    }


                public void back_to_add(javafx.event.ActionEvent actionEvent) throws IOException {
                    try {
                        Home.loadFXML("/ajouterstation.fxml");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }

                private boolean confirmAction(String title, String message) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle(title);
                    alert.setHeaderText(null);
                    alert.setContentText(message);

                    Optional<ButtonType> result = alert.showAndWait();
                    return result.isPresent() && result.get() == ButtonType.OK;
                }



}