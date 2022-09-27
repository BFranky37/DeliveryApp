package deliveryapp.services.myBatis;

import deliveryapp.dao_classes.mybatis.AddressDAOimpl;
import deliveryapp.models.people.Address;
import deliveryapp.services.AddressService;
import deliveryapp.utils.ValidateInput;
import deliveryapp.utils.exceptions.InvalidInputException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class AddressServiceImpl implements AddressService {
    private AddressDAOimpl addressDAOimpl = new AddressDAOimpl();
    private static final Logger LOGGER = Logger.getLogger(AddressServiceImpl.class.getName());
    private static final Scanner input = new Scanner(System.in);

    @Override
    public Address addAddress() {
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

        newAddress.setId(addressDAOimpl.create(newAddress));

        return newAddress;
    }

    @Override
    public Address getAddressByID(int id)  {
        return addressDAOimpl.getObjectByID(id);
    }

    @Override
    public int getIDbyAddress(Address a)  {
        return addressDAOimpl.getIDbyObject(a);
    }
}
