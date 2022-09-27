package deliveryapp.dao_classes.mybatis;

import deliveryapp.dao_classes.BoxDAO;
import deliveryapp.dao_classes.mybatis.mappers.BoxMapper;
import deliveryapp.models.orders.Box;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;

public class BoxDAOimpl extends DAOimpl implements BoxDAO {
    public BoxDAOimpl() {
        super();
    }

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
