package deliveryapp.daoClasses;

import deliveryapp.models.orders.Package;

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
