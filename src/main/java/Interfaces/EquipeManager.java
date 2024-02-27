package Interfaces;

import Entities.Equipe;

import java.util.List;

public interface EquipeManager {

    void addEquipe(Equipe e);

     List<Equipe> getAllEquipes();
    void modifierEquipe(Equipe equipe);
    void deleteTeam(int id);


}
