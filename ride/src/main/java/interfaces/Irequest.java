package interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface Irequest<T> {

        void addRequest(T t) throws SQLException, IOException;
        void updateRequest(T t, int id);
        void deleteRequest(int id);
        List<T> getallrequest();
    }


