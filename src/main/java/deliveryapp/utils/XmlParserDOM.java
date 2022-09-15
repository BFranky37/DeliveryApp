package deliveryapp.utils;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;


import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;

public class XmlParserDOM {
    private static final Logger LOGGER = Logger.getLogger(XmlParserDOM.class.getName());

    Schema schema;

    public void loadSchema(String filename) {
        try {
            String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory factory = SchemaFactory.newInstance(language);
            schema = factory.newSchema(new File(filename));
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
    }

    public Document readXMLFile(String filename) {
        Document doc = null;
        try { //parse xml file
            File xmlFile = FileUtils.getFile(filename);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = factory.newDocumentBuilder();
            doc = build.parse(xmlFile);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage() + "could not parse xml file");
            return null;
        }

        try { //validate against schema
            Validator validator = schema.newValidator();
            validator.validate(new DOMSource(doc));
        } catch (Exception e) {
            LOGGER.warn(e.getMessage() + "xml file could not be validated against schema");
            return null;
        }

        return doc;
    }
}
