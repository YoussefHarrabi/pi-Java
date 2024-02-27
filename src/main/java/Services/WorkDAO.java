package Services;

import Entities.Work;
import Interfaces.WorkManager;
import Utiles.MyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkDAO implements WorkManager {


    private Connection connection;

    // Constructeur pour initialiser la connexion à la base de données
    public WorkDAO() {
        this.connection = MyConnection.getConnection();
        // Ajoutez une vérification pour s'assurer que la connexion est initialisée correctement
        if (this.connection == null) {
            throw new RuntimeException("La connexion à la base de données n'a pas pu être établie.");
        }
    }
    @Override
    public void addWork(Work work) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO work (location, startdate, enddate, description, isActive) VALUES (?, ?, ?, ?, ?)")) {
            statement.setString(1, work.getLocation());
            statement.setDate(2, Date.valueOf(work.getStartdate()));
            statement.setDate(3, Date.valueOf(work.getEnddate()));
            statement.setString(4, work.getDescription());
            statement.setBoolean(5, work.isActive());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception (journalisation, message utilisateur, etc.)
            throw new RuntimeException("Erreur lors de l'ajout d'un travail.", e);
        }
    }

    @Override
    public void updateWork(Work work) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE work SET location=?, startdate=?, enddate=?, description=?, isActive=? WHERE workID=?")) {
            statement.setString(1, work.getLocation());
            statement.setDate(2, Date.valueOf(work.getStartdate()));
            statement.setDate(3, Date.valueOf(work.getEnddate()));
            statement.setString(4, work.getDescription());
            statement.setBoolean(5, work.isActive());
            statement.setInt(6, work.getWorkID());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception (journalisation, message utilisateur, etc.)
            throw new RuntimeException("Erreur lors de la mise à jour d'un travail.", e);
        }
    }

    @Override
    public List<Work> getAllWorks() {
        List<Work> works = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM work")) {

            while (resultSet.next()) {
                Work work = new Work();
                work.setWorkID(resultSet.getInt("workId"));
                work.setLocation(resultSet.getString("location"));
                work.setStartdate(resultSet.getDate("startdate").toLocalDate());
                work.setEnddate(resultSet.getDate("enddate").toLocalDate());
                work.setDescription(resultSet.getString("description"));
                work.setActive(resultSet.getBoolean("isActive"));

                works.add(work);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception (journalisation, message utilisateur, etc.)
            throw new RuntimeException("Erreur lors de la récupération des travaux.", e);
        }
        return works;
    }

    @Override
    public void deleteWork(int workId) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM work WHERE workID=?")) {
            statement.setInt(1, workId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception (journalisation, message utilisateur, etc.)
            throw new RuntimeException("Erreur lors de la suppression d'un travail.", e);
        }
    }

    public Work getWorkById(int workId){
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM work WHERE workId = ?")) {
            statement.setInt(1, workId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Work work = new Work();
                    work.setWorkID(resultSet.getInt("workId"));
                    work.setLocation(resultSet.getString("location"));
                    work.setStartdate(resultSet.getDate("startDate").toLocalDate());
                    work.setEnddate(resultSet.getDate("endDate").toLocalDate());
                    work.setDescription(resultSet.getString("description"));
                    work.setActive(resultSet.getBoolean("isActive"));
                    return work;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception (journalisation, message utilisateur, etc.)
            throw new RuntimeException("Erreur lors de la récupération du travail par ID.", e);
        }

        return null; // Retourner null si le travail avec l'ID spécifié n'est pas trouvé
    }

    }

