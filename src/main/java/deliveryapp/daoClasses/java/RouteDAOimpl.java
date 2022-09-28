package deliveryapp.daoClasses.java;

import deliveryapp.daoClasses.RouteDAO;
import deliveryapp.models.vehicles.Route;
import deliveryapp.utils.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteDAOimpl implements RouteDAO {
    private static final Logger LOGGER = Logger.getLogger(ProfileDAOimpl.class.getName());
    private static final String GET_BY_ID = "SELECT * FROM routes WHERE id = ?;";
    private static final String GET_ID_BY_ROUTE = "SELECT * FROM routes WHERE from_addressID = ? AND to_addressID = ?;";
    private static final String INSERT = "INSERT INTO routes (distance, price, from_addressID, to_addressID) VALUES (?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE routes SET distance = ?, price = ?, from_addressID = ?, to_addressID = ? WHERE id = ?;";

    @Override
    public Route getObjectByID(int id) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Route p = new Route(rs.getInt("from_addressID"), rs.getInt("to_addressID"));
                p.setDistance(rs.getInt("distance"));
                p.setPrice(rs.getDouble("price"));
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
    public int getIDbyObject(Route p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(GET_ID_BY_ROUTE);
            ps.setInt(1, p.getFromLocation());
            ps.setInt(2, p.getToLocation());
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
    public int create(Route p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(INSERT);
            ps.setInt(1, p.getDistance());
            ps.setDouble(2, p.getPrice());
            ps.setInt(3, p.getFromLocation());
            ps.setInt(4, p.getToLocation());
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
    public void update(Route p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(UPDATE);
            ps.setInt(1, p.getDistance());
            ps.setDouble(2, p.getPrice());
            ps.setInt(3, p.getFromLocation());
            ps.setInt(3, p.getToLocation());
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
