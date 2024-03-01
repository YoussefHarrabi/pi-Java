package Interfaces;

import java.util.List;

public interface IServices<T> {
    void addEntity(T t);
    void updateEntity(T t);
    void deleteEntity(T t);

    void deleteEntity(int id);

    List<T> getAllData();

    T getEntityById(int id);
}

