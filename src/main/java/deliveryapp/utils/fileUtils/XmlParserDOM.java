package deliveryapp.utils.fileUtils;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;


import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class XmlParserDOM implements XmlParser<Document> {
    private static final Logger LOGGER = Logger.getLogger(XmlParserDOM.class.getName());

    Schema schema;

    @Override
    public Schema loadSchema(String filename) {
        try {
            String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory factory = SchemaFactory.newInstance(language);
            schema = factory.newSchema(new File(filename));
            return schema;
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
        return null;
    }

    @Override
    public Document readXMLFile(String filename , Class<Document> clazz) {
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

        return doc;
    }

    public Document validate(Document doc) {
        //validate against schema
        try {
            Validator validator = schema.newValidator();
            validator.validate(new DOMSource(doc));
        } catch (SAXException | IOException e) {
            LOGGER.warn(e.getMessage() + "could not validate Doc against schema");
        }
        return doc;
    }

    @Override
    public List<Document> unmarshal(String filename, Class<Document> clazz) {
        return null;
    }
}
