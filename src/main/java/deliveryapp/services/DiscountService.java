package deliveryapp.services;

public interface DiscountService {
    public void parseFromXmlDOM(String schemaName, String XmlName);

    public void parseFromXmlJAXB(String schemaName, String XmlName);
}
