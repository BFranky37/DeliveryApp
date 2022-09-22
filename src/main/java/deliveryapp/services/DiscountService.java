package deliveryapp.services;

import deliveryapp.models.orders.Box;
import deliveryapp.models.people.Discount;

public interface DiscountService {
    public void createDiscount(Discount d);
    public void parseFromXmlDOM(String schemaName, String XmlName);

    public void parseFromXmlJAXB(String schemaName, String XmlName);
}
