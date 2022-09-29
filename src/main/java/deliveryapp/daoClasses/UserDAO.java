package deliveryapp.daoClasses;

import deliveryapp.models.people.User;

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
