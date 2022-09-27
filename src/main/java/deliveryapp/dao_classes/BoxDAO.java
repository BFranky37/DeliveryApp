package deliveryapp.dao_classes;

import deliveryapp.models.orders.Box;
import deliveryapp.utils.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
