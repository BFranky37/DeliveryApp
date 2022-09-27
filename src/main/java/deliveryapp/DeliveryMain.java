package deliveryapp;

import deliveryapp.models.people.Address;
import deliveryapp.models.people.Profile;
import deliveryapp.models.people.User;
import deliveryapp.services.*;
import deliveryapp.services.myBatis.*;
import deliveryapp.utils.Menu;
import deliveryapp.utils.exceptions.InvalidInputException;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class DeliveryMain {
    private static final Logger LOGGER = Logger.getLogger(DeliveryMain.class.getName());

    public static void main(String[] args) {
        LOGGER.info("Set up Logger");

        Scanner input = new Scanner(System.in);

        LOGGER.info("Welcome to the DeliveryApp. We will be happy to ship your package. ");
        AddressService addressService = new AddressServiceImpl();
        //ProfileService profileService = new ProfileServiceImpl();
        //UserService userService = new UserServiceImpl();

        //Get user information
        LOGGER.info("First we need some information about you. Press enter to continue");
        //Create user address
        Address senderAddress = addressService.addAddress();
        //Create profile
        //Profile senderProfile = profileService.addUserProfile(senderAddress.getId());
        //Create User
        //User user = new User(senderProfile.getId());
        //userService.createUser(user);

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
