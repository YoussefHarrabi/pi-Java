package entities;

public class Commun_means_of_transport {
    private String registration_number;
    private int id;
    private String type;

    public Commun_means_of_transport() {
    }

    public Commun_means_of_transport(String registration_number, String type) {
        this.registration_number = registration_number;

        this.type = type;
    }





    public String getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(String registration_number) {
        this.registration_number = registration_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Commun_means_of_transport{" +
                "registration_number=" + registration_number +
                ", id=" + id +
                ", type='" + type + '\'' +
                '}';
    }



}