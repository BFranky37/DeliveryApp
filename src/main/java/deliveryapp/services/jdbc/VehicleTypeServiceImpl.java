package deliveryapp.services.jdbc;

import deliveryapp.daoClasses.VehicleTypeDAO;
import deliveryapp.daoClasses.java.VehicleTypeDAOimpl;
import deliveryapp.models.orders.Insurance;
import deliveryapp.models.vehicles.VehicleType;
import deliveryapp.services.VehicleTypeService;
import deliveryapp.utils.fileUtils.XmlParserDOM;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VehicleTypeServiceImpl implements VehicleTypeService {
    private final VehicleTypeDAO vehicleTypeDAOimpl = new VehicleTypeDAOimpl();
    private final XmlParserDOM vehicleTypeParser = new XmlParserDOM();
    private static final Logger LOGGER = Logger.getLogger(VehicleTypeServiceImpl.class.getName());
    private static final Scanner input = new Scanner(System.in);

    @Override
    public VehicleType getVehicleTypeByID(int id) {
        try {
            return vehicleTypeDAOimpl.getObjectByID(id);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    public VehicleType getVehicleTypeByName(String name) {
        try {
            return vehicleTypeDAOimpl.getVehicleTypeByName(name);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    public VehicleType getVehicleTypeByVehicleID(int vehicleID) {
        try {
            return vehicleTypeDAOimpl.getVehicleTypeByVehicleID(vehicleID);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public int getIDbyVehicleType(VehicleType u) {
        try {
            return vehicleTypeDAOimpl.getIDbyObject(u);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    @Override
    public void createVehicleType(VehicleType u) {
        try {
            u.setId(vehicleTypeDAOimpl.create(u));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void updateVehicleType(VehicleType u) {
        try {
            vehicleTypeDAOimpl.update(u);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public List<VehicleType> parseFromXmlDOM(String schemaName, String xmlName) {
        vehicleTypeParser.loadSchema(schemaName);
        Document doc = vehicleTypeParser.readXMLFile(xmlName, Document.class);
        vehicleTypeParser.validate(doc);

        NodeList list = doc.getElementsByTagName("vehicle_type");
        List<VehicleType> vehicleTypes = new ArrayList<VehicleType>();
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == node.ELEMENT_NODE) {
                Element element = (Element) node;
                VehicleType d = new VehicleType();
                d.setId(Integer.parseInt(element.getAttribute("id")));
                d.setName(element.getElementsByTagName("name").item(0).getTextContent());
                d.setRate(Double.parseDouble(element.getElementsByTagName("cost_rate").item(0).getTextContent()));
                d.setMaxWeight(Double.parseDouble(element.getElementsByTagName("weight_capacity").item(0).getTextContent()));
                d.setMaxSize(Double.parseDouble(element.getElementsByTagName("space_capacity").item(0).getTextContent()));

                vehicleTypes.add(d);
                try {
                    vehicleTypeDAOimpl.create(d);
                } catch (SQLException e) {
                    LOGGER.warn(e.getMessage());
                }
            }
        }
        return vehicleTypes;
    }
}
