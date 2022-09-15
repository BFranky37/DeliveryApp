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
                    //GOING THROUGH THE ORDER PROCESS
                    boolean sendAnother;
                    do {
                        valid = false;
                        sendAnother = false;
                        //PACKAGE
                        LOGGER.info("We need information about the package you are sending.");
                        Package shippingPackage = Session.getPackageInfo();

                        //RECIPIENT
                        LOGGER.info("We now need to know who you want to send this package to. Press enter to continue");
                        Recipient recipient = null;
                        boolean previousRecipient;
                        do {
                            try { //SELECT FROM PREVIOUS RECIPIENTS
                                LOGGER.info("Have you sent to this person before or added their information? (y/n)");
                                previousRecipient = ValidateInput.validateYesNo(input.nextLine());
                                if (previousRecipient) {
                                    int num = 1;
                                    IFilter<LinkedHashSet<Person>, List<Person>> filterProfiles = (profiles) -> //Convert profiles to List of Recipient names
                                            (List<Person>) profiles.stream().filter(name -> !name.getClass().equals(Sender.class)).collect(Collectors.toList());
                                    List<Person> profiles = filterProfiles.filter(Session.getProfiles());
                                    for (Person profile : profiles) {
                                        LOGGER.info(num + ". " + profile.getName() + ": " + profile.getAddress());
                                        num++;
                                    }
                                    LOGGER.info("Please select the recipient for this shipment.");
                                    int recipientSelection = Integer.parseInt(input.nextLine());
                                    if (recipientSelection > profiles.size())
                                        throw new InvalidInputException("Menu selection not in range.");
                                    recipient = (Recipient) profiles.get(recipientSelection - 1);
                                }
                                else { //CREATE NEW RECIPIENT PROFILE
                                    recipient = Session.getRecipientInfo();
                                    Session.addProfile(recipient);
                                }
                                valid = true;
                            } catch (InvalidInputException e) {
                                LOGGER.info(e.getMessage());
                            }
                        } while (!valid);

                        //INSURANCE
                        Insurance insuranceType = Session.getInsuranceType(shippingPackage);
                        //SHIPMENT
                        Session.finalizeShipment(sender, recipient, shippingPackage, insuranceType);

                        do {
                            try {
                                valid = false;
                                LOGGER.info("Would you like to send another package? (y/n)");
                                sendAnother = ValidateInput.validateYesNo(input.nextLine());
                                valid = true;
                            } catch (InvalidInputException e) {
                                LOGGER.warn(e.getMessage() + "Invalid yes/no input");
                                LOGGER.info("Please enter a valid input (y/n)");
                            }
                        } while (!valid);
                    } while (sendAnother);

                    LOGGER.info("All shipments finalized.");
                    LOGGER.info("Printing Receipts...");
                    try {
                        Session.printReceipt(sender);
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage());
                        throw new RuntimeException(e);
                    }
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
