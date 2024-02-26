package services;

import entities.DonneesHistoriques;
import java.util.List;

public interface DonneesHistoriquesService {
    List<DonneesHistoriques> getAllDonneesHistoriques();
    DonneesHistoriques getDonneesHistoriquesById(int id);
    void addDonneesHistoriques(DonneesHistoriques donneesHistoriques);
    void updateDonneesHistoriques(DonneesHistoriques donneesHistoriques);
    void deleteDonneesHistoriques(int id);
}