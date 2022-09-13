package deliveryapp.utils;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class.getName());

    private static ConnectionPool pool;
    private List<Connection> connectionPool = new ArrayList<>(5);

    private ConnectionPool() {
        for (int i = 0; i < 5; i++) {
            try {
                connectionPool.add(DriverManager.getConnection("jdbc:mysql://localhost:3306/DeliveryApp", "root", "al48fh6!!boo"));
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    public static ConnectionPool getInstance() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }

    public synchronized Connection getConnection() {
        return connectionPool.remove(connectionPool.size() - 1);
    }

    public synchronized void returnConnection(Connection connection) {
        connectionPool.add(connection);
    }
}
