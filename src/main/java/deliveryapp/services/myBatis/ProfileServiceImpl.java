package deliveryapp.services.myBatis;

import deliveryapp.models.people.Profile;
import deliveryapp.services.ProfileService;
import deliveryapp.services.myBatis.mappers.ProfileMapper;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class ProfileServiceImpl implements ProfileService {
    private final SqlSessionFactory sqlSessionFactory = MyBatisFactory.getSqlSessionFactory();
    private static final Logger LOGGER = Logger.getLogger(ProfileServiceImpl.class.getName());
    private static final Scanner input = new Scanner(System.in);

    @Override
    public Profile addUserProfile(int addressID) {
        LOGGER.info("Please enter your name: ");
        String name = input.nextLine();
        LOGGER.info("Please enter your phone number: ");
        String phoneNumber = input.nextLine();
        Profile p = new Profile(name, phoneNumber, addressID);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.getMapper(ProfileMapper.class).createProfile(p);
            p.setId(getIDbyProfile(p));
        }

        return p;
    }

    @Override
    public Profile getProfileByID(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.getMapper(ProfileMapper.class).getProfileByID(id);
        }
    }

    @Override
    public int getIDbyProfile(Profile p) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.getMapper(ProfileMapper.class).getIDbyProfile(p);
        }
    }

    @Override
    public void addRecipientProfile(int addressID) {
        throw new NotImplementedException();
    }
}
