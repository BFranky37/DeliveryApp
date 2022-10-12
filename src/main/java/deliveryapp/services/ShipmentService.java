package deliveryapp.services;

import deliveryapp.models.orders.Insurance;
import deliveryapp.models.orders.Package;
import deliveryapp.models.orders.Shipment;
import deliveryapp.models.people.Profile;
import deliveryapp.models.people.User;

public interface ShipmentService {
    public Shipment shipPackage(User sender, Profile recipient, Package shippingPackage, Insurance insuranceType);
}
