package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

public class Chart {

    @FXML
    private PieChart pieChart;

    public void initializePieChart(long bus, long train) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Bus", bus),
                new PieChart.Data("Train",train)
        );

        pieChart.setData(pieChartData);
    }
}