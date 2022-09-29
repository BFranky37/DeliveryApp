package deliveryapp.services.myBatis;

import deliveryapp.services.myBatis.mappers.AddressMapper;
import deliveryapp.models.people.Address;
import deliveryapp.services.AddressService;
import deliveryapp.utils.ValidateInput;
import deliveryapp.utils.exceptions.InvalidInputException;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class AddressServiceImpl implements AddressService {
    private final SqlSessionFactory sqlSessionFactory = MyBatisFactory.getSqlSessionFactory();
    private static final Logger LOGGER = Logger.getLogger(AddressServiceImpl.class.getName());
    private static final Scanner input = new Scanner(System.in);

    @Override
    public Address addUserAddress() {
        boolean valid = false;
        LOGGER.info("Please enter your street address: ");
        String address = input.nextLine();
        LOGGER.info("Please enter your city: ");
        String city = input.nextLine();
        StringUtils.capitalize(city);
        LOGGER.info("Please enter the your zipcode or postal code: ");
        int zipcode = 0;
        do {
            try {
                zipcode = ValidateInput.validateZip(input.nextInt());
                valid = true;
            } catch (InvalidInputException e) {
                LOGGER.warn(e.getMessage() + "Invalid zipcode input");
                LOGGER.info("Please enter a valid 6-digit zipcode:");
            }
        } while (!valid);

        Address newAddress = new Address(address, city, zipcode);

        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.getMapper(AddressMapper.class).createAddress(address, city, zipcode);
            newAddress.setId(getIDbyAddress(newAddress));
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }

        return newAddress;
    }

    public Address addRecipientAddress() {
        boolean valid = false;
        LOGGER.info("Please enter the recipient's street address: ");
        String address = input.nextLine();
        LOGGER.info("Please enter the recipient's city: ");
        String city = input.nextLine();
        city = StringUtils.capitalize(city);
        LOGGER.info("Please enter the recipient's zipcode or postal code: ");
        int zipcode = 0;
        do {
            try {
                valid = false;
                zipcode = ValidateInput.validateZip(input.nextInt());
                valid = true;
            } catch (InvalidInputException e) {
                LOGGER.warn(e.getMessage() + "Invalid zipcode input");
                LOGGER.info("Please enter a valid 6-digit zipcode:");
            }
        } while (!valid);
        input.nextLine();

        Address newAddress = new Address(address, city, zipcode);

        try (SqlSession session = sqlSessionFactory.openSession()){
            int addressID = session.getMapper(AddressMapper.class).getIDbyAddress(newAddress);
            if (addressID < 0)
                session.getMapper(AddressMapper.class).createAddress(address, city, zipcode);
            else newAddress.setId(addressID);
        }

        return newAddress;
    }

    @Override
    public Address getAddressByID(int id)  {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.getMapper(AddressMapper.class).getAddressByID(id);
        }
    }

    @Override
    public int getIDbyAddress(Address a)  {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.getMapper(AddressMapper.class).getIDbyAddress(a);
        }
    }
}
