package deliveryapp.dao_classes.mybatis;

import deliveryapp.dao_classes.AddressDAO;
import deliveryapp.dao_classes.mybatis.mappers.AddressMapper;
import deliveryapp.models.people.Address;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;

public class AddressDAOimpl extends DAOimpl implements AddressDAO {

    @Override
    public Address getObjectByID(int id) {
        try (SqlSession session = this.sqlSessionFactory.openSession()) {
            return session.getMapper(AddressMapper.class).getAddressByID(id);
        }
    }

    @Override
    public int getIDbyObject(Address p) {
        try (SqlSession session = this.sqlSessionFactory.openSession()) {
            return session.getMapper(AddressMapper.class).getIDbyAddress(p);
        }
    }

    @Override
    public int create(Address p) {
        try (SqlSession session = this.sqlSessionFactory.openSession()) {
            session.getMapper(AddressMapper.class).createAddress(p);
            return getIDbyObject(p);
        }
    }

    @Override
    public void update(Address p) {
        throw new UnsupportedOperationException();
    }
}

