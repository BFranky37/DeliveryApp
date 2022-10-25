package deliveryapp.daoClasses.java;

import deliveryapp.daoClasses.AddressDAO;
import deliveryapp.utils.ConnectionPool;
import deliveryapp.models.people.Address;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDAOimpl implements AddressDAO {
    private static final Logger LOGGER = Logger.getLogger(AddressDAOimpl.class.getName());
    private static final String GET_BY_ID = "SELECT * FROM addresses WHERE id = ?;";
    private static final String GET_BY_USER_ID = "SELECT * FROM addresses WHERE id IN (SELECT addressID FROM profiles WHERE id IN (SELECT profileID FROM users WHERE id = ?));";
    private static final String GET_BY_PROFILE_ID = "SELECT * FROM addresses WHERE id IN (SELECT addressID FROM profiles WHERE id = ?);";
    private static final String GET_ID_BY_ADDRESS = "SELECT * FROM addresses WHERE street = ? AND city = ? AND zipcode = ?;";
    private static final String INSERT = "INSERT INTO addresses (street, city, zipcode) VALUES (?, ?, ?);";
    private static final String UPDATE = "UPDATE addresses SET street = ?, city = ?, zipcode = ? WHERE id = ?;";
    private static final String DELETE = "DELETE FROM addresses WHERE id = ?;";
    private static final String GET_NUM_ADDRESSES = "SELECT count(id) FROM addresses;";

    public Address getObjectByID(int id) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Address p = new Address(rs.getString("street"), rs.getString("city"),
                        rs.getInt("zipcode"));
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

    public Address getAddressByUserID(int id) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(GET_BY_USER_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Address p = new Address(rs.getString("street"), rs.getString("city"),
                        rs.getInt("zipcode"));
                p.setId(rs.getInt("id"));
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

    public Address getAddressByProfileID(int id) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(GET_BY_PROFILE_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Address p = new Address(rs.getString("street"), rs.getString("city"),
                        rs.getInt("zipcode"));
                p.setId(rs.getInt("id"));
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

    public int getIDbyObject(Address p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(GET_ID_BY_ADDRESS);
            ps.setString(1, p.getAddress());
            ps.setString(2, p.getCity());
            ps.setInt(3, p.getZipcode());
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

    public int create(Address p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(INSERT);
            ps.setString(1, p.getAddress());
            ps.setString(2, p.getCity());
            ps.setInt(3, p.getZipcode());
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

    public void update(Address p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(UPDATE);
            ps.setString(1, p.getAddress());
            ps.setString(2, p.getCity());
            ps.setInt(3, p.getZipcode());
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

    public void delete(int id) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(DELETE);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            assert ps != null;
            ps.close();
            ConnectionPool.getInstance().returnConnection(c);
        }
    }

    public int getNumAddresses() throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(GET_NUM_ADDRESSES);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("count(id)");
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
}

