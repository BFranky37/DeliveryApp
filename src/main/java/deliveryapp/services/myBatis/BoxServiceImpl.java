package deliveryapp.services.myBatis;

import deliveryapp.models.orders.Box;
import deliveryapp.services.BoxService;
import deliveryapp.services.myBatis.mappers.BoxMapper;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class BoxServiceImpl implements BoxService {
    private final SqlSessionFactory sqlSessionFactory = MyBatisFactory.getSqlSessionFactory();
    private static final Logger LOGGER = Logger.getLogger(BoxServiceImpl.class.getName());
    private static final Scanner input = new Scanner(System.in);

    @Override
    public Box getBoxByID(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.getMapper(BoxMapper.class).getBoxByID(id);
        }
    }

    @Override
    public int getIDbyBox(Box u) {
        throw new NotImplementedException();
    }

    @Override
    public void createBox(Box u) {
        throw new NotImplementedException();
    }

    @Override
    public void updateBox(Box u) {
        throw new NotImplementedException();
    }
}
