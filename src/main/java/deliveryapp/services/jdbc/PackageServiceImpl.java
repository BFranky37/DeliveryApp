package deliveryapp.services.jdbc;

import deliveryapp.daoClasses.java.PackageDAOimpl;
import deliveryapp.models.orders.Package;
import deliveryapp.services.PackageService;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class PackageServiceImpl implements PackageService {
    private final PackageDAOimpl packageDAOimpl = new PackageDAOimpl();;
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
