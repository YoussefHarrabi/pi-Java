package controllers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Home extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Map.fxml"));
        primaryStage.setTitle("Your App Title");
        Scene scene = new Scene(root, 800, 600);
        String css = this.getClass().getResource("/Map.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static class HomeController implements Initializable {

        @FXML
        private WebView mapView;

        private WebEngine webEngine;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            webEngine = mapView.getEngine();
            webEngine.load(getClass().getResource("/Leaflet.html").toExternalForm());

        }
    }
}