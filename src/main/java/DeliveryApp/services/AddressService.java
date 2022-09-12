package DeliveryApp.services;

import DeliveryApp.DAOclasses.AddressDAO;
import DeliveryApp.exceptions.InvalidInputException;
import DeliveryApp.other.ConnectionPool;
import DeliveryApp.people.Address;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AddressService {
    private AddressDAO addressDAO;
    private static final Logger LOGGER = Logger.getLogger(AddressService.class.getName());
    private static final Scanner input = new Scanner(System.in);

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

        try {
            addressDAO.createAddress(newAddress);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return newAddress;
    }

    public Address getAddressByID(int id)  {
        try {
            return addressDAO.getAddressByID(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getIDbyAddress(Address a)  {
        try {
            return addressDAO.getIDbyAddress(a);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
