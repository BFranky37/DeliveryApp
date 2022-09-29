package deliveryapp.daoClasses;

import java.sql.SQLException;

public interface IBaseDAO<T> {
    public T getObjectByID(int id) throws SQLException;

    public int getIDbyObject(T t) throws SQLException;

    public int create(T t) throws SQLException;

    public void update(T t) throws SQLException;
}
