package deliveryapp.services.jdbc;

import deliveryapp.daoClasses.ShipmentDAO;
import deliveryapp.daoClasses.java.ShipmentDAOimpl;
import deliveryapp.models.orders.Insurance;
import deliveryapp.models.orders.Shipment;
import deliveryapp.models.orders.Package;
import deliveryapp.models.people.Discount;
import deliveryapp.models.people.Profile;
import deliveryapp.models.people.User;
import deliveryapp.services.DiscountService;
import deliveryapp.services.ShipmentService;
import deliveryapp.utils.ValidateInput;
import deliveryapp.utils.exceptions.InvalidInputException;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ShipmentServiceImpl implements ShipmentService {
    private ShipmentDAO shipmentDAO = new ShipmentDAOimpl();;
    private static final Logger LOGGER = Logger.getLogger(ShipmentServiceImpl.class.getName());
    private static final Scanner input = new Scanner(System.in);

    int numShipments;

    public ShipmentServiceImpl() {
        numShipments = 0;
    }

    @Override
    public Shipment shipPackage(User sender, Profile recipient, Package shippingPackage, Insurance insuranceType) {
        boolean priority = false;
        boolean valid = false;
        do {
            try {
                valid = false;
                LOGGER.info("Would you like to pay for priority shipping to ensure your package reaches the destination quickly? (y/n): ");
                priority = ValidateInput.validateYesNo(input.nextLine());
                valid = true;
            } catch (InvalidInputException e) {
                LOGGER.warn(e.getMessage() + "Invalid yes/no input");
                LOGGER.info("Please enter a valid input (y/n)");
            }
        } while (!valid);
        if (priority) {
            LOGGER.info("Priority Shipping added.");
        }

        Shipment shipment = new Shipment(sender.getId(), recipient.getId(), shippingPackage.getId(), insuranceType.getId(), priority);

        LOGGER.info("The final price for this shipment comes out to $" + Math.round(shipment.getPrice() * 100.0) / 100.0);
        DiscountService discountService = new DiscountServiceImpl();
        List<Discount> discounts = discountService.getDiscountsByUser(sender.getId());
        for (Discount d : discounts) {
            LOGGER.info("with " + d.getName() + " discount of " + d.getDiscountRate() * 100 + "% applied");
        }

        boolean shipmentFinalized = false;
        do {
            try {
                valid = false;
                LOGGER.info("Would you like to finalize this shipment? (y/n)");
                shipmentFinalized = ValidateInput.validateYesNo(input.nextLine());
                valid = true;
            } catch (InvalidInputException e) {
                LOGGER.warn(e.getMessage() + "Invalid yes/no input");
                LOGGER.info("Please enter a valid input (y/n)");
            }
        } while (!valid);

        if (shipmentFinalized) {
            numShipments ++;
            LOGGER.info("number of shipments increased to " + numShipments);
            try {
                shipmentDAO.create(shipment);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
            LOGGER.info("Shipment finalized");
            LOGGER.info("Package Sent!");

            //Object Lock1 = new Object();
            //Object Lock2 = new Object();
            //Thread travel = new Thread(new Travel(shipment, Lock1, Lock2));
            //Thread receivePackage = new Thread(new DropOff(shipment, Lock1, Lock2));
            //travel.start();
            //receivePackage.start();
            //LOGGER.info("Delivery vehicle has begun it's transport");
        }
        return shipment;
    }
}
