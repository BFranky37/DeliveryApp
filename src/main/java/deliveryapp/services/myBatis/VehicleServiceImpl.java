package deliveryapp.services.myBatis;

import deliveryapp.dao_classes.java.VehicleDAOimpl;
import deliveryapp.models.vehicles.Vehicle;
import deliveryapp.services.VehicleService;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class VehicleServiceImpl implements VehicleService {
    private final VehicleDAOimpl vehicleDAOimpl = new VehicleDAOimpl();;
    private static final Logger LOGGER = Logger.getLogger(VehicleServiceImpl.class.getName());
    private static final Scanner input = new Scanner(System.in);

    @Override
    public Vehicle getVehicleByID(int id) {
        try {
            return vehicleDAOimpl.getObjectByID(id);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public int getIDbyVehicle(Vehicle u) {
        try {
            return vehicleDAOimpl.getIDbyObject(u);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    @Override
    public void createVehicle(Vehicle u) {
        try {
            u.setId(vehicleDAOimpl.create(u));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void updateVehicle(Vehicle u) {
        try {
            vehicleDAOimpl.update(u);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
