package DeliveryApp.services;

import DeliveryApp.DAOclasses.AddressDAO;
import DeliveryApp.DAOclasses.UserDAO;
import DeliveryApp.other.ConnectionPool;
import DeliveryApp.people.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserService {
    private UserDAO userDAO;
    private static final Logger LOGGER = Logger.getLogger(AddressService.class.getName());
    private static final Scanner input = new Scanner(System.in);

    public User getUserByID(int id) {
        try {
            return userDAO.getUserByID(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getIDbyUser(User u) {
        try {
            return userDAO.getIDbyUser(u);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUser(User u) {
        try {
            userDAO.createUser(u);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUser(User u) {
        try {
            userDAO.updateUser(u);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
