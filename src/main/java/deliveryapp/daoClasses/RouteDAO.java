package deliveryapp.daoClasses;

import deliveryapp.models.vehicles.Route;

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
