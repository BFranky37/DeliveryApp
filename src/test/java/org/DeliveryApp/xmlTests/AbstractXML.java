package org.DeliveryApp.xmlTests;

import deliveryapp.models.people.Discount;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractXML {
    protected static String discountXMLPath = "src/main/resources/xml/discounts.xml";
    protected static String discountXSDPath = "src/main/resources/xsd/discounts.xsd";
    protected List<Discount> expectedDiscountList = new ArrayList<Discount>();

    @BeforeClass
    public void setExpectedDiscountList() {
        expectedDiscountList.add(new Discount(1, "Veteran", .1));
        expectedDiscountList.add(new Discount(2, "Senior Citizen", .05));
        expectedDiscountList.add(new Discount(3, "Employee", .1));
        expectedDiscountList.add(new Discount(4, "Membership Holder", .15));
    }

    @AfterClass
    public void clearExpectedLists() {
        expectedDiscountList.clear();
    }
}
