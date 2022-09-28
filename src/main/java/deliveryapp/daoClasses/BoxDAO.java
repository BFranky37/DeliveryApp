package deliveryapp.daoClasses;

import deliveryapp.models.orders.Box;

import java.sql.SQLException;

public interface BoxDAO extends IBaseDAO<Box> {
    @Override
    public Box getObjectByID(int id) throws SQLException;

    @Override
    public int getIDbyObject(Box p) throws SQLException;

    @Override
    public int create(Box p) throws SQLException;

    @Override
    public void update(Box p) throws SQLException;
}
