package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.CapteursServices;

import java.io.IOException;

public class Home extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Initialiser le service CapteursServices
        CapteursServices capteursService = new CapteursServices();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Map.fxml"));
        VBox root = loader.load();

        // Récupérer le contrôleur et injecter le service
        MapController controller = loader.getController();
        controller.setCapteursService(capteursService);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Appel à la méthode initialize de MapController après l'affichage de la scène
        controller.initializeMap();
    }

    public static void main(String[] args) {
        launch(args);
    }
}






