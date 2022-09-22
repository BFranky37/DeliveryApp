package deliveryapp.services.jdbc;

import deliveryapp.dao_classes.PackageDAO;
import deliveryapp.models.orders.Package;
import deliveryapp.services.PackageService;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class PackageServiceImpl implements PackageService {
    private final PackageDAO packageDAO = new PackageDAO();;
    private static final Logger LOGGER = Logger.getLogger(PackageServiceImpl.class.getName());
    private static final Scanner input = new Scanner(System.in);

    @Override
    public Package getPackageByID(int id) {
        try {
            return packageDAO.getObjectByID(id);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public int getIDbyPackage(Package u) {
        try {
            return packageDAO.getIDbyObject(u);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    @Override
    public void createPackage(Package u) {
        try {
            u.setId(packageDAO.create(u));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void updatePackage(Package u) {
        try {
            packageDAO.update(u);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
