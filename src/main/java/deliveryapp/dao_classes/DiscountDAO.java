package deliveryapp.dao_classes;

import deliveryapp.models.people.Discount;
import deliveryapp.models.people.User;
import deliveryapp.utils.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

