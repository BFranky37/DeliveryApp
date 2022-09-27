package deliveryapp.services.myBatis;

import deliveryapp.dao_classes.java.ProfileDAOimpl;
import deliveryapp.models.people.Profile;
import deliveryapp.services.ProfileService;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class ProfileServiceImpl implements ProfileService {
    private ProfileDAOimpl profileDAOimpl = new ProfileDAOimpl();;
    private static final Logger LOGGER = Logger.getLogger(ProfileDAOimpl.class.getName());
    private static final Scanner input = new Scanner(System.in);

    @Override
    public Profile addUserProfile(int addressID) {
        LOGGER.info("Please enter your name: ");
        String name = input.nextLine();
        LOGGER.info("Please enter your phone number: ");
        String phoneNumber = input.nextLine();
        Profile p = new Profile(name, phoneNumber, addressID);
        try {
            p.setId(profileDAOimpl.create(p));
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
    public void addRecipientProfile(int addressID) {

    }
}
