package deliveryapp.daoClasses.java;

import deliveryapp.daoClasses.ProfileDAO;
import deliveryapp.utils.ConnectionPool;
import deliveryapp.models.people.Profile;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileDAOimpl implements ProfileDAO {
    private static final Logger LOGGER = Logger.getLogger(ProfileDAOimpl.class.getName());
    private static final String GET_BY_ID = "SELECT * FROM profiles WHERE id = ?;";
    private static final String GET_ID_BY_PROFILE = "SELECT * FROM profiles WHERE name = ? AND phone_number = ? AND addressID = ?;";
    private static final String INSERT = "INSERT INTO profiles (name, phone_number, addressID) VALUES (?, ?, ?);";
    private static final String UPDATE = "UPDATE profiles SET name = ?, phone_number = ?, addressID = ? WHERE id = ?;";

    @Override
    public Profile getObjectByID(int id) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Profile p = new Profile(rs.getString("name"), rs.getString("phone_number"),
                        rs.getInt("addressID"));
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
        throw new SQLException("No data matching the ID given");
    }

    @Override
    public int getIDbyObject(Profile p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(GET_ID_BY_PROFILE);
            ps.setString(1, p.getName());
            ps.setString(2, p.getNumber());
            ps.setInt(3, p.getAddressID());
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
        throw new SQLException("No data matching the Object given");
    }

    @Override
    public int create(Profile p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(INSERT);
            ps.setString(1, p.getName());
            ps.setString(2, p.getNumber());
            ps.setInt(3, p.getAddressID());
            ps.executeUpdate();

            return getIDbyObject(p);
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
    public void update(Profile p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(UPDATE);
            ps.setString(1, p.getName());
            ps.setString(2, p.getNumber());
            ps.setInt(3, p.getAddressID());
            ps.setInt(4, p.getId());
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
