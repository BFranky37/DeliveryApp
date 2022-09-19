package deliveryapp.dao_classes;

import deliveryapp.models.orders.Shipment;
import deliveryapp.utils.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShipmentDAO implements IBaseDAO<Shipment>{
    private static final Logger LOGGER = Logger.getLogger(ShipmentDAO.class.getName());
    private static final String GET_BY_ID = "SELECT * FROM shipments WHERE id = ?;";
    private static final String GET_ID_BY_OBJECT = "SELECT * FROM shipments WHERE senderID = ? AND recipientID = ? AND packageID = ? AND insuranceID = ? AND routeID = ? AND vehicleID = ? AND priority = ? AND price = ?;";
    private static final String INSERT = "INSERT INTO shipments (senderID, recipientID, packageID, insuranceID, routeID, vehicleID, priority, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE shipments SET senderID = ?, recipientID = ?, packageID = ?, insuranceID = ?, routeID = ?, vehicleID = ?, priority = ?, price = ? WHERE id = ?;";

    @Override
    public Shipment getObjectByID(int id) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Shipment p = new Shipment(rs.getInt("senderID"), rs.getInt("recipientID"), rs.getInt("packageID"), rs.getInt("insuranceID"), rs.getBoolean("priority"));
                p.setRouteID(rs.getInt("routeID"));
                p.setVehicleID(rs.getInt("vehicleID"));
                p.setTotalPrice(rs.getDouble("price"));
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
    public int getIDbyObject(Shipment p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(GET_ID_BY_OBJECT);
            ps.setInt(1, p.getSenderID());
            ps.setInt(2, p.getRecipientID());
            ps.setInt(3, p.getPackageID());
            ps.setInt(4, p.getInsuranceID());
            ps.setInt(5, p.getRouteID());
            ps.setInt(6, p.getVehicleID());
            ps.setBoolean(7, p.getPrio());
            ps.setDouble(8, p.getSenderID());
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
    public void create(Shipment p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(INSERT);
            ps.setInt(1, p.getSenderID());
            ps.setInt(2, p.getRecipientID());
            ps.setInt(3, p.getPackageID());
            ps.setInt(4, p.getInsuranceID());
            ps.setInt(5, p.getRouteID());
            ps.setInt(6, p.getVehicleID());
            ps.setBoolean(7, p.getPrio());
            ps.setDouble(8, p.getSenderID());
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
    public void update(Shipment p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(UPDATE);
            ps.setInt(1, p.getSenderID());
            ps.setInt(2, p.getRecipientID());
            ps.setInt(3, p.getPackageID());
            ps.setInt(4, p.getInsuranceID());
            ps.setInt(5, p.getRouteID());
            ps.setInt(6, p.getVehicleID());
            ps.setBoolean(7, p.getPrio());
            ps.setDouble(8, p.getSenderID());
            ps.setInt(9, p.getId());
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
