package deliveryapp.services;

import deliveryapp.dao_classes.ShipmentDAO;
import deliveryapp.dao_classes.UserDAO;
import deliveryapp.models.orders.Shipment;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class ShipmentServiceImpl implements ShipmentService {

    private ShipmentDAO shipmentDAO = new ShipmentDAO();;
    private static final Logger LOGGER = Logger.getLogger(ShipmentServiceImpl.class.getName());
    private static final Scanner input = new Scanner(System.in);

    @Override
    public Shipment shipPackage() {
        return null;
    }
}
