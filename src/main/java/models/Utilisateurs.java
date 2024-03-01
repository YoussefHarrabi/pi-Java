package models;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Utilisateurs {




    private int id;
    private StringProperty nom;
    private StringProperty prenom;
    private StringProperty email;

    private String motDePasse;
    private StringProperty role;
    private StringProperty age; // Change the type to String


    public String getAge() {
        return age.get();
    }

    public StringProperty ageProperty() {
        return age;
    }

    public Utilisateurs(StringProperty nom, StringProperty prenom, StringProperty email, String motDePasse, StringProperty age) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.age = age;
    }

    public void setAge(String age) {
        this.age.set(age);
    }

    public Utilisateurs() {
        this.nom = new SimpleStringProperty();
        this.prenom = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.role = new SimpleStringProperty();
        this.age = new SimpleStringProperty();
    }

    public Utilisateurs(int id, String nom, String prenom, String email, String motDePasse, String role) {
        this.id = id;
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.email = new SimpleStringProperty(email);
        this.motDePasse = motDePasse;
        this.role = new SimpleStringProperty(role);
    }

    public Utilisateurs(String nom, String prenom, String email, String motDePasse, String role) {
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.email = new SimpleStringProperty(email);
        this.motDePasse = motDePasse;
        this.role = new SimpleStringProperty(role);
    }
    public Utilisateurs(int id, String nom, String prenom, String email, String motDePasse, String role, String age) {
        this.id = id;
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.email = new SimpleStringProperty(email);
        this.motDePasse = motDePasse;
        this.role = new SimpleStringProperty(role);
        this.age = new SimpleStringProperty(age);
    }


    public Utilisateurs(String nom, String prenom, String email, String motDePasse, String role, String age) {
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.email = new SimpleStringProperty(email);
        this.motDePasse = motDePasse;
        this.role = new SimpleStringProperty(role);
        this.age = new SimpleStringProperty(age);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom.get();
    }

    public StringProperty nomProperty() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public String getPrenom() {
        return prenom.get();
    }

    public StringProperty prenomProperty() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom.set(prenom);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getRole() {
        return role.get();
    }

    public StringProperty roleProperty() {
        return role;
    }

    public void setRole(String role) {
        this.role.set(role);
    }

    @Override
    public String toString() {
        return "Utilisateurs{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
