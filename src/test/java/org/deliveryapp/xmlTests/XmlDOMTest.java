package org.deliveryapp.xmlTests;

import deliveryapp.services.DiscountService;
import deliveryapp.services.jdbc.DiscountServiceImpl;
import deliveryapp.utils.fileUtils.XmlParserDOM;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;

import org.testng.annotations.Test;
import org.w3c.dom.Document;

public class XmlDOMTest extends AbstractXML {
    private static Logger LOGGER = LogManager.getLogger(XmlDOMTest.class.getName());


    @Test
    public void testReadDiscountSchema() {
        LOGGER.info("XmlDOMTest 1");
        XmlParserDOM xmlParser = new XmlParserDOM();
        Assert.assertNotNull(xmlParser.loadSchema(discountXSDPath), "Schema must be created from XSD file");
    }

    @Test
    public void testReadDiscountXML() {
        LOGGER.info("XmlDOMTest 2");
        XmlParserDOM xmlParser = new XmlParserDOM();
        Assert.assertNotNull(xmlParser.readXMLFile(discountXMLPath, Document.class), "XML must be parsed into Document");
    }

    @Test
    public void testValidateDiscountDOM() {
        LOGGER.info("XmlDOMTest 3");
        XmlParserDOM xmlParser = new XmlParserDOM();
        xmlParser.loadSchema(discountXSDPath);
        Document doc = xmlParser.readXMLFile(discountXMLPath, Document.class);
        Assert.assertNotNull(xmlParser.validate(doc), "Parsed Doc must be validated against Discount schema");
    }

    @Test
    public void testParseDiscountDOM() {
        LOGGER.info("XmlDOMTest 4");
        DiscountService discountService = new DiscountServiceImpl();
        Assert.assertEquals(discountService.parseFromXmlDOM(discountXSDPath, discountXMLPath), expectedDiscountList, "Data read from XML should match expected data");
    }

}
