package deliveryapp.dao_classes.mybatis;

import deliveryapp.dao_classes.InsuranceDAO;
import deliveryapp.models.orders.Insurance;
import deliveryapp.utils.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsuranceDAOimpl implements InsuranceDAO {
    private static final Logger LOGGER = Logger.getLogger(InsuranceDAOimpl.class.getName());
    private static final String GET_BY_ID = "SELECT * FROM insurance WHERE id = ?;";
    private static final String GET_ID_BY_INSURANCE = "SELECT * FROM insurance WHERE name = ? AND base_cost = ? AND price_rate = ?;";
    private static final String INSERT = "INSERT INTO insurance (name, base_cost, price_rate) VALUES (?, ?, ?);";
    private static final String UPDATE = "UPDATE insurance SET name = ?, base_cost = ?, price_rate = ? WHERE id = ?;";

    @Override
    public Insurance getObjectByID(int id) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Insurance p = null;
                p.setName(rs.getString("name"));
                p.setCost(rs.getDouble("base_cost"));
                p.setRate(rs.getDouble("price_rate"));
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
    public int getIDbyObject(Insurance p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(GET_ID_BY_INSURANCE);
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getCost());
            ps.setDouble(3, p.getRate());
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
    public int create(Insurance p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(INSERT);
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getCost());
            ps.setDouble(3, p.getRate());
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
    public void update(Insurance p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(UPDATE);
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getCost());
            ps.setDouble(3, p.getRate());
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
