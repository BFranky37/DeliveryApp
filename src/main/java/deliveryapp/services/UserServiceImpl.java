package deliveryapp.services;

import deliveryapp.dao_classes.UserDAO;
import deliveryapp.models.people.User;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class UserServiceImpl implements UserService{
    private UserDAO userDAO;
    private static final Logger LOGGER = Logger.getLogger(AddressServiceImpl.class.getName());
    private static final Scanner input = new Scanner(System.in);

    @Override
    public User getUserByID(int id) {
        try {
            return userDAO.getUserByID(id);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public int getIDbyUser(User u) {
        try {
            return userDAO.getIDbyUser(u);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    @Override
    public void createUser(User u) {
        try {
            userDAO.createUser(u);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void updateUser(User u) {
        try {
            userDAO.updateUser(u);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
