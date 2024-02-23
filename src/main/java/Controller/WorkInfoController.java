package Controller;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.time.LocalDate;
public class WorkInfoController {
    @FXML
    private Label locationLabel;

    @FXML
    private Label startDateLabel;

    @FXML
    private Label endDateLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label isActiveLabel;

    // Méthode pour initialiser et afficher les informations sur le travail
    public void initializeWorkInfo(String location, LocalDate startDate, LocalDate endDate, String description, boolean isActive) {
        locationLabel.setText("Location: " + location);
        startDateLabel.setText("Start Date: " + startDate.toString());
        endDateLabel.setText("End Date: " + endDate.toString());
        descriptionLabel.setText("Description: " + description);
        isActiveLabel.setText("Is Active: " + (isActive ? "Yes" : "No"));
    }
}

