package deliveryapp.services;

import deliveryapp.models.vehicles.VehicleType;

public interface VehicleTypeService {
    public VehicleType getVehicleTypeByID(int id);

    public VehicleType getVehicleTypeByName(String name);

    public VehicleType getVehicleTypeByVehicleID(int vehicleID);

    public int getIDbyVehicleType(VehicleType u);

    public void createVehicleType(VehicleType u);

    public void updateVehicleType(VehicleType u);

    public void parseFromXmlDOM(String schemaName, String xmlName);
}
