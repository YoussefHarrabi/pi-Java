package Interfaces;
import java.util.List;

public interface Iservices <T>{

    void addEntity(T t);
    void updateEntity(T t);
    void deleteEntity(T t);
    List<T> getAllData();
 }
