package deliveryapp.dao_classes.mybatis;

import deliveryapp.dao_classes.BoxDAO;
import deliveryapp.dao_classes.mybatis.mappers.BoxMapper;
import deliveryapp.models.orders.Box;
import deliveryapp.services.myBatis.MyBatisFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.SQLException;

public class BoxDAOimpl implements BoxDAO {
    private final SqlSessionFactory sqlSessionFactory = MyBatisFactory.getSqlSessionFactory();

    @Override
    public Box getObjectByID(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.getMapper(BoxMapper.class).getBoxByID(id);
        }
    }

    @Override
    public int getIDbyObject(Box p) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int create(Box p) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Box p) {
        throw new UnsupportedOperationException();
    }
}
