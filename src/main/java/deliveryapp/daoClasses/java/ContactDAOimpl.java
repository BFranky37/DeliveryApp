package deliveryapp.daoClasses.java;

import deliveryapp.daoClasses.ContactDAO;
import deliveryapp.models.people.Contact;
import deliveryapp.models.people.Profile;
import deliveryapp.models.people.User;
import deliveryapp.utils.ConnectionPool;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContactDAOimpl implements ContactDAO {
    private static final Logger LOGGER = Logger.getLogger(ContactDAOimpl.class.getName());

    private static final String GET_BY_ID = "SELECT * FROM contacts WHERE userID = ? AND profileID = ?;";
    private static final String GET_CONTACTS_BY_USER = "SELECT * FROM profiles WHERE id IN (SELECT profileID FROM contacts WHERE userID = ?);";
    private static final String GET_ID_BY_CONTACT = "SELECT * FROM contacts WHERE profileID = ? AND relationship = ? AND date_added = ?;";
    private static final String INSERT = "INSERT INTO contacts (userID, profileID, relationship, date_added) VALUES (?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE contacts SET relationship = ? WHERE userID = ? AND profileID = ?;";

    @Override
    public Contact getObjectByID(int user, int profile) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(GET_BY_ID);
            ps.setInt(1, user);
            ps.setInt(2, profile);
            rs = ps.executeQuery();
            while (rs.next()) {
                Contact p = new Contact(rs.getInt("userID"), rs.getInt("profileID"), rs.getString("relationship"));
                p.setDateAdded(rs.getDate("date_added"));
                return p;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            assert rs != null;
            rs.close();
            ps.close();
            ConnectionPool.getInstance().returnConnection(c);
        }
        throw new SQLException("No data matching the ID given");
    }

    @Override
    public Contact getObjectByID(int id) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public int getIDbyObject(Contact con) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(GET_ID_BY_CONTACT);
            ps.setInt(1, con.getProfileID());
            ps.setString(2, con.getRelationship());
            ps.setDate(3, con.getDateAdded());
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("userID");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            assert rs != null;
            rs.close();
            ps.close();
            ConnectionPool.getInstance().returnConnection(c);
        }
        throw new SQLException("No data matching the Object given");
    }

    @Override
    public ArrayList<Profile> getContactsByUser(User u) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(GET_CONTACTS_BY_USER);
            ps.setInt(1, u.getId());
            rs = ps.executeQuery();

            ArrayList<Profile> contacts = new ArrayList<Profile>();
            while (rs.next()) {
                Profile p = new Profile(rs.getString("name"), rs.getString("phone_number"), rs.getInt("addressID"));
                p.setId(rs.getInt("id"));
                contacts.add(p);
            }

            return contacts;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            assert rs != null;
            rs.close();
            ps.close();
            ConnectionPool.getInstance().returnConnection(c);
        }
        throw new SQLException("No data matching the ID given");
    }

    @Override
    public int create(Contact con) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(INSERT);
            ps.setInt(1, con.getUserID());
            ps.setInt(2, con.getProfileID());
            ps.setString(3, con.getRelationship());
            ps.setDate(4, con.getDateAdded());
            ps.executeUpdate();

            return getIDbyObject(con);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            assert ps != null;
            ps.close();
            ConnectionPool.getInstance().returnConnection(c);
        }
        throw new SQLException("Could not get ID of newly created object");
    }

    @Override
    public void update(Contact con) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(UPDATE);
            ps.setString(1, con.getRelationship());
            ps.setInt(2, con.getUserID());
            ps.setInt(3, con.getProfileID());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            assert ps != null;
            ps.close();
            ConnectionPool.getInstance().returnConnection(c);
        }
    }
}
