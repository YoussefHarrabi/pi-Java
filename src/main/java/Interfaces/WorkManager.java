package Interfaces;

import Entities.Work;

import java.util.List;

public interface WorkManager {
    // Ajoute un travail à la base de données
    void addWork(Work work);

    // Met à jour les détails d'un travail existant
    void updateWork(Work work);

    // Récupère la liste de tous les travaux
    List<Work> getAllWorks();

    // Supprime un travail de la base de données en utilisant son ID
    void deleteWork(int workId);
    Work getWorkById(int workId);
}
