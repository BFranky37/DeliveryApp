package deliveryapp.services;

import deliveryapp.dao_classes.ProfileDAO;
import deliveryapp.models.people.Profile;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class ProfileServiceImpl implements ProfileService{
    private ProfileDAO profileDAO;
    private static final Logger LOGGER = Logger.getLogger(ProfileDAO.class.getName());
    private static final Scanner input = new Scanner(System.in);

    @Override
    public Profile addUserProfile(int addressID) {
        LOGGER.info("Please enter your name: ");
        String name = input.nextLine();
        LOGGER.info("Please enter your phone number: ");
        String phoneNumber = input.nextLine();
        Profile p = new Profile(name, phoneNumber, addressID, 0);
        try {
            profileDAO.createProfile(p);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return p;
    }

    @Override
    public Profile getProfileByID(int id) {
        try {
            return profileDAO.getProfileByID(id);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public int getIDbyProfile(Profile p) {
        try {
            return profileDAO.getIDbyProfile(p);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    @Override
    public void addRecipientProfile(int addressID) {

    }
}
