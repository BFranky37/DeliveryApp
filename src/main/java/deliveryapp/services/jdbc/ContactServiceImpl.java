package deliveryapp.services.jdbc;

import deliveryapp.daoClasses.java.ContactDAOimpl;
import deliveryapp.models.people.Contact;
import deliveryapp.models.people.Profile;
import deliveryapp.models.people.User;
import deliveryapp.services.ContactService;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;

public class ContactServiceImpl implements ContactService {
    private final ContactDAOimpl contactDAOimpl = new ContactDAOimpl();;
    private static final Logger LOGGER = Logger.getLogger(ContactServiceImpl.class.getName());

    @Override
    public Contact getContactByID(int id) throws SQLException {
        try {
            return contactDAOimpl.getObjectByID(id);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public int getIDbyContact(Contact c) throws SQLException {
        try {
            return contactDAOimpl.getIDbyObject(c);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    @Override
    public ArrayList<Profile> getContactsByUser(User u) throws SQLException {
        try {
            return contactDAOimpl.getContactsByUser(u);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public void createContact(Contact c) throws SQLException {
        try {
            contactDAOimpl.create(c);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void updateContact(Contact c) throws SQLException {
        try {
            contactDAOimpl.update(c);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
