package controllers;

import entities.DonneesHistoriques;
import services.DonneesHistoriquesServices;

import java.util.List;

public class DonneesHistoriquesController {

    private final DonneesHistoriquesServices donneesHistoriquesService;

    public DonneesHistoriquesController(DonneesHistoriquesServices donneesHistoriquesService) {
        this.donneesHistoriquesService = donneesHistoriquesService;
    }

    public List<DonneesHistoriques> getAllDonneesHistoriques() {
        return donneesHistoriquesService.getAllData();
    }

    public DonneesHistoriques getDonneesHistoriquesById(int id) {
        return donneesHistoriquesService.getEntityById(id);
    }

    public void ajouterDonneesHistoriques(DonneesHistoriques donneesHistoriques) {
        donneesHistoriquesService.addEntity(donneesHistoriques);
    }

    public void modifierDonneesHistoriques(DonneesHistoriques donneesHistoriques) {
        donneesHistoriquesService.updateEntity(donneesHistoriques);
    }

    public void supprimerDonneesHistoriques(int id) {
        donneesHistoriquesService.deleteEntity(id);
    }


}


