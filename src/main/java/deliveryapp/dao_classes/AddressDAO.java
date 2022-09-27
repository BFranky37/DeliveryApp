package deliveryapp.dao_classes;

import deliveryapp.models.people.Address;
import deliveryapp.utils.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface AddressDAO extends IBaseDAO<Address> {
    @Override
    public Address getObjectByID(int id) throws SQLException;

    @Override
    public int getIDbyObject(Address p) throws SQLException;

    @Override
    public int create(Address p) throws SQLException;

    @Override
    public void update(Address p) throws SQLException;
}

