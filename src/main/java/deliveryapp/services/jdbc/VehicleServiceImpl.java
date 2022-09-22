package deliveryapp.services.jdbc;

import deliveryapp.dao_classes.VehicleDAO;
import deliveryapp.models.vehicles.Vehicle;
import deliveryapp.services.VehicleService;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class VehicleServiceImpl implements VehicleService {
    private final VehicleDAO vehicleDAO = new VehicleDAO();;
    private static final Logger LOGGER = Logger.getLogger(VehicleServiceImpl.class.getName());
    private static final Scanner input = new Scanner(System.in);

    @Override
    public Vehicle getVehicleByID(int id) {
        try {
            return vehicleDAO.getObjectByID(id);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public int getIDbyVehicle(Vehicle u) {
        try {
            return vehicleDAO.getIDbyObject(u);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    @Override
    public void createVehicle(Vehicle u) {
        try {
            u.setId(vehicleDAO.create(u));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void updateVehicle(Vehicle u) {
        try {
            vehicleDAO.update(u);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
