package controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entities.Station;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableColumn<?, ?> actions;



    @FXML
    void initialize() {

        ObservableList<Station> list = FXCollections.observableList(StationServices.getAllData1());
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        address.setCellValueFactory(new PropertyValueFactory<>("Address"));

        liststat.setItems(list);
        
        

    }


    public void back_to_add(javafx.event.ActionEvent actionEvent) throws IOException {
        try {
            Home.loadFXML("/ajouterstation.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
