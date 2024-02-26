package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.CapteursServices;

import java.io.IOException;

public class Home extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CapteursController.fxml"));
        try {
            Parent root = loader.load(); // charger le fichier

            // Initialiser le service et le passer au contrôleur
            CapteursServices capteursService = new CapteursServices();
            CapteursController controller = loader.getController();
            controller.setCapteursService(capteursService);

            Scene scene = new Scene(root); // événement
            primaryStage.setScene(scene); // pour mettre la scène
            primaryStage.show(); // afficher l'ensemble de la scène

            String css = this.getClass().getResource("/style.css").toExternalForm();
            scene.getStylesheets().add(css);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
