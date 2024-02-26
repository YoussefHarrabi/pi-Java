package interfaces;

import java.util.List;

public interface IServices <T>{
    void addEntity(T t);
    void updateEntity(T t,int id);
    void deleteEntity(T t);
    List<T> getAllData();
}
