package entities;

import java.time.LocalTime;
import java.util.Date;

public class Incident {
    int IncidentId ;
    String Type;
    String Place;
    String  Hour;
    String Description;
    Date Date;

    public Incident() {
    }

    public Incident(int incidentId, String type, String place, String hour, String description) {
        IncidentId = incidentId;
        Type = type;
        Place = place;
        Hour = hour;
        Description = description;
    }

    public Incident(String type, String place, String hour, String description) {
        Type = type;
        Place = place;
        Hour = hour;
        Description = description;
    }

    public int getIncidentId() {
        return IncidentId;
    }

    public void setIncidentId(int incidentId) {
        IncidentId = incidentId;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public String getHour() {
        return Hour;
    }

    public void setHour(String hour) {
        Hour = hour;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    @Override
    public String toString() {
        return "Incident{" +

                " Type='" + Type + '\'' +
                ", Place='" + Place + '\'' +
                ", Hour=" + Hour +
                ", Description='" + Description + '\'' +
                '}';
    }
    public static Incident getIncident(){
        String type = getIncident().getType();// Assuming typeComboBox is a ComboBox<String>
        String place = getIncident().getPlace(); // Assuming placeTextField is a TextField
        String hour = getIncident().getHour(); // Assuming hourTextField is a TextField
        String description = getIncident().getDescription(); // Assuming descriptionTextField is a TextField

        // Create and return an Incident object
        return new Incident(type, place, hour, description);
    }
}
