package deliveryapp.services;

import deliveryapp.models.people.Contact;
import deliveryapp.models.people.Profile;
import deliveryapp.models.people.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ContactService {
    public Contact getContactByID(int id) throws SQLException;

    public int getIDbyContact(Contact t) throws SQLException;

    public ArrayList<Profile> getContactsByUser(User u) throws SQLException;

    public void createContact(Contact t) throws SQLException;

    public void updateContact(Contact t) throws SQLException;
}
