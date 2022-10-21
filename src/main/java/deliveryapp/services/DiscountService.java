package deliveryapp.services;

import deliveryapp.models.people.Discount;

import java.util.List;

public interface DiscountService {
    public void createDiscount(Discount d);

    public List<Discount> getDiscountsByUser(int userID);

    public List<Discount> parseFromXmlDOM(String schemaName, String XmlName);

    public void parseFromXmlJAXB(String schemaName, String XmlName);
}
