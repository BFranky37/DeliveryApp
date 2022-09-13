package deliveryapp;

import deliveryapp.models.people.Address;
import deliveryapp.services.*;
import deliveryapp.models.people.Profile;
import deliveryapp.models.people.User;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class DeliveryMain {
    private static final Logger LOGGER = Logger.getLogger(DeliveryMain.class.getName());

    public static void main(String[] args) {
        LOGGER.info("Set up Logger");

        Scanner input = new Scanner(System.in);

        LOGGER.info("Welcome to the DeliveryApp. We will be happy to ship your package. ");

        //Get user information
        LOGGER.info("First we need some information about you. Press enter to continue");
        //Create user address
        AddressServiceImpl addressService = new AddressServiceImpl();
        Address senderAddress = addressService.addAddress();
        senderAddress.setId(addressService.getIDbyAddress(senderAddress));
        //Create profile
        ProfileServiceImpl profileService = new ProfileServiceImpl();
        Profile senderProfile = profileService.addUserProfile(senderAddress.getId());
        senderProfile.setId(profileService.getIDbyProfile(senderProfile));
        //Create User
        UserServiceImpl userService = new UserServiceImpl();
        User user = new User(senderProfile.getId());
        userService.createUser(user);
        user.setId(userService.getIDbyUser(user));



    }
}
