package deliveryapp.services.jdbc;

import deliveryapp.daoClasses.ShipmentStatusDAO;
import deliveryapp.daoClasses.java.ShipmentStatusDAOimpl;
import deliveryapp.models.orders.Shipment;
import deliveryapp.models.orders.ShipmentStatus;
import deliveryapp.services.ShipmentStatusService;
import deliveryapp.utils.threads.DropOff;
import deliveryapp.utils.threads.Travel;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class ShipmentStatusServiceImpl implements ShipmentStatusService {
    private final ShipmentStatusDAO shipmentStatusDAOimpl = new ShipmentStatusDAOimpl();;
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

    public ShipmentStatus getShipmentStatusByShipment(int id) {
        try {
            return shipmentStatusDAOimpl.getObjectByID(shipmentStatusDAOimpl.getIDbyShipment(id));
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
    public void createShipmentStatus(Shipment u) {
        ShipmentStatus shipmentStatus = new ShipmentStatus(u.getId());
        try {
            shipmentStatus.setId(shipmentStatusDAOimpl.create(shipmentStatus));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void createShipmentStatus(int shipmentID) {
        ShipmentStatus shipmentStatus = new ShipmentStatus(shipmentID);
        try {
            shipmentStatus.setId(shipmentStatusDAOimpl.create(shipmentStatus));
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

    public void shipmentTravel(Shipment s) {
        Object Lock1 = new Object();
        Object Lock2 = new Object();
        Thread travel = new Thread(new Travel(s, Lock1, Lock2));
        Thread receivePackage = new Thread(new DropOff(s, Lock1, Lock2));
        travel.start();
        receivePackage.start();
        LOGGER.info("Delivery vehicle has begun it's transport");
    }
}
