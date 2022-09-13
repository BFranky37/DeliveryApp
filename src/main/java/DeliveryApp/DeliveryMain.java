package DeliveryApp;

import DeliveryApp.exceptions.InvalidInputException;
import DeliveryApp.fileUtils.FileUtilities;
import DeliveryApp.functionalInterfaces.IReturnNum;
import DeliveryApp.orders.Insurance;
import DeliveryApp.orders.Package;
import DeliveryApp.other.CustomList;
import DeliveryApp.people.Address;
import DeliveryApp.services.*;
import DeliveryApp.people.Discount;
import DeliveryApp.people.Profile;
import DeliveryApp.people.User;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DeliveryMain {
    private static final Logger LOGGER = Logger.getLogger(DeliveryMain.class.getName());

    public static void main(String[] args) {
        LOGGER.info("Set up Logger");

        Scanner input = new Scanner(System.in);

        //Count unique words
        try {
            FileUtilities.countUniqueWords("src/main/java/DeliverySystem/fileUtils/UniqueWords.txt");
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }

        LOGGER.info("Welcome to the DeliveryApp. We will be happy to ship your package. ");

        //Get user information
        LOGGER.info("First we need some information about you. Press enter to continue");
        //Create user address
        AddressService addressService = new AddressService();
        Address senderAddress = addressService.addAddress();
        senderAddress.setId(addressService.getIDbyAddress(senderAddress));
        //Create user profile
        ProfileService profileService = new ProfileService();
        Profile senderProfile = profileService.addUserProfile(senderAddress.getId());
        senderProfile.setId(profileService.getIDbyProfile(senderProfile));
        //Create User
        UserService userService = new UserService();
        User user = new User(senderProfile.getId());
        userService.createUser(user);
        user.setId(userService.getIDbyUser(user));

        

    }
}
