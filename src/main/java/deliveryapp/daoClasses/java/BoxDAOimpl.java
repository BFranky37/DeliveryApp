package deliveryapp.daoClasses.java;

import deliveryapp.daoClasses.BoxDAO;
import deliveryapp.models.orders.Box;
import deliveryapp.utils.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoxDAOimpl implements BoxDAO {
    private static final Logger LOGGER = Logger.getLogger(BoxDAOimpl.class.getName());
    private static final String GET_BY_ID = "SELECT * FROM boxes WHERE id = ?;";
    private static final String GET_ID_BY_BOX = "SELECT * FROM boxes WHERE length = ? AND width = ? AND height = ?;";
    private static final String INSERT = "INSERT INTO boxes (length, width, height) VALUES (?, ?, ?);";
    private static final String UPDATE = "UPDATE boxes SET length = ?, width = ?, height = ? WHERE id = ?;";

    @Override
    public Box getObjectByID(int id) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Box p = new Box(rs.getDouble("length"), rs.getDouble("width"), rs.getDouble("height"));
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
    public int getIDbyObject(Box p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(GET_ID_BY_BOX);
            ps.setDouble(1, p.getLength());
            ps.setDouble(2, p.getWidth());
            ps.setDouble(3, p.getHeight());
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
    public int create(Box p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(INSERT);
            ps.setDouble(1, p.getLength());
            ps.setDouble(2, p.getWidth());
            ps.setDouble(3, p.getHeight());
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
    public void update(Box p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(UPDATE);
            ps.setDouble(1, p.getLength());
            ps.setDouble(2, p.getWidth());
            ps.setDouble(3, p.getHeight());
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
