package deliveryapp.dao_classes;

import deliveryapp.models.orders.Shipment;
import deliveryapp.utils.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ShipmentDAO extends IBaseDAO<Shipment> {
    @Override
    public Shipment getObjectByID(int id) throws SQLException;

    @Override
    public int getIDbyObject(Shipment p) throws SQLException;

    @Override
    public int create(Shipment p) throws SQLException;

    @Override
    public void update(Shipment p) throws SQLException;
}
