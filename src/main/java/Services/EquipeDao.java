package Services;

import Entities.Equipe;
import Entities.Work;
import Interfaces.EquipeManager;
import Utiles.MyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipeDao implements EquipeManager {

    private Connection connection;

    // Constructeur pour initialiser la connexion à la base de données
    public EquipeDao() {
        this.connection = MyConnection.getConnection();
        // Ajoutez une vérification pour s'assurer que la connexion est initialisée correctement
        if (this.connection == null) {
            throw new RuntimeException("La connexion à la base de données n'a pas pu être établie.");
        }
    }


    @Override
    public void addEquipe(Equipe equipe) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO equipe (relationTo,nbrPersonne,worke) VALUES ( ?, ?, ?)")) {
            statement.setString(1, equipe.getRelationTo());
            statement.setInt(2, equipe.getNbrPersonne());
            statement.setInt(3, equipe.getWorke().getWorkID());



            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception (journalisation, message utilisateur, etc.)
            throw new RuntimeException("Erreur lors de l'ajout d'un travail.", e);
        }

    }
    @Override
    public List<Equipe> getAllEquipes() {
        List<Equipe> equipes = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM equipe")) {

            while (resultSet.next()) {
                //ResultSet resultSet2 = statement.executeQuery("SELECT worke FROM equipe WHERE id = " + resultSet.getInt(1));
               // System.out.println(resultSet2);
                System.out.println(resultSet.getInt(4));
                Work w=getWorkById(resultSet.getInt(4));
                Equipe equipe=new Equipe(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),w);

                equipes.add(equipe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception (journalisation, message utilisateur, etc.)
            throw new RuntimeException("Erreur lors de la récupération des travaux.", e);
        }
        return equipes;
    }

    private Work getWorkById(int workId) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM work WHERE workID= ?")) {
            statement.setInt(1, workId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Work(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getDate(3).toLocalDate(),
                            resultSet.getDate(4).toLocalDate(),
                            resultSet.getString(5),
                            resultSet.getBoolean(6)
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving Work by ID", e);
        }
        return null;
    }

    @Override
    public void modifierEquipe(Equipe equipe) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE equipe SET relationTo=?, nbrPersonne=?, worke=? WHERE id=?")) {
            statement.setString(1, equipe.getRelationTo());
            statement.setInt(2, equipe.getNbrPersonne());
            statement.setInt(3, equipe.getWorke().getWorkID());
            statement.setInt(4, equipe.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception (logging, user message, etc.)
            throw new RuntimeException("Erreur lors de la modification d'une équipe.", e);
        }
    }

    @Override
    public void deleteTeam(int id) {


            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM equipe WHERE id=?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                // Gérer l'exception (journalisation, message utilisateur, etc.)
                throw new RuntimeException("Erreur lors de la suppression d'un travail.", e);
            }
        }
    }


