package deliveryapp.services;

import deliveryapp.models.orders.Insurance;
import deliveryapp.models.orders.Package;
import deliveryapp.models.people.Discount;

import java.util.List;

public interface InsuranceService {
    public Insurance getInsuranceByID(int id);
    public Insurance getInsuranceType(Package shippingPackage);
    public List<Insurance> parseFromXmlDOM(String schemaName, String XmlName);
}
