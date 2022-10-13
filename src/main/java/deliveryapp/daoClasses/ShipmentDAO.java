package deliveryapp.daoClasses;

import deliveryapp.models.orders.Shipment;
import deliveryapp.models.people.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ShipmentDAO extends IBaseDAO<Shipment> {
    @Override
    public Shipment getObjectByID(int id) throws SQLException;

    public ArrayList<Shipment> getShipmentsForReciept(User u, int limit) throws SQLException;

    @Override
    public int getIDbyObject(Shipment p) throws SQLException;

    @Override
    public int create(Shipment p) throws SQLException;

    @Override
    public void update(Shipment p) throws SQLException;
}
