package deliveryapp;

import deliveryapp.models.people.Address;
import deliveryapp.services.*;
import deliveryapp.models.people.Profile;
import deliveryapp.models.people.User;
import deliveryapp.utils.Menu;
import deliveryapp.utils.exceptions.InvalidInputException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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

        //MENU
        Menu menu = Menu.SHIP_PACKAGE;
        boolean exit = false;
        boolean valid = false;
        do {
            boolean validInput = false;
            menu.printMenu(); //Print menu
            while (!validInput) {
                try {
                    menu = menu.makeSelection(Integer.parseInt(input.nextLine())); //Select menu option
                    validInput = true;
                } catch (InvalidInputException | NumberFormatException e) {
                    LOGGER.warn(e.getMessage() + " Please try again.");
                }
            }

            switch (menu) {
                case SHIP_PACKAGE:

                    break;

                case EDIT_PROFILE:
                    break;

                case CHANGE_DISCOUNT:
                    break;

                case ADD_RECIPIENT:
                    break;

                case VIEW_PROFILES:
                    break;

                case OPERATING_CITIES:
                    break;

                case EXIT_PROGRAM:
                    LOGGER.info("Thank you for using the Delivery App!");
                    LOGGER.info("Exiting Program...");
                    exit = true;
                    break;
            }
        } while (!exit);

    }
}
