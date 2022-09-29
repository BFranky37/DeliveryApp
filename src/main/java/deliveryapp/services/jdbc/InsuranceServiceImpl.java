package deliveryapp.services.jdbc;

import deliveryapp.daoClasses.InsuranceDAO;
import deliveryapp.daoClasses.java.InsuranceDAOimpl;
import deliveryapp.models.orders.Insurance;
import deliveryapp.services.InsuranceService;
import deliveryapp.utils.file_utils.XmlParserDOM;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.sql.SQLException;

public class InsuranceServiceImpl implements InsuranceService {
    private static final Logger LOGGER = Logger.getLogger(InsuranceServiceImpl.class.getName());

    private final InsuranceDAO insuranceDAO;
    XmlParserDOM insuranceParser;

    public InsuranceServiceImpl() {
        insuranceDAO  = new InsuranceDAOimpl();
        insuranceParser = new XmlParserDOM();
    }

    @Override
    public void parseFromXmlDOM(String schemaName, String xmlName) {
        insuranceParser.loadSchema(schemaName);
        Document doc = insuranceParser.readXMLFile(xmlName);

        NodeList list = doc.getElementsByTagName("insurance");
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == node.ELEMENT_NODE) {
                Element element = (Element) node;
                Insurance d = new Insurance();
                d.setId(Integer.parseInt(element.getAttribute("id")));
                d.setName(element.getElementsByTagName("name").item(0).getTextContent());
                d.setCost(Double.parseDouble(element.getElementsByTagName("base_cost").item(0).getTextContent()));
                d.setRate(Double.parseDouble(element.getElementsByTagName("price_rate").item(0).getTextContent()));
                try {
                    insuranceDAO.create(d);
                } catch (SQLException e) {
                    LOGGER.warn(e.getMessage());
                }
            }
        }
    }
}
