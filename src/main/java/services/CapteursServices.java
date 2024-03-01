package services;

import entities.Capteurs;
import entities.DonneesHistoriques;
import Interfaces.IServices;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import utils.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class CapteursServices implements IServices<Capteurs> {
    private List<Capteurs> listeCapteurs = new ArrayList<>();


    public List<Capteurs> rechercherParNom(String nom) {
        // Récupérer la liste complète des capteurs depuis la base de données
        List<Capteurs> capteursList = getAllData();

        List<Capteurs> capteursTrouves = new ArrayList<>();

        // Vous pouvez ajuster la condition de recherche selon vos besoins
        for (Capteurs capteur : capteursList) {
            if (capteur.getNom() != null && capteur.getNom().equalsIgnoreCase(nom)) {
                capteursTrouves.add(capteur);
            }
        }

        return capteursTrouves;
    }




    @Override
    public void addEntity(Capteurs capteurs) {
        // Vérifier si le nom du capteur existe déjà
        if (!isCapteurNameExists(capteurs.getNom())) {
            String requete = "INSERT INTO capteurs (Nom, Type, Latitude, Longitude, DateInstallation) VALUES (?, ?, ?, ?, ?)";
            try {
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
                pst.setString(1, capteurs.getNom());
                pst.setString(2, capteurs.getType());
                pst.setFloat(3, capteurs.getLatitude());
                pst.setFloat(4, capteurs.getLongitude());
                pst.setString(5, capteurs.getDateInstallation());
                pst.executeUpdate();



            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {

            afficherNotification("Le nom du capteur existe déjà. Veuillez choisir un nom différent.");
        }
    }

    // Méthode pour afficher une notification dans l'interface
    public void afficherNotification(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notification");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }


    // Méthode pour vérifier si le nom du capteur existe déjà
    private boolean isCapteurNameExists(String nom) {
        String requete = "SELECT COUNT(*) FROM capteurs WHERE Nom = ?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, nom);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    @Override
    public void updateEntity(Capteurs capteurs) {
        String requete = "UPDATE capteurs SET Nom=?, Type=?, Latitude=?, Longitude=?, DateInstallation=? WHERE idCapteur=?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, capteurs.getNom());
            pst.setString(2, capteurs.getType());
            pst.setFloat(3, capteurs.getLatitude());
            pst.setFloat(4, capteurs.getLongitude());
            pst.setString(5, capteurs.getDateInstallation());
            pst.setInt(6, capteurs.getIdCapteur());

            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Capteur mis à jour avec succès");
            } else {
                System.out.println("Aucun capteur mis à jour");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //obtenir la liste des capteurs
    public static List<Capteurs> getCapteursFromDatabase() {
        // Création d'une instance de CapteursService
        CapteursServices capteursService = new CapteursServices();
        // Récupération des capteurs depuis la base de données
        return capteursService.getAllData();
    }


    @Override
    public void deleteEntity(Capteurs capteurs) {
        String requete = "DELETE FROM capteurs WHERE idCapteur=?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, capteurs.getIdCapteur());
            int rowsDeleted = pst.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Capteur supprimé avec succès");
            } else {
                System.out.println("Aucun capteur supprimé");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteEntity(int id) {

    }

    public List<Capteurs> getCapteursProches(int capteurId, double distanceMax) {
        Capteurs capteurReference = getEntityById(capteurId); // Méthode pour récupérer un capteur par son ID

        if (capteurReference != null) {
            List<Capteurs> capteursProches = new ArrayList<>();

            // Récupérez tous les capteurs de la base de données (vous pouvez optimiser selon votre situation)
            List<Capteurs> tousLesCapteurs = getAllData();

            for (Capteurs capteur : tousLesCapteurs) {
                if (capteur.getIdCapteur() != capteurId) { // Évitez de comparer avec le capteur de référence lui-même
                    double distance = haversine(
                            capteurReference.getLatitude(), capteurReference.getLongitude(),
                            capteur.getLatitude(), capteur.getLongitude()
                    );

                    if (distance <= distanceMax) {
                        capteursProches.add(capteur);
                    }
                }
            }

            return capteursProches;
        }

        return new ArrayList<>(); // Retournez une liste vide si le capteur de référence n'est pas trouvé
    }

    // Formule de Haversine pour calculer la distance entre deux points géographiques
    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371; // Rayon de la Terre en kilomètres
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }


    @Override
    public List<Capteurs> getAllData() {
        List<Capteurs> data = new ArrayList<>();
        String requete = "SELECT * FROM capteurs";
        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Capteurs c = new Capteurs();
                c.setIdCapteur(rs.getInt("idCapteur"));
                c.setNom(rs.getString("Nom"));
                c.setType(rs.getString("Type"));
                c.setLatitude(rs.getFloat("Latitude"));
                c.setLongitude(rs.getFloat("Longitude"));
                c.setDateInstallation(rs.getString("DateInstallation"));
                data.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }

    @Override
    public Capteurs getEntityById(int id) {
        String requete = "SELECT * FROM capteurs WHERE idCapteur = ?";

        try (PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Capteurs capteur = new Capteurs();
                    capteur.setIdCapteur(rs.getInt("idCapteur"));
                    capteur.setNom(rs.getString("Nom"));
                    capteur.setType(rs.getString("Type"));
                    capteur.setLatitude(rs.getFloat("Latitude"));
                    capteur.setLongitude(rs.getFloat("Longitude"));
                    capteur.setDateInstallation(rs.getString("DateInstallation"));
                    return capteur;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null; // Retourne null si le capteur n'est pas trouvé
    }



    public List<Capteurs> rechercherParCritere(String critere, String valeurRecherche) {
        List<Capteurs> capteurs = getAllData(); // Récupérez tous les capteurs depuis votre source de données
        List<Capteurs> resultats = new ArrayList<>();

        // Utilisez les critères pour filtrer la liste
        for (Capteurs capteur : capteurs) {
            switch (critere) {
                case "Nom":
                    if (capteur.getNom().toLowerCase().contains(valeurRecherche.toLowerCase())) {
                        resultats.add(capteur);
                    }
                    break;
                case "Type":
                    if (capteur.getType().toLowerCase().contains(valeurRecherche.toLowerCase())) {
                        resultats.add(capteur);
                    }
                    break;
                case "Latitude":
                    if (String.valueOf(capteur.getLatitude()).contains(valeurRecherche)) {
                        resultats.add(capteur);
                    }
                    break;
                case "Longitude":
                    if (String.valueOf(capteur.getLongitude()).contains(valeurRecherche)) {
                        resultats.add(capteur);
                    }
                    break;

            }
        }

        return resultats;
    }





}
