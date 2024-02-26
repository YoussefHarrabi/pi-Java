package entities;

public class Capteurs {
    private int idCapteur;
    private String nom;
    private String type;
    private float latitude;
    private float longitude;
    private String dateInstallation;

    public Capteurs(String nom, String type, float latitude, float longitude, String dateInstallation) {
        this.nom = nom;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dateInstallation = dateInstallation ;
    }

    public Capteurs() {

    }

    public int getIdCapteur() {
        return idCapteur;
    }

    public void setIdCapteur(int idCapteur) {
        this.idCapteur = idCapteur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getDateInstallation() {
        return dateInstallation;
    }

    public void setDateInstallation(String dateInstallation) {
        this.dateInstallation = dateInstallation;

    }
    @Override
    public String toString() {
        return "Capteurs{" +
                "idCapteur=" + idCapteur +
                ", nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", dateInstallation='" + dateInstallation + '\'' +
                '}';
    }

}

