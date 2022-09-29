package deliveryapp.services.myBatis;

import deliveryapp.models.people.User;
import deliveryapp.services.UserService;
import deliveryapp.services.myBatis.mappers.ProfileMapper;
import deliveryapp.services.myBatis.mappers.UserMapper;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

public class UserServiceImpl implements UserService {
    private final SqlSessionFactory sqlSessionFactory = MyBatisFactory.getSqlSessionFactory();
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());

    @Override
    public User getUserByID(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.getMapper(UserMapper.class).getUserByID(id);
        }
    }

    @Override
    public int getIDbyUser(User u) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.getMapper(UserMapper.class).getIDbyUser(u);
        }
    }

    @Override
    public void createUser(User u) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.getMapper(UserMapper.class).createUser(u);
        }
    }

    @Override
    public void updateUser(User u) {
        throw new NotImplementedException();
    }
}
