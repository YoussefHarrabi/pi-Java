package entities;

public class Injury {
    int id ;
    int incidentId;
    String type;
    String severity;
    int Number_pers;

    public Injury() {
    }

    public Injury(int id, int incidentId, String type,int number_pers, String severity) {
        this.id = id;
        this.incidentId = incidentId;
        this.type = type;
        this.Number_pers = number_pers;
        this.severity = severity;
    }

    public Injury(int incidentId, String type,int number_pers, String severity) {
        this.incidentId = incidentId;
        this.type = type;
        this.severity = severity;
        this.Number_pers = number_pers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(int incidentId) {
        this.incidentId = incidentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public int getNumber_pers() {
        return Number_pers;
    }

    public void setNumber_pers(int number_pers) {
        Number_pers = number_pers;
    }

    @Override
    public String toString() {
        return "Injury{" +
                "incidentId=" + incidentId +
                ", type='" + type + '\'' +
                ", severity='" + severity + '\'' +
                ", Number_pers=" + Number_pers +
                '}';
    }

}
