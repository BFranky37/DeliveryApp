package DeliveryApp.services;

import DeliveryApp.DAOclasses.ProfileDAO;
import DeliveryApp.people.Profile;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class ProfileService {
    private ProfileDAO profileDAO;
    private static final Logger LOGGER = Logger.getLogger(ProfileDAO.class.getName());
    private static final Scanner input = new Scanner(System.in);

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

    public Profile getProfileByID(int id) {
        try {
            return profileDAO.getProfileByID(id);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    public int getIDbyProfile(Profile p) {
        try {
            return profileDAO.getIDbyProfile(p);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    public void addRecipientProfile() {

    }
}
