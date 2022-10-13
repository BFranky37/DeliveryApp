package deliveryapp.services.jdbc;

import deliveryapp.daoClasses.InsuranceDAO;
import deliveryapp.daoClasses.java.InsuranceDAOimpl;
import deliveryapp.models.orders.Insurance;
import deliveryapp.models.orders.Package;
import deliveryapp.services.InsuranceService;
import deliveryapp.utils.ValidateInput;
import deliveryapp.utils.exceptions.InvalidInputException;
import deliveryapp.utils.fileUtils.XmlParserDOM;
import org.apache.log4j.Logger;
import org.w3c.dom.*;

import java.sql.SQLException;
import java.util.Scanner;

public class InsuranceServiceImpl implements InsuranceService {
    private static final Logger LOGGER = Logger.getLogger(InsuranceServiceImpl.class.getName());

    private final InsuranceDAO insuranceDAOimpl = new InsuranceDAOimpl();;
    XmlParserDOM insuranceParser = new XmlParserDOM();

    public InsuranceServiceImpl() {
    }

    public Insurance getInsuranceByID(int id) {
        try {
            return insuranceDAOimpl.getObjectByID(id);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    public Insurance getInsuranceType(Package shippingPackage) {
        Scanner input = new Scanner(System.in);

        Insurance insuranceType = new Insurance();
        boolean insurancePurchased = false;
        double value = shippingPackage.getValue();
        try {
            if (value > 200)
                insuranceType = insuranceDAOimpl.getObjectByName("High Value Insurance");
            else if (shippingPackage.getFragility())
                insuranceType = insuranceDAOimpl.getObjectByName("Fragility Insurance");
            else insuranceType = insuranceDAOimpl.getObjectByName("Basic Insurance");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        boolean valid = false;
        do {
            try {
                LOGGER.info("Would you like to purchase " + insuranceType.getName() + " for this package to be reimbursed in the case it is lost or damaged? ");
                LOGGER.info("The price for insurance for your item would be " +
                        "$" + Math.round(insuranceType.calculatePrice(value) * 100.0) / 100.0 + " (y/n):");
                insurancePurchased = ValidateInput.validateYesNo(input.nextLine());
                valid = true;
            } catch (InvalidInputException e) {
                LOGGER.warn(e.getMessage() + "Invalid yes/no input");
                LOGGER.info("Please enter a valid input (y/n)");
            }
        } while (!valid);

        if (!insurancePurchased) {
            try {
                insuranceType = insuranceDAOimpl.getObjectByName("No Insurance");
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
        else {
            LOGGER.info("Insurance added: " + insuranceType.getName());
        }
        return insuranceType;
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
                    insuranceDAOimpl.create(d);
                } catch (SQLException e) {
                    LOGGER.warn(e.getMessage());
                }
            }
        }
    }
}
