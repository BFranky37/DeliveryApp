package deliveryapp.daoClasses;

import deliveryapp.models.people.Discount;
import deliveryapp.models.people.User;

import java.sql.SQLException;

public interface DiscountDAO extends IBaseDAO<Discount> {
    @Override
    public Discount getObjectByID(int id) throws SQLException;

    public int getIDbyObject(User u) throws SQLException;

    @Override
    public int getIDbyObject(Discount p) throws SQLException;

    @Override
    public int create(Discount p) throws SQLException;

    @Override
    public void update(Discount p) throws SQLException;
}

