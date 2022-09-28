package deliveryapp.daoClasses.mybatis;

import deliveryapp.daoClasses.UserDAO;
import deliveryapp.daoClasses.mybatis.mappers.UserMapper;
import deliveryapp.models.people.User;
import deliveryapp.services.myBatis.MyBatisFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserDAOimpl implements UserDAO {

    private final SqlSessionFactory sqlSessionFactory = MyBatisFactory.getSqlSessionFactory();

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
