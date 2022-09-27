package deliveryapp.dao_classes;

import deliveryapp.models.orders.Package;
import deliveryapp.utils.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface PackageDAO extends IBaseDAO<Package> {
    @Override
    public Package getObjectByID(int id) throws SQLException;

    @Override
    public int getIDbyObject(Package p) throws SQLException;

    @Override
    public int create(Package p) throws SQLException;

    @Override
    public void update(Package p) throws SQLException;
}
