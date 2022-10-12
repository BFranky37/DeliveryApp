package deliveryapp.services;

import deliveryapp.models.orders.Box;
import deliveryapp.models.people.Discount;
import deliveryapp.models.people.User;

import java.sql.SQLException;
import java.util.List;

public interface DiscountService {
    public void createDiscount(Discount d);

    public List<Discount> getDiscountsByUser(int userID);

    public void parseFromXmlDOM(String schemaName, String XmlName);

    public void parseFromXmlJAXB(String schemaName, String XmlName);
}
