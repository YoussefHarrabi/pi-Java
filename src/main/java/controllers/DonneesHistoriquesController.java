package controllers;

import entities.DonneesHistoriques;
import services.DonneesHistoriquesService;

import java.util.List;

public class DonneesHistoriquesController {
    private final DonneesHistoriquesService donneesHistoriquesService;

    public DonneesHistoriquesController(DonneesHistoriquesService donneesHistoriquesService) {
        this.donneesHistoriquesService = donneesHistoriquesService;
    }

    public List<DonneesHistoriques> getAllDonneesHistoriques() {
        return donneesHistoriquesService.getAllDonneesHistoriques();
    }

    public DonneesHistoriques getDonneesHistoriquesById(int id) {
        return donneesHistoriquesService.getDonneesHistoriquesById(id);
    }

    public void ajouterDonneesHistoriques(DonneesHistoriques donneesHistoriques) {
        donneesHistoriquesService.addDonneesHistoriques(donneesHistoriques);
    }

    public void modifierDonneesHistoriques(DonneesHistoriques donneesHistoriques) {
        donneesHistoriquesService.updateDonneesHistoriques(donneesHistoriques);
    }

    public void supprimerDonneesHistoriques(int id) {
        donneesHistoriquesService.deleteDonneesHistoriques(id);
    }

    // Vous pouvez ajouter des méthodes supplémentaires pour des fonctionnalités spécifiques de l'interface utilisateur.
}
