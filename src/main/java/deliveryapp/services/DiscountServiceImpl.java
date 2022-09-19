package deliveryapp.services;

import deliveryapp.dao_classes.DiscountDAO;
import deliveryapp.models.people.Discount;
import deliveryapp.utils.file_utils.XmlParserDOM;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;

public class DiscountServiceImpl implements DiscountService {
    private static final Logger LOGGER = Logger.getLogger(DiscountServiceImpl.class.getName());

    private final DiscountDAO discountDAO;
    XmlParserDOM domParser;

    public DiscountServiceImpl() {
        discountDAO  = new DiscountDAO();
        domParser = new XmlParserDOM();
    }

    @Override
    public void parseFromXmlDOM(String schemaName, String xmlName) {
        domParser.loadSchema(schemaName);
        Document doc = domParser.readXMLFile(xmlName);

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

    @Override
    public void parseFromXmlJAXB(String schemaName, String xmlName) {
        Discount xmlDiscount = new Discount();
        try {
            JAXBContext context = JAXBContext.newInstance(Discount.class);
            xmlDiscount = (Discount) context.createUnmarshaller().unmarshal(new FileReader(xmlName));
        } catch (JAXBException | FileNotFoundException e) {
            LOGGER.warn(e.getMessage());
        }

        try {
            discountDAO.create(xmlDiscount);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }

    }
}
