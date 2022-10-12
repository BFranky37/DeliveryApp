package deliveryapp.daoClasses;

import deliveryapp.models.people.Discount;
import deliveryapp.models.people.User;

import java.sql.SQLException;
import java.util.List;

public interface DiscountDAO extends IBaseDAO<Discount> {
    @Override
    public Discount getObjectByID(int id) throws SQLException;

    public List<Discount> getDiscountsByUser(int userID) throws SQLException;

    @Override
    public int getIDbyObject(Discount p) throws SQLException;

    @Override
    public int create(Discount p) throws SQLException;

    @Override
    public void update(Discount p) throws SQLException;
}

