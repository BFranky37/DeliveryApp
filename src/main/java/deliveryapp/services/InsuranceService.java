package deliveryapp.services;

import deliveryapp.models.orders.Insurance;
import deliveryapp.models.orders.Package;

public interface InsuranceService {
    public Insurance getInsuranceByID(int id);
    public Insurance getInsuranceType(Package shippingPackage);
    public void parseFromXmlDOM(String schemaName, String XmlName);
}
