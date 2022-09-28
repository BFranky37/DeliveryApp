package deliveryapp.daoClasses;

import deliveryapp.models.vehicles.Vehicle;

import java.sql.SQLException;

public interface VehicleDAO extends IBaseDAO<Vehicle> {
    @Override
    public Vehicle getObjectByID(int id) throws SQLException;

    @Override
    public int getIDbyObject(Vehicle p) throws SQLException;

    @Override
    public int create(Vehicle p) throws SQLException;

    @Override
    public void update(Vehicle p) throws SQLException;
}
