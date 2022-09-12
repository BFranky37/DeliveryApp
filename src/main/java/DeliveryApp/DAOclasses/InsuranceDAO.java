package DeliveryApp.DAOclasses;

import DeliveryApp.other.ConnectionPool;
import DeliveryApp.orders.Insurance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsuranceDAO {
    public Insurance getInsuranceByID(int id) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement("SELECT * FROM insurance WHERE id = ?;");
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
            throw new RuntimeException(e);
        } finally {
            assert rs != null;
            rs.close();
            ps.close();
            ConnectionPool.getInstance().returnConnection(c);
        }
        return null;
    }

    public int getIDbyInsurance(Insurance p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement("SELECT * FROM insurance WHERE " +
                    "name = ? AND base_cost = ? AND price_rate = ?;");
            ps.setString(1, p.getName());
            ps.setDouble(3, p.getCost());
            ps.setDouble(4, p.getRate());
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            assert rs != null;
            rs.close();
            ps.close();
            ConnectionPool.getInstance().returnConnection(c);
        }
        return -1;
    }

    public void createInsurance(Insurance p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement("INSERT INTO insurance (name, base_cost, price_rate) VALUES " +
                    "(?, ?, ?);"
            );
            ps.setString(1, p.getName());
            ps.setDouble(3, p.getCost());
            ps.setDouble(4, p.getRate());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            assert ps != null;
            ps.close();
            ConnectionPool.getInstance().returnConnection(c);
        }
    }

    public void updateInsurance(Insurance p) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement("UPDATE insurance " +
                    "SET name = ?, base_cost = ?, price_rate = ?" +
                    "WHERE id = ?;"
            );
            ps.setString(1, p.getName());
            ps.setDouble(3, p.getCost());
            ps.setDouble(4, p.getRate());
            ps.setInt(5, p.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            assert ps != null;
            ps.close();
            ConnectionPool.getInstance().returnConnection(c);
        }
    }
}
