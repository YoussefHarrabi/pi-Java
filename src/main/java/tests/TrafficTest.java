package tests;

import entities.Capteurs;
import services.CapteursServices;
import utils.DataGenerator;

import java.util.List;

public class TrafficTest {
    public static void main(String[] args) {
        // Création d'une instance de CapteursService
        CapteursServices capteursService = new CapteursServices();

        // Génération de capteurs fictifs avec DataGenerator
        List<Capteurs> capteursFictifs = DataGenerator.generateCapteursData(10); // Changer 5 selon le nombre de capteurs souhaité

        // Ajout des capteurs fictifs à la base de données
        for (Capteurs capteur : capteursFictifs) {
            capteursService.addEntity(capteur);
        }

        // Affichage de toutes les données
        List<Capteurs> capteursList = capteursService.getAllData();
        for (Capteurs capteur : capteursList) {
            System.out.println(capteur);
        }
    }
}
