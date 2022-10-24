package org.deliveryapp.jdbcServiceTests;

import deliveryapp.models.people.Address;
import deliveryapp.services.AddressService;
import deliveryapp.services.jdbc.AddressServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class AddressServiceTest {
    private static Logger LOGGER = LogManager.getLogger(AddressServiceTest.class.getName());
    private final AddressService addressService = new AddressServiceImpl();

    private final String street = "123 Street st";
    private final String city = "Cityville";
    private final int zipcode = 1;
    private final Address address = new Address(street, city, zipcode);

    @Test
    public void testCreateAddress() {
        LOGGER.info("AddressServiceTest 1");
        int numAddresses = addressService.getNumAddresses();
        addressService.create(address);
        Assert.assertEquals(addressService.getNumAddresses(), numAddresses + 1, "Created Address must be added to database");
    }

    @Test
    public void testGetIdByAddress() {
        LOGGER.info("AddressServiceTest 2");
        addressService.create(address);
        Assert.assertNotNull(addressService.getIDbyAddress(address), "ID should be found for created address");
    }

    @Test
    public void testGetAddressByID() {
        LOGGER.info("AddressServiceTest 3");
        addressService.create(address);
        Address duplicate = addressService.getAddressByID(address.getId());
        Assert.assertEquals(address, duplicate, "Address of the same ID should have the same data");
    }

    @Test
    public void testUpdateAddress() {
        LOGGER.info("AddressServiceTest 4");
        SoftAssert softAssertion= new SoftAssert();
        addressService.create(address);
        Address updatedAddress = address;
        updatedAddress.setZipcode(zipcode + 1);
        addressService.updateAddress(updatedAddress);
        updatedAddress = addressService.getAddressByID(updatedAddress.getId());
        softAssertion.assertNotEquals(updatedAddress.getZipcode(),zipcode, "Zipcode value should be different after Update");
        softAssertion.assertAll();
    }

    @Test
    public void testDeleteAddress() {
        LOGGER.info("AddressServiceTest 5");
        addressService.create(address);
        int numAddresses = addressService.getNumAddresses();
        addressService.delete(address.getId());
        Assert.assertEquals(addressService.getNumAddresses(), numAddresses - 1, "Deleted address must be removed from database");
    }

    @AfterMethod(alwaysRun = true)
    public void deleteAddress() {
        addressService.delete(addressService.getIDbyAddress(address));
    }
}
