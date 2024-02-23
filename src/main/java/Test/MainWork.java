package Test;

import Entities.Work;
import Services.WorkDAO;

import java.time.LocalDate;
import java.util.List;

public class MainWork {
    public static void main(String[] args) {
        // Création d'une instance de WorkDAO
        WorkDAO workDAO = new WorkDAO();

        // Création d'un nouveau travail
        Work newWork = new Work();
        newWork.setLocation("gafsa");
        newWork.setStartdate(LocalDate.now());
        newWork.setEnddate(LocalDate.now().plusDays(7));
        newWork.setDescription("okay");
        newWork.setActive(true);

        // Ajout du travail à la base de données
        //workDAO.addWork(newWork);

        // Récupération de la liste de tous les travaux
        List<Work> allWorks = workDAO.getAllWorks();

        // Affichage des travaux
        System.out.println("Liste de tous les travaux :");
        for (Work work : allWorks) {
            System.out.println("ID: " + work.getWorkID());
            System.out.println("Emplacement: " + work.getLocation());
            System.out.println("Date de début: " + work.getStartdate());
            System.out.println("Date de fin: " + work.getEnddate());
            System.out.println("Description: " + work.getDescription());
            System.out.println("Actif: " + work.isActive());
            System.out.println("-----------");
        }
/*
        // Vérifiez si la liste contient au moins un travail
        if (!allWorks.isEmpty()) {
            // Récupérez le premier travail de la liste (indice 0)
            Work existingWork = allWorks.get(1);

            // Affichez les détails du travail avant la mise à jour
            System.out.println("Avant la mise à jour : " + existingWork);

            // Modifiez les propriétés du travail
            existingWork.setLocation("ArianaSoghra");
            existingWork.setDescription("hofra");

            // Appelez la méthode pour mettre à jour le travail
            //workDAO.updateWork(existingWork);

            // Récupérez le travail mis à jour depuis la base de données en utilisant l'ID
            Work updatedWork = workDAO.getWorkById(existingWork.getWorkID());

            // Affichez les détails du travail après la mise à jour
            System.out.println("Après la mise à jour : " + updatedWork);
        } else {
            System.out.println("La liste de travaux est vide.");
        }
*/
        if (!allWorks.isEmpty()) {
            // Récupérez le premier travail de la liste (indice 0)
            Work firstWork = allWorks.get(2);
            // Suppression du premier travail par son ID
            workDAO.deleteWork(firstWork.getWorkID());
            // Affichage des travaux après la suppression

            System.out.println("Liste de tous les travaux :");
            for (Work work : allWorks) {
                System.out.println("ID: " + work.getWorkID());
                System.out.println("Emplacement: " + work.getLocation());
                System.out.println("Date de début: " + work.getStartdate());
                System.out.println("Date de fin: " + work.getEnddate());
                System.out.println("Description: " + work.getDescription());
                System.out.println("Actif: " + work.isActive());
                System.out.println("-----------");
            }


        }
    }
}

