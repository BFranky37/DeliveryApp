package deliveryapp.services.jdbc;

import deliveryapp.daoClasses.java.ProfileDAOimpl;
import deliveryapp.models.people.Address;
import deliveryapp.daoClasses.AddressDAO;
import deliveryapp.daoClasses.ProfileDAO;
import deliveryapp.daoClasses.java.ProfileDAOimpl;
import deliveryapp.models.people.Profile;
import deliveryapp.models.people.User;
import deliveryapp.services.AddressService;
import deliveryapp.services.ContactService;
import deliveryapp.services.ProfileService;
import deliveryapp.utils.ValidateInput;
import deliveryapp.utils.exceptions.InvalidInputException;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProfileServiceImpl implements ProfileService {
    private final ProfileDAO profileDAO = new ProfileDAOimpl();;
    private static final Logger LOGGER = Logger.getLogger(ProfileDAOimpl.class.getName());
    private static final Scanner input = new Scanner(System.in);

    public void createProfile(Profile p) {
        try {
            p.setId(profileDAOimpl.create(p));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public Profile addUserProfile() {
        LOGGER.info("Please enter your name: ");
        String name = input.nextLine();
        LOGGER.info("Please enter your phone number: ");
        String phoneNumber = input.nextLine();

        AddressService addressService = new AddressServiceImpl();
        Address userAddress = addressService.addUserAddress();

        Profile p = new Profile(name, phoneNumber, userAddress.getId());

        try {
            int profileID = getIDbyProfile(p);
            if (profileID < 0)
                p.setId(profileDAOimpl.create(p));
            else p.setId(profileID);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return p;
    }

    @Override
    public Profile addRecipientProfile() {
        input.nextLine();
        LOGGER.info("Please enter the recipient's name: ");
        String name = input.nextLine();
        LOGGER.info("Please enter the recipient's phone number: ");
        String phoneNumber = input.nextLine();

        AddressService addressService = new AddressServiceImpl();
        Address recipientAddress = addressService.addRecipientAddress();

        Profile p = new Profile(name, phoneNumber, recipientAddress.getId());

        try {
            int profileID = getIDbyProfile(p);
            if (profileID < 0)
                p.setId(profileDAOimpl.create(p));
            else p.setId(profileID);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return p;
    }

    @Override
    public Profile getProfileByID(int id) {
        try {
            return profileDAOimpl.getObjectByID(id);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public int getIDbyProfile(Profile p) {
        try {
            return profileDAOimpl.getIDbyObject(p);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    @Override
    public Profile selectRecipient(User user) {
        ContactService contactService = new ContactServiceImpl();
        boolean previousRecipient;
        Profile recipient = new Profile();
        boolean valid;
        do {
            valid = false;
            try { //SELECT FROM PREVIOUS RECIPIENTS
                LOGGER.info("Have you sent to this person before or added their information? (y/n)");
                previousRecipient = ValidateInput.validateYesNo(input.nextLine());
                if (previousRecipient) {
                    recipient = getProfileSelection(contactService.getContactsByUser(user));
                    if (recipient == null)
                        throw new SQLException("Going back...");
                } else { //CREATE NEW RECIPIENT PROFILE
                    recipient = addRecipientProfile();
                    //add new recipient profile to user's contacts
                }
                valid = true;
            } catch (InvalidInputException | SQLException e) {
                LOGGER.info(e.getMessage());
            }
        } while (!valid);

        return recipient;
    }

    public Profile getProfileSelection(ArrayList<Profile> profiles) throws InvalidInputException {
        int num = 1;
        for (Profile profile : profiles) {
            LOGGER.info(num + ". " + profile.getName() + ", phone number: " + profile.getNumber());
            num++;
        }
        LOGGER.info(num +". Nevermind, take me back");
        LOGGER.info("\nPlease select the recipient for this shipment.");
        int recipientSelection = Integer.parseInt(input.nextLine());
        if (recipientSelection > profiles.size() + 1)
            throw new InvalidInputException("Menu selection not in range.");
        else if (recipientSelection > profiles.size())
            return null;
        else return profiles.get(recipientSelection);
    }
}
