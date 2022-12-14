package deliveryapp.services.jdbc;

import deliveryapp.daoClasses.PackageDAO;
import deliveryapp.daoClasses.java.PackageDAOimpl;
import deliveryapp.models.orders.Box;
import deliveryapp.models.orders.Package;
import deliveryapp.services.PackageService;
import deliveryapp.utils.ValidateInput;
import deliveryapp.utils.exceptions.*;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class PackageServiceImpl implements PackageService {
    private final PackageDAO packageDAOimpl = new PackageDAOimpl();;
    private static final Logger LOGGER = Logger.getLogger(PackageServiceImpl.class.getName());
    private static final Scanner input = new Scanner(System.in);

    @Override
    public Package getPackageByID(int id) {
        try {
            return packageDAOimpl.getObjectByID(id);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public int getIDbyPackage(Package u) {
        try {
            return packageDAOimpl.getIDbyObject(u);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    @Override
    public Package getPackageInfo() {
        //Getting Box info
        BoxServiceImpl boxService = new BoxServiceImpl();
        Box box = boxService.getBoxInfo();
        //Getting Package info
        double weight = 0, value = 0;
        boolean fragility = false;
        boolean valid = false;
        //Get Weight and value
        do {
            try {
                valid = false;
                LOGGER.info("What is the weight of your package in kilograms: ");
                weight = Package.validateWeight(input.nextDouble());
                LOGGER.info("What is the value of the item you are shipping in dollars: ");
                value = ValidateInput.validateValue(input.nextDouble());
                input.nextLine();
                valid = true;
            } catch (ExceedsLimitsException | NegativeValueException | InvalidInputException e) {
                LOGGER.warn(e.getMessage());
            }
        } while (!valid);

        //Get fragility
        do {
            try {
                valid = false;
                LOGGER.info("Is the item you are shipping fragile? (y/n): ");
                fragility = ValidateInput.validateYesNo(input.nextLine());
                valid = true;
            } catch (InvalidInputException e) {
                LOGGER.warn(e.getMessage());
            }
        } while (!valid);

        //Create Package and add it to database
        Package shippingPackage = new Package(box.getId(), weight, value, fragility);
        createPackage(shippingPackage);
        return shippingPackage;
    }

    @Override
    public void createPackage(Package u) {
        try {
            u.setId(packageDAOimpl.create(u));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void updatePackage(Package u) {
        try {
            packageDAOimpl.update(u);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
