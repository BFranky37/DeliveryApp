package deliveryapp.dao_classes;

import deliveryapp.models.vehicles.Route;
import deliveryapp.utils.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface RouteDAO extends IBaseDAO<Route> {
    @Override
    public Route getObjectByID(int id) throws SQLException;

    @Override
    public int getIDbyObject(Route p) throws SQLException;

    @Override
    public int create(Route p) throws SQLException;

    @Override
    public void update(Route p) throws SQLException;
}
