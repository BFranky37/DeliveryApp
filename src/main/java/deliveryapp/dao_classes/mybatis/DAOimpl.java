package deliveryapp.dao_classes.mybatis;

import deliveryapp.services.myBatis.MyBatisFactory;
import org.apache.ibatis.session.SqlSessionFactory;

public abstract class DAOimpl {
    protected SqlSessionFactory sqlSessionFactory;

    protected void DAOImpl() {
        sqlSessionFactory = MyBatisFactory.getSqlSessionFactory();
    }
}
