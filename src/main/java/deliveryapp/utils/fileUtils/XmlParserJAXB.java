package deliveryapp.utils.fileUtils;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class XmlParserJAXB implements XmlParser {
    private static final Logger LOGGER = Logger.getLogger(XmlParserDOM.class.getName());

    Schema schema;

    @Override
    public void loadSchema(String filename) {
        try {
            String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory factory = SchemaFactory.newInstance(language);
            schema = factory.newSchema(new File(filename));
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
    }

    @Override
    public Document readXMLFile(String filename) {
        return null;
    }
}
