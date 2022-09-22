package deliveryapp.services.jdbc;

import deliveryapp.dao_classes.UserDAO;
import deliveryapp.models.people.User;
import deliveryapp.services.UserService;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO = new UserDAO();;
    private static final Logger LOGGER = Logger.getLogger(AddressServiceImpl.class.getName());
    private static final Scanner input = new Scanner(System.in);

    @Override
    public User getUserByID(int id) {
        try {
            return userDAO.getObjectByID(id);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public int getIDbyUser(User u) {
        try {
            return userDAO.getIDbyObject(u);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    @Override
    public void createUser(User u) {
        try {
            u.setId(userDAO.create(u));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void updateUser(User u) {
        try {
            userDAO.update(u);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
