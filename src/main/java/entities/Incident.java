package entities;

import java.time.LocalTime;
import java.util.Date;

public class Incident {
    int IncidentId ;
    String Type;
    String Place;
    String  Hour;
    String Description;

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

    @Override
    public String toString() {
        return "Incident{" +

                " Type='" + Type + '\'' +
                ", Place='" + Place + '\'' +
                ", Hour=" + Hour +
                ", Description='" + Description + '\'' +
                '}';
    }
}
