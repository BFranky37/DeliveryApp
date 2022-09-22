package deliveryapp.services;

import deliveryapp.models.orders.ShipmentStatus;

public interface ShipmentStatusService {
    public ShipmentStatus getShipmentStatusByID(int id);

    public int getIDbyShipmentStatus(ShipmentStatus u);

    public void createShipmentStatus(ShipmentStatus u);

    public void updateShipmentStatus(ShipmentStatus u);
}
