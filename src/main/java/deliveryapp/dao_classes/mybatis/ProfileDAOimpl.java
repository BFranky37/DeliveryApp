package deliveryapp.dao_classes.mybatis;

import deliveryapp.dao_classes.ProfileDAO;
import deliveryapp.dao_classes.mybatis.mappers.ProfileMapper;
import deliveryapp.models.people.Profile;
import deliveryapp.services.myBatis.MyBatisFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class ProfileDAOimpl implements ProfileDAO {

    private final SqlSessionFactory sqlSessionFactory = MyBatisFactory.getSqlSessionFactory();

    @Override
    public Profile getObjectByID(int id) {
        try (SqlSession session = this.sqlSessionFactory.openSession()) {
            return session.getMapper(ProfileMapper.class).getProfileByID(id);
        }
    }

    @Override
    public int getIDbyObject(Profile p) {
        try (SqlSession session = this.sqlSessionFactory.openSession()) {
            return session.getMapper(ProfileMapper.class).getIDbyProfile(p);
        }
    }

    @Override
    public int create(Profile p) {
        try (SqlSession session = this.sqlSessionFactory.openSession()) {
            session.getMapper(ProfileMapper.class).createProfile(p);
            return getIDbyObject(p);
        }
    }

    @Override
    public void update(Profile p) {
        throw new UnsupportedOperationException();
    }
}
