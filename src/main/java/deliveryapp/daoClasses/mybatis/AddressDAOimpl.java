package deliveryapp.daoClasses.mybatis;

import deliveryapp.daoClasses.AddressDAO;
import deliveryapp.daoClasses.mybatis.mappers.AddressMapper;
import deliveryapp.models.people.Address;
import deliveryapp.services.myBatis.MyBatisFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

public class AddressDAOimpl implements AddressDAO {
    private final SqlSessionFactory sqlSessionFactory = MyBatisFactory.getSqlSessionFactory();
    private static final Logger LOGGER = Logger.getLogger(AddressDAOimpl.class.getName());

    @Override
    public Address getObjectByID(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.getMapper(AddressMapper.class).getAddressByID(id);
        }
    }

    @Override
    public int getIDbyObject(Address p) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.getMapper(AddressMapper.class).getIDbyAddress(p);
        }
    }

    @Override
    public int create(Address p) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.getMapper(AddressMapper.class).createAddress(p.getAddress(), p.getCity(), p.getZipcode());
            return getIDbyObject(p);
        }
    }

    @Override
    public void update(Address p) {
        throw new UnsupportedOperationException();
    }
}

