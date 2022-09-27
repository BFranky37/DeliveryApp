package deliveryapp.dao_classes;

import deliveryapp.models.vehicles.VehicleType;
import deliveryapp.utils.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface VehicleTypeDAO extends IBaseDAO<VehicleType> {
        @Override
    public VehicleType getObjectByID(int id) throws SQLException;

    @Override
    public int getIDbyObject(VehicleType p) throws SQLException;

    @Override
    public int create(VehicleType p) throws SQLException;

    @Override
    public void update(VehicleType p) throws SQLException;
}
