package Entities;

import javafx.scene.control.Button;

import java.time.LocalDate;

public class Work {
    private int workID;
    private String location;
    private LocalDate startdate;
    private LocalDate enddate;
    private String description;
    private boolean isActive;
    private final Button modifyButton = new Button("Modify");
    public Button modifyButtonProperty() {
        return modifyButton;
    }

    public int getWorkID() {
        return workID;
    }

    public void setWorkID(int workID) {
        this.workID = workID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Work() {
    }

    public LocalDate getStartdate() {
        return startdate;
    }

    public void setStartdate(LocalDate startdate) {
        this.startdate = startdate;
    }

    public LocalDate getEnddate() {
        return enddate;
    }

    public void setEnddate(LocalDate enddate) {
        this.enddate = enddate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {

        this.isActive = isActive;
    }

    public Work(int workID, String location, LocalDate startdate, LocalDate enddate, String description, boolean isActive) {
        this.workID = workID;
        this.location = location;
        this.startdate = startdate;
        this.enddate = enddate;
        this.description = description;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "Work{" +
                "workID=" + workID +
                ", location='" + location + '\'' +
                ", startdate=" + startdate +
                ", enddate=" + enddate +
                ", description='" + description + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
