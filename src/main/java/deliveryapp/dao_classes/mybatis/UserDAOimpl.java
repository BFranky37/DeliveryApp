package deliveryapp.dao_classes.mybatis;

import deliveryapp.dao_classes.UserDAO;
import deliveryapp.dao_classes.mybatis.mappers.ProfileMapper;
import deliveryapp.dao_classes.mybatis.mappers.UserMapper;
import deliveryapp.models.people.User;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;

public class UserDAOimpl extends DAOimpl implements UserDAO {
    @Override
    public User getObjectByID(int id) {
        try (SqlSession session = this.sqlSessionFactory.openSession()) {
            return session.getMapper(UserMapper.class).getUserByID(id);
        }
    }

    @Override
    public int getIDbyObject(User u) {
        try (SqlSession session = this.sqlSessionFactory.openSession()) {
            return session.getMapper(UserMapper.class).getIDbyUser(u);
        }
    }

    @Override
    public int create(User u) {
        try (SqlSession session = this.sqlSessionFactory.openSession()) {
            session.getMapper(UserMapper.class).createUser(u);
            return getIDbyObject(u);
        }
    }

    @Override
    public void update(User u) {
        throw new UnsupportedOperationException();
    }
}
