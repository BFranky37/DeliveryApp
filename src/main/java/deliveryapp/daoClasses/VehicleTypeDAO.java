package deliveryapp.daoClasses;

import deliveryapp.models.vehicles.VehicleType;

import java.sql.SQLException;

public interface VehicleTypeDAO extends IBaseDAO<VehicleType> {
        @Override
    public VehicleType getObjectByID(int id) throws SQLException;

    public VehicleType getVehicleTypeByName(String name) throws SQLException;

    public VehicleType getVehicleTypeByVehicleID(int vehicleID) throws SQLException;

    @Override
    public int getIDbyObject(VehicleType p) throws SQLException;

    @Override
    public int create(VehicleType p) throws SQLException;

    @Override
    public void update(VehicleType p) throws SQLException;
}
