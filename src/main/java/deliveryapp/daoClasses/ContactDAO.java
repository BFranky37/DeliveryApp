package deliveryapp.daoClasses;

import deliveryapp.models.orders.Box;
import deliveryapp.models.people.Contact;
import deliveryapp.models.people.Profile;
import deliveryapp.models.people.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ContactDAO extends IBaseDAO<Contact> {
    public Contact getObjectByID(int user, int profile) throws SQLException;

    @Override
    public int getIDbyObject(Contact t) throws SQLException;

    public ArrayList<Profile> getContactsByUser(User u) throws SQLException;

    @Override
    public int create(Contact t) throws SQLException;

    @Override
    public void update(Contact t) throws SQLException;
}
