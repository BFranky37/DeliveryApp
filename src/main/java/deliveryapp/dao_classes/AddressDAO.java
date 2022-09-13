package deliveryapp.dao_classes;

import deliveryapp.utils.ConnectionPool;
import deliveryapp.models.people.Address;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDAO {
    private static final Logger LOGGER = Logger.getLogger(AddressDAO.class.getName());
    public Address getAddressByID(int id) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement("SELECT * FROM addresses WHERE id = ?;");
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
        return null;
    }

    public int getIDbyAddress(Address p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement("SELECT * FROM addresses WHERE " +
                    "street = ? AND city = ? AND zipcode = ?;");
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
        return -1;
    }

    public void createAddress(Address p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement("INSERT INTO addresses (street, city, zipcode) VALUES " +
                    "(?, ?, ?);"
            );
            ps.setString(1, p.getAddress());
            ps.setString(2, p.getCity());
            ps.setInt(3, p.getZipcode());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            assert ps != null;
            ps.close();
            ConnectionPool.getInstance().returnConnection(c);
        }
    }

    public void updateAddress(Address p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement("UPDATE addresses " +
                    "SET street = ?, city = ?, zipcode = ?" +
                    "WHERE id = ?;"
            );
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
}

