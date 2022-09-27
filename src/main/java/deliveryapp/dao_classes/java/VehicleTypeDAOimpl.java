package deliveryapp.dao_classes.java;

import deliveryapp.dao_classes.VehicleTypeDAO;
import deliveryapp.models.vehicles.VehicleType;
import deliveryapp.utils.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleTypeDAOimpl implements VehicleTypeDAO {
    private static final Logger LOGGER = Logger.getLogger(VehicleTypeDAOimpl.class.getName());
    private static final String GET_BY_ID = "SELECT * FROM vehicle_types WHERE id = ?;";
    private static final String GET_ID_BY_VEHICLE_TYPE = "SELECT * FROM vehicle_types WHERE name = ? AND cost_rate = ? AND weight_capacity = ? AND space_capacity = ?;";
    private static final String INSERT = "INSERT INTO vehicle_types (name, cost_rate, weight_capacity, space_capacity) VALUES (?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE vehicle_types SET name = ?, cost_rate = ?, weight_capacity = ?, space_capacity = ? WHERE id = ?;";

    @Override
    public VehicleType getObjectByID(int id) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                VehicleType p = new VehicleType(rs.getString("name"), rs.getDouble("cost_rate"),
                        rs.getDouble("weight_capacity"), rs.getDouble("space_capacity"));
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
    public int getIDbyObject(VehicleType p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(GET_ID_BY_VEHICLE_TYPE);
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getRate());
            ps.setDouble(3, p.getMaxWeight());
            ps.setDouble(4, p.getMaxSize());
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
    public int create(VehicleType p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(INSERT);
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getRate());
            ps.setDouble(3, p.getMaxWeight());
            ps.setDouble(4, p.getMaxSize());
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
    public void update(VehicleType p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(UPDATE);
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getRate());
            ps.setDouble(3, p.getMaxWeight());
            ps.setDouble(4, p.getMaxSize());
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
