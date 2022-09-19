package deliveryapp.services;

import deliveryapp.dao_classes.DiscountDAO;
import deliveryapp.models.people.Discount;
import deliveryapp.utils.file_utils.XmlParserDOM;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.sql.SQLException;

public class DiscountServiceImpl implements DiscountService {
    private static final Logger LOGGER = Logger.getLogger(DiscountServiceImpl.class.getName());

    private final DiscountDAO discountDAO;
    XmlParserDOM discountParser;

    public DiscountServiceImpl() {
        discountDAO  = new DiscountDAO();
        discountParser = new XmlParserDOM();
    }

    @Override
    public void parseFromXML(String schemaName, String xmlName) {
        discountParser.loadSchema(schemaName);
        Document doc = discountParser.readXMLFile(xmlName);

        NodeList list = doc.getElementsByTagName("discount");
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == node.ELEMENT_NODE) {
                Element element = (Element) node;
                Discount d = new Discount();
                d.setId(Integer.parseInt(element.getAttribute("id")));
                d.setName(element.getElementsByTagName("name").item(0).getTextContent());
                d.setDiscountRate(Double.parseDouble(element.getElementsByTagName("rate").item(0).getTextContent()));

                try {
                    discountDAO.create(d);
                } catch (SQLException e) {
                    LOGGER.warn(e.getMessage());
                }
            }
        }
    }
}
