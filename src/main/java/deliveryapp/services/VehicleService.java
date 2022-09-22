package deliveryapp.services;

import deliveryapp.models.vehicles.Vehicle;

public interface VehicleService {
    public Vehicle getVehicleByID(int id);

    public int getIDbyVehicle(Vehicle u);

    public void createVehicle(Vehicle u);

    public void updateVehicle(Vehicle u);
}
