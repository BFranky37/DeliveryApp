package deliveryapp.dao_classes;

import deliveryapp.models.orders.Insurance;
import deliveryapp.utils.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface InsuranceDAO extends IBaseDAO<Insurance> {
    @Override
    public Insurance getObjectByID(int id) throws SQLException;

    @Override
    public int getIDbyObject(Insurance p) throws SQLException;

    @Override
    public int create(Insurance p) throws SQLException;

    @Override
    public void update(Insurance p) throws SQLException;
}
