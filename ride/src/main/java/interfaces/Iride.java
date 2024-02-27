package interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
public interface Iride <T>{
    void addEntity(T t) throws SQLException, IOException;
    void updateEntity(T t, int id);
    void deleteEntity(int id);
    List<T> getallData();
}
