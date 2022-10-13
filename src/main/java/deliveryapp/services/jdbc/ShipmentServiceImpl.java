package deliveryapp.services.jdbc;

import deliveryapp.daoClasses.ShipmentDAO;
import deliveryapp.daoClasses.java.ShipmentDAOimpl;
import deliveryapp.models.orders.Insurance;
import deliveryapp.models.orders.Shipment;
import deliveryapp.models.orders.Package;
import deliveryapp.models.people.Profile;
import deliveryapp.models.people.User;
import deliveryapp.models.vehicles.Route;
import deliveryapp.models.vehicles.Vehicle;
import deliveryapp.services.*;
import deliveryapp.utils.ValidateInput;
import deliveryapp.utils.exceptions.InvalidInputException;
import deliveryapp.utils.functionalInterfaces.IEditString;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ShipmentServiceImpl implements ShipmentService {
    private final ShipmentDAO shipmentDAO = new ShipmentDAOimpl();
    private final ShipmentStatusService shipmentStatusService = new ShipmentStatusServiceImpl();
    private final ProfileService profileService = new ProfileServiceImpl();
    private final RouteService routeService = new RouteServiceImpl();
    private final VehicleService vehicleService = new VehicleServiceImpl();
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
                shipment.setId(shipmentDAO.create(shipment));
                shipmentStatusService.createShipmentStatus(shipment);
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

    public void printReceipt(User sender) throws IOException {
        if (numShipments == 0)
            return;
        Date date = new java.util.Date();
        double totalPrice = 0;
        File file = FileUtils.getFile("Receipts.txt");
        IEditString<String> punctuationAppend = (string, punctuation) -> string + punctuation;
        String writeToFile = ("Receipt for " + date);
        ArrayList<Shipment> shipments = new ArrayList<>();
        try {
            shipments = shipmentDAO.getShipmentsForReciept(sender, numShipments);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        for (Shipment s : shipments) {
            Profile p = profileService.getProfileByID(s.getRecipientID());
            Route r = routeService.getRouteByID(s.getRouteID());
            Vehicle v = vehicleService.getVehicleByID(s.getVehicleID());
            writeToFile = punctuationAppend.append(writeToFile + "\n\nRecipient: " + p +
                    "\nRoute: " + r +
                    "\nShipping method: " + v +
                    "\nPrice: $" + String.format("%.2f", s.getPrice()) + "\n", ",");
            totalPrice += s.getPrice();
            numShipments--;
        }
        writeToFile = punctuationAppend.append(writeToFile + "\nTotal: $" + String.format("%.2f", totalPrice) + "\n\n\n", "...");
        FileUtils.writeStringToFile(file, writeToFile, Charset.defaultCharset(), true);
    }
}
