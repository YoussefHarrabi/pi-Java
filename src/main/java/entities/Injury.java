package entities;

public class Injury {
    int id ;
    int incidentId;
    String type;
    String severity;

    public Injury() {
    }

    public Injury(int id, int incidentId, String type, String severity) {
        this.id = id;
        this.incidentId = incidentId;
        this.type = type;
        this.severity = severity;
    }

    public Injury(int incidentId, String type, String severity) {
        this.incidentId = incidentId;
        this.type = type;
        this.severity = severity;
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

    @Override
    public String toString() {
        return "Injury{" +
                "id=" + id +
                ", incidentId=" + incidentId +
                ", type='" + type + '\'' +
                ", severity='" + severity + '\'' +
                '}';
    }
}
