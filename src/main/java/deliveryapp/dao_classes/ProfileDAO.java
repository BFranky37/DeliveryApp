package deliveryapp.dao_classes;

import deliveryapp.models.people.Profile;
import deliveryapp.utils.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ProfileDAO extends IBaseDAO<Profile> {
    @Override
    public Profile getObjectByID(int id) throws SQLException;

    @Override
    public int getIDbyObject(Profile p) throws SQLException;

    @Override
    public int create(Profile p) throws SQLException;

    @Override
    public void update(Profile p) throws SQLException;
}
