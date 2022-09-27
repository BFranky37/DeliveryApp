package deliveryapp.services.jdbc;

import deliveryapp.dao_classes.java.ShipmentStatusDAOimpl;
import deliveryapp.models.orders.ShipmentStatus;
import deliveryapp.services.ShipmentStatusService;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class ShipmentStatusServiceImpl implements ShipmentStatusService {
    private final ShipmentStatusDAOimpl shipmentStatusDAOimpl = new ShipmentStatusDAOimpl();;
    private static final Logger LOGGER = Logger.getLogger(ShipmentStatusServiceImpl.class.getName());
    private static final Scanner input = new Scanner(System.in);

    @Override
    public ShipmentStatus getShipmentStatusByID(int id) {
        try {
            return shipmentStatusDAOimpl.getObjectByID(id);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public int getIDbyShipmentStatus(ShipmentStatus u) {
        try {
            return shipmentStatusDAOimpl.getIDbyObject(u);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    @Override
    public void createShipmentStatus(ShipmentStatus u) {
        try {
            u.setId(shipmentStatusDAOimpl.create(u));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void updateShipmentStatus(ShipmentStatus u) {
        try {
            shipmentStatusDAOimpl.update(u);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
