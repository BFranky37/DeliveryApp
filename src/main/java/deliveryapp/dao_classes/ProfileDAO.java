package deliveryapp.dao_classes;

import deliveryapp.utils.ConnectionPool;
import deliveryapp.models.people.Profile;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileDAO {
    private static final Logger LOGGER = Logger.getLogger(ProfileDAO.class.getName());
    
    public Profile getProfileByID(int id) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement("SELECT * FROM profiles WHERE id = ?;");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Profile p = new Profile(rs.getString("name"), rs.getString("phone_number"),
                        rs.getInt("addressID"), rs.getInt("userID"));
                p.setId(id);
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
        return null;
    }

    public int getIDbyProfile(Profile p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement("SELECT * FROM profiles WHERE " +
                    "name = ? AND phone_number = ? AND addressID = ? AND userID = ?;");
            ps.setString(1, p.getName());
            ps.setString(2, p.getNumber());
            ps.setInt(3, p.getAddressID());
            ps.setInt(4, p.getAddedByUserID());
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            assert rs != null;
            rs.close();
            ps.close();
            ConnectionPool.getInstance().returnConnection(c);
        }
        return -1;
    }

    public void createProfile(Profile p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement("INSERT INTO profiles (name, phone_number, addressID, userID) VALUES " +
                    "(?, ?, ?, ? );"
            );
            ps.setString(1, p.getName());
            ps.setString(2, p.getNumber());
            ps.setInt(3, p.getAddressID());
            ps.setInt(4, p.getAddedByUserID());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            assert ps != null;
            ps.close();
            ConnectionPool.getInstance().returnConnection(c);
        }
    }

    public void updateProfile(Profile p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement("UPDATE profiles " +
                    "SET name = ?, phone_number = ?, addressID = ?, userID = ?" +
                    "WHERE id = ?;"
            );
            ps.setString(1, p.getName());
            ps.setString(2, p.getNumber());
            ps.setInt(3, p.getAddressID());
            ps.setInt(4, p.getAddedByUserID());
            ps.setInt(5, p.getId());
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
