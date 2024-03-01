package models;

public class Station {
    private int stationId;
    private String stationName;
    private int stationOrder;


    public Station(int stationId, String stationName, int stationOrder) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.stationOrder = stationOrder;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getStationOrder() {
        return stationOrder;
    }

    public void setStationOrder(int stationOrder) {
        this.stationOrder = stationOrder;
    }

    @Override
    public String toString() {
        return "Station{" +
                "stationId=" + stationId +
                ", stationName='" + stationName + '\'' +
                ", stationOrder=" + stationOrder +
                '}';
    }
}
