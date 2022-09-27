package deliveryapp.services.myBatis;

import deliveryapp.dao_classes.mybatis.UserDAOimpl;
import deliveryapp.models.people.User;
import deliveryapp.services.UserService;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class UserServiceImpl implements UserService {
    private final UserDAOimpl userDAOimpl = new UserDAOimpl();;
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());

    @Override
    public User getUserByID(int id) {
        return userDAOimpl.getObjectByID(id);
    }

    @Override
    public int getIDbyUser(User u) {
        return userDAOimpl.getIDbyObject(u);
    }

    @Override
    public void createUser(User u) {
        u.setId(userDAOimpl.create(u));
    }

    @Override
    public void updateUser(User u) {
        userDAOimpl.update(u);
    }
}
