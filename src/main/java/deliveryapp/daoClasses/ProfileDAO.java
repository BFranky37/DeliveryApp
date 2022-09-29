package deliveryapp.daoClasses;

import deliveryapp.models.people.Profile;

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
