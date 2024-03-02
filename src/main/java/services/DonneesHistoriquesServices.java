package services;

import Interfaces.IServices;
import entities.DonneesHistoriques;
import utils.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DonneesHistoriquesServices implements IServices<DonneesHistoriques> {

    @Override
    public void addEntity(DonneesHistoriques donneesHistoriques) {
        String requete = "INSERT INTO donneeshistoriques (idCapteur, timestamp, niveauEmbouteillage, alerte, conditionsMeteo) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, donneesHistoriques.getIdCapteur());
            pst.setString(2, donneesHistoriques.getTimestamp());
            pst.setInt(3, donneesHistoriques.getNiveauEmbouteillage());
            pst.setString(4, donneesHistoriques.getAlerte());
            pst.setString(5, donneesHistoriques.getConditionsMeteo());
            pst.executeUpdate();
            System.out.println("Données historiques ajoutées");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void updateEntity(DonneesHistoriques donneesHistoriques) {

    }

    @Override
    public void deleteEntity(DonneesHistoriques donneesHistoriques) {

    }

    @Override
    public void deleteEntity(int id) {

    }

    @Override
    public List<DonneesHistoriques> getAllData() {
        List<DonneesHistoriques> data = new ArrayList<>();
        String requete = "SELECT * FROM donneeshistoriques";
        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                DonneesHistoriques donnees = new DonneesHistoriques();
                donnees.setId(rs.getInt("id"));
                donnees.setIdCapteur(rs.getInt("idCapteur"));
                donnees.setTimestamp(rs.getString("timestamp"));
                donnees.setNiveauEmbouteillage(rs.getInt("niveauEmbouteillage"));
                donnees.setAlerte(rs.getString("alerte"));
                donnees.setConditionsMeteo(rs.getString("conditionsMeteo"));
                data.add(donnees);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }



    @Override
    public DonneesHistoriques getEntityById(int id) {
        // Logique pour récupérer une donnée historique de la source de données par ID
        return null;
    }

    // Méthode pour récupérer les données historiques par ID de capteur
    public List<DonneesHistoriques> getHistoriquesByCapteurId(int idCapteur) {
        List<DonneesHistoriques> historiques = new ArrayList<>();
        String requete = "SELECT * FROM donneeshistoriques WHERE idCapteur = ?";

        try (PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete)) {
            pst.setInt(1, idCapteur);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    DonneesHistoriques donnee = new DonneesHistoriques();
                    donnee.setId(rs.getInt("id"));
                    donnee.setIdCapteur(rs.getInt("idCapteur"));
                    donnee.setTimestamp(rs.getString("timestamp"));
                    donnee.setNiveauEmbouteillage(rs.getInt("niveauEmbouteillage"));
                    donnee.setAlerte(rs.getString("alerte"));
                    donnee.setConditionsMeteo(rs.getString("conditionsMeteo"));
                    historiques.add(donnee);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return historiques;
    }


    public List<DonneesHistoriques> getDerniersEnregistrementsEmbouteillageSuperieurTrois() {
        // Récupérer toutes les données historiques
        List<DonneesHistoriques> donneesHistoriques = getAllData();

        // Map pour stocker les derniers enregistrements de chaque capteur avec un niveau d'embouteillage > 3
        Map<Integer, List<DonneesHistoriques>> derniersEnregistrementsParCapteur = new HashMap<>();

        // Parcourir les données historiques et mettre à jour la map avec les derniers enregistrements de chaque capteur
        for (DonneesHistoriques donnee : donneesHistoriques) {
            // Vérifier si le niveau d'embouteillage est supérieur à 3
            if (donnee.getNiveauEmbouteillage() > 3) {
                // Vérifier si le capteur est déjà présent dans la map
                if (derniersEnregistrementsParCapteur.containsKey(donnee.getIdCapteur())) {
                    // Récupérer la liste des enregistrements actuels pour ce capteur
                    List<DonneesHistoriques> enregistrementsActuels = derniersEnregistrementsParCapteur.get(donnee.getIdCapteur());
                    // Vérifier si cet enregistrement est plus récent que le dernier enregistré
                    if (enregistrementsActuels.isEmpty() || donnee.getTimestamp().compareTo(enregistrementsActuels.get(0).getTimestamp()) > 0) {
                        // Ajouter cet enregistrement au début de la liste (les données sont triées par ordre décroissant de timestamp)
                        enregistrementsActuels.add(0, donnee);
                        // S'assurer que la liste ne dépasse pas 3 enregistrements
                        if (enregistrementsActuels.size() > 3) {
                            enregistrementsActuels.remove(3);
                        }
                    }
                } else {
                    // Si le capteur n'est pas encore présent dans la map, créer une nouvelle liste et y ajouter cet enregistrement
                    List<DonneesHistoriques> nouveauxEnregistrements = new ArrayList<>();
                    nouveauxEnregistrements.add(donnee);
                    derniersEnregistrementsParCapteur.put(donnee.getIdCapteur(), nouveauxEnregistrements);
                }
            }
        }

        // Construire la liste finale des derniers enregistrements de chaque capteur
        List<DonneesHistoriques> derniersEnregistrements = new ArrayList<>();
        for (List<DonneesHistoriques> enregistrements : derniersEnregistrementsParCapteur.values()) {
            derniersEnregistrements.addAll(enregistrements);
        }

        return derniersEnregistrements;
    }


}


