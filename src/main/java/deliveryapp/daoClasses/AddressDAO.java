package deliveryapp.daoClasses;

import deliveryapp.models.people.Address;

import java.sql.SQLException;

public interface AddressDAO extends IBaseDAO<Address> {
    @Override
    public Address getObjectByID(int id) throws SQLException;

    public Address getAddressByUserID(int id) throws SQLException;

    public Address getAddressByProfileID(int id) throws SQLException;

    @Override
    public int getIDbyObject(Address p) throws SQLException;

    @Override
    public int create(Address p) throws SQLException;

    @Override
    public void update(Address p) throws SQLException;
}

