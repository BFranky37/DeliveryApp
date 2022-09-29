package deliveryapp.daoClasses.java;

import deliveryapp.daoClasses.PackageDAO;
import deliveryapp.models.orders.Package;
import deliveryapp.utils.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PackageDAOimpl implements PackageDAO {
    private static final Logger LOGGER = Logger.getLogger(PackageDAOimpl.class.getName());
    private static final String GET_BY_ID = "SELECT * FROM packages WHERE id = ?;";
    private static final String GET_ID_BY_PACKAGE = "SELECT * FROM packages WHERE boxID = ? AND weight = ? AND value = ? AND fragility = ? AND cost = ?;";
    private static final String INSERT = "INSERT INTO packages (boxID, weight, value, fragility, cost) VALUES (?, ?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE packages SET boxID = ?, weight = ?, value = ?, fragility = ?, cost = ? WHERE id = ?;";

    @Override
    public Package getObjectByID(int id) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Package p = new Package(rs.getInt("boxID"), rs.getDouble("weight"),
                        rs.getDouble("value"), rs.getBoolean("fragility"));
                p.setPrice(rs.getDouble("cost"));
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
    public int getIDbyObject(Package p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(GET_ID_BY_PACKAGE);
            ps.setInt(1, p.getBoxID());
            ps.setDouble(2, p.getWeight());
            ps.setDouble(3, p.getValue());
            ps.setBoolean(4, p.getFragility());
            ps.setDouble(5, p.getPrice());
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
    public int create(Package p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(INSERT);
            ps.setInt(1, p.getBoxID());
            ps.setDouble(2, p.getWeight());
            ps.setDouble(3, p.getValue());
            ps.setBoolean(4, p.getFragility());
            ps.setDouble(5, p.getPrice());
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
    public void update(Package p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(UPDATE);
            ps.setInt(1, p.getBoxID());
            ps.setDouble(2, p.getWeight());
            ps.setDouble(3, p.getValue());
            ps.setBoolean(4, p.getFragility());
            ps.setDouble(5, p.getPrice());
            ps.setInt(6, p.getId());
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
