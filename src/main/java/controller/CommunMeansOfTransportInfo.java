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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
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
    private Button displayPieChart;
    @FXML
    private TextField efficacite;

    @FXML
    private TextField ponctualite;
    @FXML
    private TextField note;



    @FXML
    Commun_means_of_transportServices moyenServices = new Commun_means_of_transportServices();

    @FXML
    void initialize() {

       // displayPieChart.setOnAction(this::handleDisplayPieChart);

        ObservableList<Commun_means_of_transport> list = FXCollections.observableList(moyenServices.
                getAllData());
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



           FXMLLoader loader = new FXMLLoader(getClass().getResource("/chart.fxml"));


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }




    // int countGreaterThan100 = (int) panierServices.countBuses();


    @FXML
    void displayPieChart(ActionEvent event) throws IOException {
        Commun_means_of_transportServices moyenServices  = new Commun_means_of_transportServices();

        long bus = moyenServices.countBuses();
        long train = moyenServices.countTrains();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Chart.fxml"));
        Parent root = loader.load();

        Chart pieChartController = loader.getController();
        pieChartController.initializePieChart(bus, train);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("chartpie");
        stage.show();
    }







    private boolean confirmAction(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
    @FXML
    void Rating(ActionEvent event) {
        double calculatedRating;
        // Retrieve the values of the text fields
        double efficace= Double.parseDouble(efficacite.getText());
        double ponct = Double.parseDouble(ponctualite.getText());
       // double preparation = Double.parseDouble(preparationField.getText());
     //   double evaluations = Double.parseDouble(note.getText());

        // Define weights for each criterion
        double efficaceWeight = 0.5;
        double ponctWeight = 0.5;

        //double evaluationsWeight = 0.1;

        // Calculate the weighted sum of the criteria
        double weightedQuality = efficace * efficaceWeight;
        double weightedInteraction = ponct * ponctWeight;

     //   double weightedEvaluations = evaluations * evaluationsWeight;

        // Calculate the overall rating
        double overallRating = weightedQuality + weightedInteraction ;
        Commun_means_of_transport train = listMoy.getSelectionModel().getSelectedItem();
        if (train!= null) {
            // Display the calculated rating in the corresponding text field
            calculatedRating = overallRating;
            note.setText(String.valueOf(calculatedRating));
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("no selectected train");
            alert.show();
        }
    }

    public void go_to_station(ActionEvent actionEvent) throws IOException {
        Home.loadFXML("/ajouterstation.fxml");
    }

    public void back_to_add_mean(ActionEvent actionEvent) throws IOException {
        Home.loadFXML("/AjouerCommun_means_of_transport.fxml");
    }


}




