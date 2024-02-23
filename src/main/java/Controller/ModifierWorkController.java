package Controller;

import Entities.Work;
import Services.WorkDAO;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class ModifierWorkController {

    @FXML
    private TextField modifiedLocationField;

    @FXML
    private DatePicker modifiedStartDatePicker;

    @FXML
    private DatePicker modifiedEndDatePicker;

    @FXML
    private TextField modifiedDescriptionField;

    @FXML
    private CheckBox modifiedIsActiveCheckbox;

    private Work workToModify;

    public void setWork(Work work) {
        this.workToModify = work;
        populateFields();
    }

    private void populateFields() {
        if (workToModify != null) {
            modifiedLocationField.setText(workToModify.getLocation());
            modifiedStartDatePicker.setValue(workToModify.getStartdate());
            modifiedEndDatePicker.setValue(workToModify.getEnddate());
            modifiedDescriptionField.setText(workToModify.getDescription());
            modifiedIsActiveCheckbox.setSelected(workToModify.isActive());
        }
    }


    @FXML
    private void saveChangesButtonClicked() {
        if (workToModify != null) {
            // Update the work with the modified values
            workToModify.setLocation(modifiedLocationField.getText());
            workToModify.setStartdate(modifiedStartDatePicker.getValue());
            workToModify.setEnddate(modifiedEndDatePicker.getValue());
            workToModify.setDescription(modifiedDescriptionField.getText());
            workToModify.setActive(modifiedIsActiveCheckbox.isSelected());

            // Implement logic to save the changes to the database or perform any necessary actions
            WorkDAO workDAO = new WorkDAO();
            workDAO.updateWork(workToModify);

            // Close the modification interface
            modifiedLocationField.getScene().getWindow().hide();
        }
    }

}
