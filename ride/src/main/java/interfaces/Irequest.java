package interfaces;

import java.util.List;

public interface Irequest<T> {

        void addRequest(T t);
        void updateRequest(T t, int id);
        void deleteRequest(int id);
        List<T> getallrequest();
    }


