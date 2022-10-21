package deliveryapp.daoClasses;

import deliveryapp.models.orders.ShipmentStatus;

import java.sql.SQLException;

public interface ShipmentStatusDAO extends IBaseDAO<ShipmentStatus> {
    @Override
    public ShipmentStatus getObjectByID(int id) throws SQLException;

    @Override
    public int getIDbyObject(ShipmentStatus p) throws SQLException;

    int getIDbyShipment(int shipmentID) throws SQLException;

    @Override
    public int create(ShipmentStatus p) throws SQLException;

    @Override
    public void update(ShipmentStatus p) throws SQLException;
}
