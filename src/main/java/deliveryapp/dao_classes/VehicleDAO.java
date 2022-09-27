package deliveryapp.dao_classes;

import deliveryapp.models.vehicles.Vehicle;
import deliveryapp.utils.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface VehicleDAO extends IBaseDAO<Vehicle> {
    @Override
    public Vehicle getObjectByID(int id) throws SQLException;

    @Override
    public int getIDbyObject(Vehicle p) throws SQLException;

    @Override
    public int create(Vehicle p) throws SQLException;

    @Override
    public void update(Vehicle p) throws SQLException;
}
