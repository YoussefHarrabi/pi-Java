package controles;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Home extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/displayIncident.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            String css = this.getClass().getResource("/Style.css").toExternalForm();
            scene.getStylesheets().add(css);
            //addIncident controller = loader.getController();
            primaryStage.setScene(scene);

            // controller.onStartup();


            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void switchScene(Stage primaryStage, String fxml) {
        try {
            Parent root = FXMLLoader.load(Home.class.getResource(fxml));
            Scene scene = new Scene(root);
            String css = Home.class.getResource("/Style.css").toExternalForm();
            scene.getStylesheets().add(css);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void switchSceneWithData(Stage primaryStage, String fxml, int incidentId, String type, String place, String hour, String description) {
        try {
            FXMLLoader loader = new FXMLLoader(Home.class.getResource(fxml));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            String css = Home.class.getResource("/Style.css").toExternalForm();
            scene.getStylesheets().add(css);
            primaryStage.setScene(scene);

            // Access controller to set data
            UpdateIncident controller = loader.getController();
            controller.setData(incidentId,type, place, hour, description);

            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
