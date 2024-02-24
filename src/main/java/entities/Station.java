package entities;

public class Station {
    private static String name;
    private static String address;

    public Station() {
    }

    public Station(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Station{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }


    public void deleteEntity1(Station s) {

    }
}
