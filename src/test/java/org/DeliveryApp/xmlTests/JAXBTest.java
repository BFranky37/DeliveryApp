package org.DeliveryApp.xmlTests;

import deliveryapp.models.people.Discount;
import deliveryapp.utils.fileUtils.XmlParser;
import deliveryapp.utils.fileUtils.XmlParserJAXB;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

public class JAXBTest extends AbstractXML {
    static Logger LOGGER = LogManager.getLogger(JAXBTest.class.getName());


    @Test
    public void testReadDiscountXML() {
        LOGGER.info("JAXBTest 1");
        XmlParser xmlParser = new XmlParserJAXB<Discount>();
        List<Discount> discounts = xmlParser.unmarshal(discountXMLPath, Discount.class);
        Assert.assertEquals(discounts, expectedDiscountList, "Data read from XML should match expected data");
    }
}
