package controles;



import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import Utiles.MyConnection;
import entities.Incident;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.IncidentServices;

/**
 * FXML Controller class
 *
 * @author spangsberg
 */
public class Charts implements Initializable {

    @FXML
    private BorderPane borderPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        borderPane.setCenter(buildPieChart());

    }

    @FXML
    private void handleShowBarChart() {

        borderPane.setCenter(buildBarChart());
    }

    @FXML
    private void handleShowPieChart() {
        borderPane.setCenter(buildPieChart());
    }

    public BarChart<String, Number> buildBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Place");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Count");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

        XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<>();
        dataSeries1.setName("Place Count");

        try (
                Statement st = MyConnection.getInstance().getCnx().createStatement();
                ResultSet rs = st.executeQuery("SELECT Place, COUNT(*) AS count FROM Incident GROUP BY Place")) {

            while (rs.next()) {
                String place = rs.getString("place");
                int count = rs.getInt("count");
                dataSeries1.getData().add(new XYChart.Data<>(place, count));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }

        barChart.getData().add(dataSeries1);

        return barChart;
    }

    public PieChart buildPieChart() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        try (
                Statement st = MyConnection.getInstance().getCnx().createStatement();
                ResultSet rs = st.executeQuery("SELECT Place, COUNT(*) AS count FROM Incident GROUP BY Place")) {

            while (rs.next()) {
                String place = rs.getString("Place");
                int count = rs.getInt("count");
                pieChartData.add(new PieChart.Data(place, count));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }

        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Incidents by Place");

        // Add tooltips to pie chart slices
        createToolTips(pieChart);

        return pieChart;
    }

    private void setTooltip(PieChart.Data data, String place, int count) {
        Tooltip tooltip = new Tooltip(place + ": " + count);
        Tooltip.install(data.getNode(), tooltip);
    }

    @FXML
    private void handleClose() {
        Stage primaryStage = (Stage) borderPane.getScene().getWindow(); // Get primaryStage
        Home.switchScene(primaryStage, "/displayIncident.fxml");
    }



    private void createToolTips(PieChart pc) {
        for (PieChart.Data data : pc.getData()) {
            String msg = Double.toString(data.getPieValue());

            Tooltip tp = new Tooltip(msg);
            tp.setShowDelay(Duration.seconds(0));

            Tooltip.install(data.getNode(), tp);

            data.pieValueProperty().addListener((observable, oldValue, newValue) -> {
                tp.setText(newValue.toString());
            });
        }
    }


}
