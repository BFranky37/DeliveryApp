package deliveryapp.dao_classes;

import deliveryapp.utils.ConnectionPool;
import deliveryapp.models.people.Discount;
import deliveryapp.models.people.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscountDAO {
    private static final Logger LOGGER = Logger.getLogger(DiscountDAO.class.getName());

    public Discount getDiscountByID(int id) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement("SELECT * FROM discounts WHERE id = ?;");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Discount d = null;
                d.setName(rs.getString("name"));
                d.setDiscountRate(rs.getDouble("rate"));
                d.setId(id);
                return d;
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

    public int getIDbyUser(User u) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement("SELECT * FROM user_has_discount WHERE userID = ?;");
            ps.setInt(1, u.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("discountID");
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

    public int getIDbyDiscount(Discount p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement("SELECT * FROM discounts WHERE " +
                    "name = ? AND rate = ?;");
            ps.setString(1, p.getName());
            ps.setDouble(4, p.getDiscountRate());
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

    public void createDiscount(Discount p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement("INSERT INTO discounts (name, rate) VALUES " +
                    "(?, ?);"
            );
            ps.setString(1, p.getName());
            ps.setDouble(4, p.getDiscountRate());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            assert ps != null;
            ps.close();
            ConnectionPool.getInstance().returnConnection(c);
        }
    }

    public void updateDiscount(Discount p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement("UPDATE discounts " +
                    "SET name = ?, rate = ?" +
                    "WHERE id = ?;"
            );
            ps.setString(1, p.getName());
            ps.setDouble(4, p.getDiscountRate());
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

