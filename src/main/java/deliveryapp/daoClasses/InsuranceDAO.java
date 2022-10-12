package deliveryapp.daoClasses;

import deliveryapp.models.orders.Insurance;

import java.sql.SQLException;

public interface InsuranceDAO extends IBaseDAO<Insurance> {
    @Override
    public Insurance getObjectByID(int id) throws SQLException;

    public Insurance getObjectByName(String name) throws SQLException;

    @Override
    public int getIDbyObject(Insurance p) throws SQLException;

    @Override
    public int create(Insurance p) throws SQLException;

    @Override
    public void update(Insurance p) throws SQLException;
}
