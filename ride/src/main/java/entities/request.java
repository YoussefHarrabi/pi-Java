package entities;

public class request {
    private int id_request;
    private String startLocation;
    private String endLocation;
    private String departureTime;
    private int availableseats;

    public request(int id_passenger, String startLocation, String endLocation, String departureTime, int availableseats) {
        this.id_request = id_passenger;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.departureTime = departureTime;
        this.availableseats = availableseats;
    }
    public request(){}
    public request(String startLocation, String endLocation, String departureTime, int availableseats) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.departureTime = departureTime;
        this.availableseats = availableseats;
    }

    public int getId_request() {
        return id_request;
    }

    public void setId_request(int id_request) {
        this.id_request = id_request;
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
                "id_passenger=" + id_request +
                ", startLocation='" + startLocation + '\'' +
                ", endLocation='" + endLocation + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", availableseats=" + availableseats +
                '}';
    }
}

