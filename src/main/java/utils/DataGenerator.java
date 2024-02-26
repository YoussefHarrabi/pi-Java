package utils;

import entities.Capteurs;
import entities.DonneesHistoriques;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {

    // Générer des capteurs fictifs
    public static List<Capteurs> generateCapteursData(int count) {
        List<Capteurs> capteursList = new ArrayList<>();
        Random random = new Random(System.currentTimeMillis()); // Utilisation de la seed actuelle du système

        for (int i = 0; i < count; i++) {
            String nom = "Capteur" + (i + 1);
            String type = "Type" + (i % 3 + 1); // Trois types différents
            float latitude = 33.8869f + (random.nextFloat() * 0.2f); // Latitude centrée autour de 33.8869
            float longitude = 9.5375f + (random.nextFloat() * 0.2f); // Longitude centrée autour de 9.5375
            String dateInstallation = "2022-02-24"; // Date d'installation fixe pour l'exemple

            Capteurs capteur = new Capteurs(nom, type, latitude, longitude, dateInstallation);
            capteursList.add(capteur);
        }

        return capteursList;
    }

    // Générer des données historiques fictives
    public static List<DonneesHistoriques> generateDonneesHistoriquesData(List<Capteurs> capteursList) {
        List<DonneesHistoriques> donneesList = new ArrayList<>();
        Random random = new Random();

        for (Capteurs capteur : capteursList) {
            String timestamp = generateTimestamp(random);
            String niveauEmbouteillage = Integer.toString(random.nextInt(5) + 1); // Niveau entre 1 et 5
            String alerte = (random.nextBoolean()) ? "Oui" : "Non"; // Aléatoire Oui/Non
            String conditionsMeteo = generateConditionsMeteo(random);

            DonneesHistoriques donnees = new DonneesHistoriques();
            donnees.setIdCapteur(capteur.getIdCapteur());
            donnees.setTimestamp(timestamp);
            donnees.setNiveauEmbouteillage(niveauEmbouteillage);
            donnees.setAlerte(alerte);
            donnees.setConditionsMeteo(conditionsMeteo);

            donneesList.add(donnees);
        }

        return donneesList;
    }

    private static String generateTimestamp(Random random) {
        // Générez un timestamp avec une date aléatoire dans une fenêtre de temps
        // Vous pouvez personnaliser cela en fonction de vos besoins
        int day = random.nextInt(28) + 1; // Jour entre 1 et 28
        int hour = random.nextInt(24); // Heure entre 0 et 23
        int minute = random.nextInt(60); // Minute entre 0 et 59
        int second = random.nextInt(60); // Seconde entre 0 et 59

        return String.format("2022-02-%02d %02d:%02d:%02d", day, hour, minute, second);
    }

    private static String generateConditionsMeteo(Random random) {
        // Générez des conditions météo aléatoires
        String[] conditions = {"Claires", "Nuageuses", "Pluvieuses", "Orageuses", "Neigeuses"};
        return conditions[random.nextInt(conditions.length)];
    }


    private static String generateUniqueName(int index) {
        // Utilisez un mécanisme pour garantir l'unicité des noms (par exemple, en ajoutant un index)
        return "Capteur_" + index;
    }
}
