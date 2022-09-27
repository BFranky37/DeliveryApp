package deliveryapp.services.myBatis;

import deliveryapp.dao_classes.java.VehicleTypeDAOimpl;
import deliveryapp.models.vehicles.VehicleType;
import deliveryapp.services.VehicleTypeService;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class VehicleTypeServiceImpl implements VehicleTypeService {
    private final VehicleTypeDAOimpl vehicleTypeDAOimpl = new VehicleTypeDAOimpl();;
    private static final Logger LOGGER = Logger.getLogger(VehicleTypeServiceImpl.class.getName());
    private static final Scanner input = new Scanner(System.in);

    @Override
    public VehicleType getVehicleTypeByID(int id) {
        try {
            return vehicleTypeDAOimpl.getObjectByID(id);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public int getIDbyVehicleType(VehicleType u) {
        try {
            return vehicleTypeDAOimpl.getIDbyObject(u);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    @Override
    public void createVehicleType(VehicleType u) {
        try {
            u.setId(vehicleTypeDAOimpl.create(u));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void updateVehicleType(VehicleType u) {
        try {
            vehicleTypeDAOimpl.update(u);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
