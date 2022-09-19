package deliveryapp.utils.file_utils;

import org.w3c.dom.Document;

public interface XmlParser {
    public void loadSchema(String filename);

    public Document readXMLFile(String filename);
}
