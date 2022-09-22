package deliveryapp.dao_classes;

import deliveryapp.models.orders.ShipmentStatus;
import deliveryapp.utils.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShipmentStatusDAO implements IBaseDAO<ShipmentStatus>{
    private static final Logger LOGGER = Logger.getLogger(ShipmentStatusDAO.class.getName());
    private static final String GET_BY_ID = "SELECT * FROM shipment_status WHERE id = ?;";
    private static final String GET_ID_BY_SHIPMENT = "SELECT * FROM shipment_status WHERE shipmentID = ? AND delivered = ? AND date_arrived = ? AND date_departed = ?;";
    private static final String INSERT = "INSERT INTO shipment_status (shipmentID, delivered, date_arrived, date_departed) VALUES (?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE shipment_status SET shipmentID = ?, delivered = ?, date_arrived = ?, date_departed = ? WHERE id = ?;";

    @Override
    public ShipmentStatus getObjectByID(int id) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                ShipmentStatus p = new ShipmentStatus();
                p.setShipmentID(rs.getInt("shipmentID"));
                p.setDelivered(rs.getBoolean("delivered"));
                p.setDateArrived(rs.getDate("date_arrived"));
                p.setDateDeparted(rs.getDate("date_departed"));
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
    public int getIDbyObject(ShipmentStatus p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(GET_ID_BY_SHIPMENT);
            ps.setInt(1, p.getShipmentID());
            ps.setBoolean(2, p.isDelivered());
            ps.setDate(3, p.getDateArrived());
            ps.setDate(4, p.getDateDeparted());
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
    public int create(ShipmentStatus p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(INSERT);
            ps.setInt(1, p.getShipmentID());
            ps.setBoolean(2, p.isDelivered());
            ps.setDate(3, p.getDateArrived());
            ps.setDate(4, p.getDateDeparted());
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
    public void update(ShipmentStatus p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(UPDATE);
            ps.setInt(1, p.getShipmentID());
            ps.setBoolean(2, p.isDelivered());
            ps.setDate(3, p.getDateArrived());
            ps.setDate(4, p.getDateDeparted());
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
