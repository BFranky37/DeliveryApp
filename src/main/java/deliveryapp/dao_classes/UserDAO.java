package deliveryapp.dao_classes;

import deliveryapp.models.people.User;
import deliveryapp.utils.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserDAO extends IBaseDAO<User> {
    @Override
    public User getObjectByID(int id) throws SQLException;

    @Override
    public int getIDbyObject(User p) throws SQLException;

    @Override
    public int create(User p) throws SQLException;

    @Override
    public void update(User p) throws SQLException;
}
