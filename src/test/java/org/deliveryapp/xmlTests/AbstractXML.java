package org.deliveryapp.xmlTests;

import deliveryapp.models.people.Discount;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractXML {
    protected static final String discountXMLPath = "src/main/resources/xml/discounts.xml";
    protected static final String discountXSDPath = "src/main/resources/xsd/discounts.xsd";
    protected List<Discount> expectedDiscountList = new ArrayList<Discount>();

    public AbstractXML() {
        expectedDiscountList.add(new Discount(1, "Veteran", .1));
        expectedDiscountList.add(new Discount(2, "Senior Citizen", .05));
        expectedDiscountList.add(new Discount(3, "Employee", .1));
        expectedDiscountList.add(new Discount(4, "Membership Holder", .15));
    }

}
