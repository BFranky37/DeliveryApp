package deliveryapp.services.jdbc;

import deliveryapp.daoClasses.java.UserDAOimpl;
import deliveryapp.models.people.User;
import deliveryapp.services.UserService;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class UserServiceImpl implements UserService {
    private UserDAOimpl userDAOimpl = new UserDAOimpl();;
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());
    private static final Scanner input = new Scanner(System.in);

    @Override
    public User getUserByID(int id) {
        try {
            return userDAOimpl.getObjectByID(id);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public int getIDbyUser(User u) {
        try {
            return userDAOimpl.getIDbyObject(u);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    @Override
    public void createUser(User u) {
        try {
            u.setId(userDAOimpl.create(u));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void updateUser(User u) {
        try {
            userDAOimpl.update(u);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
