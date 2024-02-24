package entities;

public class ride {

    private int id_driver;
    private String driver;
    private String startLocation;
    private String endLocation;
    private String departureTime;
    private int availableseats;

    public ride(int id_driver, String driver, String startLocation, String endLocation, String departureTime, int availableseats) {
        this.id_driver = id_driver;
        this.driver = driver;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.departureTime = departureTime;
        this.availableseats = availableseats;
    }
    public ride(){}
    public ride(String driver, String startLocation, String endLocation, String departureTime, int availableseats) {
        this.driver = driver;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.departureTime = departureTime;
        this.availableseats = availableseats;
    }

    public int getId_driver() {
        return id_driver;
    }

    public void setId_driver(int id_driver) {
        this.id_driver = id_driver;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public int getAvailableseats() {
        return availableseats;
    }

    public void setAvailableseats(int availableseats) {
        this.availableseats = availableseats;
    }

    @Override
    public String toString() {
        return "ride{" +
                "id_driver=" + id_driver +
                ", driver='" + driver + '\'' +
                ", startLocation='" + startLocation + '\'' +
                ", endLocation='" + endLocation + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", availableseats=" + availableseats +
                '}';
    }
}
