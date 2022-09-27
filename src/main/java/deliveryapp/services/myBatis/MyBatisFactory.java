package deliveryapp.services.myBatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class MyBatisFactory {
    private static final Logger LOGGER = Logger.getLogger(MyBatisFactory.class.getName());
    private static SqlSessionFactory factory;

    private MyBatisFactory() {
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        if(factory == null) {
            initialize();
        }
        if(factory == null) {
            LOGGER.error("SqlSessionFactory failed to initialize");
        }
        return factory;
    }

    private static void initialize() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            factory = builder.build(reader);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
