package tests;

import entities.Capteurs;
import entities.DonneesHistoriques;
import services.CapteursServices;
import services.DonneesHistoriquesServices;
import utils.DataGenerator;
import java.util.ArrayList;


import java.util.List;

public class TrafficTest {
    public static void main(String[] args) {
        // Création d'instances de services
        CapteursServices capteursService = new CapteursServices();
        DonneesHistoriquesServices donneesHistoriquesService = new DonneesHistoriquesServices();

        // Génération de capteurs fictifs avec DataGenerator
        List<Capteurs> capteursFictifs = DataGenerator.generateCapteursData(5);

        // Ajout des capteurs fictifs à la base de données
        for (Capteurs capteur : capteursFictifs) {
            capteursService.addEntity(capteur);
        }

        // Récupération des capteurs depuis la base de données
        List<Capteurs> capteursList = capteursService.getAllData();

        // Génération de données historiques fictives liées aux capteurs
        List<DonneesHistoriques> donneesHistoriquesFictives = new ArrayList<>();
        int nombreDeDonneesHistoriques = 5; // le nombre donnes q'un capteur peut generer

        for (Capteurs capteur : capteursList) {
            List<DonneesHistoriques> donneesCapteur = DataGenerator.generateDonneesHistoriquesDataForCapteur(capteur, nombreDeDonneesHistoriques);
            donneesHistoriquesFictives.addAll(donneesCapteur);
        }


        // Affichage de toutes les données
        List<DonneesHistoriques> donneesHistoriquesList = donneesHistoriquesService.getAllData();
        for (DonneesHistoriques donneesHistoriques : donneesHistoriquesList) {
            System.out.println(donneesHistoriques);
        }
    }
}



