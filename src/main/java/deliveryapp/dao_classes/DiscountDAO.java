package deliveryapp.dao_classes;

import deliveryapp.utils.ConnectionPool;
import deliveryapp.models.people.Discount;
import deliveryapp.models.people.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscountDAO implements IBaseDAO<Discount>{
    private static final Logger LOGGER = Logger.getLogger(DiscountDAO.class.getName());
    private static final String GET_BY_ID = "SELECT * FROM discounts WHERE id = ?;";
    private static final String GET_ID_BY_USER = "SELECT * FROM user_has_discount WHERE userID = ?;";
    private static final String GET_ID_BY_DISCOUNT = "SELECT * FROM discounts WHERE name = ? AND rate = ?;";
    private static final String INSERT = "INSERT INTO discounts (name, rate) VALUES (?, ?);";
    private static final String UPDATE = "UPDATE discounts SET name = ?, rate = ? WHERE id = ?;";

    @Override
    public Discount getObjectByID(int id) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(GET_BY_ID);
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

    public int getIDbyObject(User u) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(GET_ID_BY_USER);
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

    @Override
    public int getIDbyObject(Discount p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(GET_ID_BY_DISCOUNT);
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getDiscountRate());
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

    @Override
    public void create(Discount p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(INSERT);
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getDiscountRate());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            assert ps != null;
            ps.close();
            ConnectionPool.getInstance().returnConnection(c);
        }
    }

    @Override
    public void update(Discount p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(UPDATE);
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getDiscountRate());
            ps.setInt(3, p.getId());
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

