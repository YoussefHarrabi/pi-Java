package interfaces;

import java.util.List;

public interface Irequest<T> {

        void addEntity(T t);
        void updateEntity(T t, int id);
        void deleteEntity(int id);
        List<T> getallData();
    }


