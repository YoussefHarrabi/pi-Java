package models;

import java.sql.Date;
import java.time.LocalDate;

public class Tickets {

        private int ticketId;
        private String moyenDeTransport;
        private double prix;
        private LocalDate dateDachat;



    private int utilisateurId;
        private String destination;
        private String fromDestination; // New attribute
        private boolean confirmation;
        private int walletId;




    // Constructors, getters, and setters
    public Tickets(int ticketId, String moyenDeTransport, double prix, LocalDate dateDachat, int utilisateurId, String destination, String fromDestination, boolean confirmation, int walletId) {
        this.ticketId = ticketId;
        this.moyenDeTransport = moyenDeTransport;
        this.prix = prix;
        this.dateDachat = dateDachat;
        this.utilisateurId = utilisateurId;
        this.destination = destination;
        this.fromDestination = fromDestination;
        this.confirmation = confirmation;
        this.walletId = walletId;
    }

    public LocalDate getDateDachat() {
        return dateDachat;
    }

    public void setDateDachat(LocalDate dateDachat) {
        this.dateDachat = dateDachat;
    }

    public String getFromDestination() {
        return fromDestination;
    }

    public void setFromDestination(String fromDestination) {
        this.fromDestination = fromDestination;
    }
    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getMoyenDeTransport() {
        return moyenDeTransport;
    }

    public void setMoyenDeTransport(String moyenDeTransport) {
        this.moyenDeTransport = moyenDeTransport;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public LocalDate getDateAchat() {
        return dateDachat;
    }

    public void setDateAchat(LocalDate dateAchat) {
        this.dateDachat = dateAchat;
    }

    public int getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(int utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public boolean isConfirmation() {
        return confirmation;
    }

    public void setConfirmation(boolean confirmation) {
        this.confirmation = confirmation;
    }

    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", moyenDeTransport='" + moyenDeTransport + '\'' +
                ", prix=" + prix +
                ", dateAchat=" + dateDachat +
                ", utilisateurId=" + utilisateurId +
                ", destination='" + destination + '\'' +
                ", confirmation=" + confirmation +
                ", walletId=" + walletId +
                '}';
    }
}
