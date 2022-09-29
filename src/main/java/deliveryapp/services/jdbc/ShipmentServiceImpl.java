package deliveryapp.services.jdbc;

import deliveryapp.daoClasses.ShipmentDAO;
import deliveryapp.daoClasses.java.ShipmentDAOimpl;
import deliveryapp.models.orders.Shipment;
import deliveryapp.models.people.Profile;
import deliveryapp.services.ShipmentService;
import deliveryapp.utils.ValidateInput;
import deliveryapp.utils.exceptions.InvalidInputException;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class ShipmentServiceImpl implements ShipmentService {

    private ShipmentDAO shipmentDAO = new ShipmentDAOimpl();;
    private static final Logger LOGGER = Logger.getLogger(ShipmentServiceImpl.class.getName());
    private static final Scanner input = new Scanner(System.in);

    @Override
    public Shipment shipPackage() {
        //GOING THROUGH THE ORDER PROCESS
        boolean sendAnother;
        do {
            boolean valid = false;
            sendAnother = false;
            //PACKAGE
            LOGGER.info("We need information about the package you are sending.");
            //PackageService -> Session.getpackageinfo

            //RECIPIENT
            LOGGER.info("We now need to know who you want to send this package to. Press enter to continue");
            Profile recipient = null;

            //ProfileService OR ContactService -> previousRecipients
            //if not create new profile for recipient

            //INSURANCE
            //InsuranceService -> Session.getInsuranceType(shippingPackage);

            //SHIPMENT
            //Session.finalizeShipment(sender, recipient, shippingPackage, insuranceType);

            do {
                try {
                    valid = false;
                    LOGGER.info("Would you like to send another package? (y/n)");
                    sendAnother = ValidateInput.validateYesNo(input.nextLine());
                    valid = true;
                } catch (InvalidInputException e) {
                    LOGGER.warn(e.getMessage() + "Invalid yes/no input");
                    LOGGER.info("Please enter a valid input (y/n)");
                }
            } while (!valid);
        } while (sendAnother);

        LOGGER.info("All shipments finalized.");
        LOGGER.info("Printing Receipts...");
        // ShipmentService -> Session.printReceipt(sender);
        return null;
    }
}
