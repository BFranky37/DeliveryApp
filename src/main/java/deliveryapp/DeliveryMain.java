package deliveryapp;

import deliveryapp.models.people.Address;
import deliveryapp.services.*;
import deliveryapp.models.people.Profile;
import deliveryapp.models.people.User;
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
import java.util.Scanner;

public class DeliveryMain {
    private static final Logger LOGGER = Logger.getLogger(DeliveryMain.class.getName());

    public static void main(String[] args) {
        LOGGER.info("Set up Logger");

        Scanner input = new Scanner(System.in);

        LOGGER.info("Welcome to the DeliveryApp. We will be happy to ship your package. ");
        AddressServiceImpl addressService = new AddressServiceImpl();
        ProfileServiceImpl profileService = new ProfileServiceImpl();
        UserServiceImpl userService = new UserServiceImpl();

        //Read Discount data from xml
        DiscountServiceImpl discountService = new DiscountServiceImpl();
        discountService.parseFromXML("discounts.xsd", "discounts.xml");
        //Read Insurance data from xml
        InsuranceServiceImpl insuranceService = new InsuranceServiceImpl();
        insuranceService.parseFromXML("insurance.xsd", "insurance.xml");

        //Get user information
        LOGGER.info("First we need some information about you. Press enter to continue");
        //Create user address
        Address senderAddress = addressService.addAddress();
        senderAddress.setId(addressService.getIDbyAddress(senderAddress));
        //Create profile
        Profile senderProfile = profileService.addUserProfile(senderAddress.getId());
        senderProfile.setId(profileService.getIDbyProfile(senderProfile));
        //Create User
        User user = new User(senderProfile.getId());
        userService.createUser(user);
        user.setId(userService.getIDbyUser(user));



    }
}
