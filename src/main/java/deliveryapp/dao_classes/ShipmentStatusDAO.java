package deliveryapp.dao_classes;

import deliveryapp.models.orders.ShipmentStatus;
import deliveryapp.utils.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ShipmentStatusDAO extends IBaseDAO<ShipmentStatus> {
    @Override
    public ShipmentStatus getObjectByID(int id) throws SQLException;

    @Override
    public int getIDbyObject(ShipmentStatus p) throws SQLException;

    @Override
    public int create(ShipmentStatus p) throws SQLException;

    @Override
    public void update(ShipmentStatus p) throws SQLException;
}
