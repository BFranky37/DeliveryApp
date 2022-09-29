package deliveryapp.daoClasses;

import deliveryapp.models.orders.Shipment;

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
