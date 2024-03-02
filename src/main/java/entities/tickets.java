package entities;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;

public class tickets {
    private int ticket_id;
    private String moyen_de_transport;
    private double prix ;
    private Date date_d_achat;
    private Time temp_d_arriver;
    private int utilisateur_id;
    private String destination;
    private int confirmation;
    private Time temps_depart;
    private Time duree_trajet;

    public tickets() {
    }

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getMoyen_de_transport() {
        return moyen_de_transport;
    }

    public void setMoyen_de_transport(String moyen_de_transport) {
        this.moyen_de_transport = moyen_de_transport;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Date getDate_d_achat() {
        return date_d_achat;
    }

    public void setDate_d_achat(Date date_d_achat) {
        this.date_d_achat = date_d_achat;
    }

    public Time getTemp_d_arriver() {
        return temp_d_arriver;
    }

    public void setTemp_d_arriver(Time temp_d_arriver) {
        this.temp_d_arriver = temp_d_arriver;
    }

    public int getUtilisateur_id() {
        return utilisateur_id;
    }

    public void setUtilisateur_id(int utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(int confirmation) {
        this.confirmation = confirmation;
    }

    public Time getTemps_depart() {
        return temps_depart;
    }

    public void setTemps_depart(Time temps_depart) {
        this.temps_depart = temps_depart;
    }

    public Time getDuree_trajet() {
        return duree_trajet;
    }

    public void setDuree_trajet(Time duree_trajet) {
        this.duree_trajet = duree_trajet;
    }
    public LocalTime getDepartureTime() {
        return temps_depart.toLocalTime();
    }

    // Méthode pour obtenir la durée du trajet
    public LocalTime getTrajetDuration() {
        return duree_trajet.toLocalTime();
    }

    public void verification() {
        LocalTime duration = getTrajetDuration();

        if ((temps_depart.getHours() >= 7 && temps_depart.getHours() <= 9) ||
                (temps_depart.getHours() == 13 && temps_depart.getMinutes() >= 0 && temps_depart.getMinutes() <= 59)) {
            duration = duration.plusMinutes(15);


            System.out.println("Retard possible. Nouvelle durée : " + duration);
        }
    }
}
