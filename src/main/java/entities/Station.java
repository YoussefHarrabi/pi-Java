package entities;

public class Station {
    private String name;
    private String address;
    private int id;

    public Station() {
    }

    public Station(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Station{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public void deleteEntity1(Station s) {
        // Votre logique de suppression ici
    }
}
