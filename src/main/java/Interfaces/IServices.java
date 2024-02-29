package Interfaces;

import entities.DonneesHistoriques;

import java.util.List;

public interface IServices<T> {
    void addEntity(T t);
    void updateEntity(T t);
    void deleteEntity(T t);

    void deleteEntity(int id);

    List<T> getAllData();

    DonneesHistoriques getEntityById(int id);
}
