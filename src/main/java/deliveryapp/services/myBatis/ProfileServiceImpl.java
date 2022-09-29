package deliveryapp.services.myBatis;

import deliveryapp.models.people.Address;
import deliveryapp.models.people.Profile;
import deliveryapp.models.people.User;
import deliveryapp.services.AddressService;
import deliveryapp.services.ProfileService;
import deliveryapp.services.jdbc.AddressServiceImpl;
import deliveryapp.services.myBatis.mappers.ProfileMapper;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Scanner;

public class ProfileServiceImpl implements ProfileService {
    private final SqlSessionFactory sqlSessionFactory = MyBatisFactory.getSqlSessionFactory();
    private static final Logger LOGGER = Logger.getLogger(ProfileServiceImpl.class.getName());
    private static final Scanner input = new Scanner(System.in);

    @Override
    public Profile addUserProfile() {
        LOGGER.info("Please enter your name: ");
        String name = input.nextLine();
        LOGGER.info("Please enter your phone number: ");
        String phoneNumber = input.nextLine();

        AddressService addressService = new AddressServiceImpl();
        Address userAddress = addressService.addUserAddress();

        Profile p = new Profile(name, phoneNumber, userAddress.getId());

        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.getMapper(ProfileMapper.class).createProfile(p);
            p.setId(getIDbyProfile(p));
        }

        return p;
    }

    @Override
    public Profile addRecipientProfile() {
        input.nextLine();
        LOGGER.info("Please enter the recipient's name: ");
        String name = input.nextLine();
        LOGGER.info("Please enter the recipient's phone number: ");
        String phoneNumber = input.nextLine();

        AddressService addressService = new AddressServiceImpl();
        Address recipientAddress = addressService.addRecipientAddress();

        Profile p = new Profile(name, phoneNumber, recipientAddress.getId());

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
    public Profile getProfileSelection(ArrayList<Profile> profiles) {
        throw new NotImplementedException();
    }

    @Override
    public Profile selectRecipient(User user) {
        throw new NotImplementedException();
    }
}
