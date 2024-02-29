package utils;

import entities.Capteurs;
import entities.DonneesHistoriques;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {

    private static int lastIndex;

    static {
        // Charger la dernière index à partir du fichier s'il existe
        try (BufferedReader reader = new BufferedReader(new FileReader("lastIndex.txt"))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                lastIndex = Integer.parseInt(line);
            }
        } catch (FileNotFoundException e) {

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    // Générer des capteurs fictifs
    public static List<Capteurs> generateCapteursData(int count) {
        List<Capteurs> capteursList = new ArrayList<>();
        Random random = new Random(System.currentTimeMillis()); // Utilisation de la seed actuelle du système

        for (int i = 0; i < count; i++) {
            String nom = generateUniqueName();
            String type = generateRandomType(random);
            float latitude = 33.8869f + (random.nextFloat() * 0.2f); // Latitude centrée autour de 33.8869
            float longitude = 9.5375f + (random.nextFloat() * 0.2f); // Longitude centrée autour de 9.5375
            String dateInstallation = generateRandomDate(random); // Date d'installation aléatoire

            Capteurs capteur = new Capteurs(nom, type, latitude, longitude, dateInstallation);
            capteursList.add(capteur);
        }

        // Sauvegarder le dernier index dans le fichier
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("lastIndex.txt"))) {
            writer.write(String.valueOf(lastIndex));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return capteursList;
    }

    private static String generateUniqueName() {
        lastIndex++;
        return "Capteur_" + lastIndex;
    }

    // Générer des données historiques fictives
    public static List<DonneesHistoriques> generateDonneesHistoriquesDataForCapteur(Capteurs capteur, int count) {
        List<DonneesHistoriques> donneesList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            String timestamp = generateRandomTimestamp(random);
            int niveauEmbouteillage = random.nextInt(5) + 1; // Niveau entre 1 et 5
            String alerte = (random.nextBoolean()) ? "Oui" : "Non"; // Aléatoire Oui/Non
            String conditionsMeteo = generateRandomConditionsMeteo(random);

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


    private static String generateRandomTimestamp(Random random) {
        int year = 2022;
        int month = random.nextInt(12) + 1;
        int day = random.nextInt(28) + 1;
        int hour = random.nextInt(24);
        int minute = random.nextInt(60);
        int second = random.nextInt(60);

        return String.format("%04d-%02d-%02d %02d:%02d:%02d", year, month, day, hour, minute, second);
    }

    private static String generateRandomConditionsMeteo(Random random) {
        String[] conditions = {"Claires", "Nuageuses", "Pluvieuses", "Orageuses", "Neigeuses"};
        return conditions[random.nextInt(conditions.length)];
    }

    private static String generateRandomType(Random random) {
        String[] types = {"LoopSense", "CamTrack", "WaveSense"};
        return types[random.nextInt(types.length)];
    }

    private static String generateRandomDate(Random random) {
        int year = 2021 + random.nextInt(2);
        int month = random.nextInt(12) + 1;
        int day = random.nextInt(28) + 1;

        return String.format("%04d-%02d-%02d", year, month, day);
    }
}
