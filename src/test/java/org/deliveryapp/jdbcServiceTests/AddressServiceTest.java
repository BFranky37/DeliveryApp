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
    private final Address ADDRESS = new Address(street, city, zipcode);

    @Test
    public void testCreateAddress() {
        LOGGER.info("AddressServiceTest 1");
        int numAddresses = addressService.getNumAddresses();
        addressService.create(ADDRESS);
        Assert.assertEquals(addressService.getNumAddresses(), numAddresses + 1, "Created Address must be added to database");
    }

    @Test
    public void testGetIdByAddress() {
        LOGGER.info("AddressServiceTest 2");
        addressService.create(ADDRESS);
        Assert.assertNotNull(addressService.getIDbyAddress(ADDRESS), "ID should be found for created address");
    }

    @Test
    public void testGetAddressByID() {
        LOGGER.info("AddressServiceTest 3");
        addressService.create(ADDRESS);
        Address duplicate = addressService.getAddressByID(ADDRESS.getId());
        Assert.assertEquals(ADDRESS, duplicate, "Address of the same ID should have the same data");
    }

    @Test
    public void testUpdateAddress() {
        LOGGER.info("AddressServiceTest 4");
        SoftAssert softAssertion= new SoftAssert();
        addressService.create(ADDRESS);
        Address updatedAddress = ADDRESS;
        updatedAddress.setZipcode(zipcode + 1);
        addressService.updateAddress(updatedAddress);
        updatedAddress = addressService.getAddressByID(updatedAddress.getId());
        softAssertion.assertNotEquals(updatedAddress.getZipcode(),zipcode, "Zipcode value should be different after Update");
        softAssertion.assertAll();
    }

    @Test
    public void testDeleteAddress() {
        LOGGER.info("AddressServiceTest 5");
        addressService.create(ADDRESS);
        int numAddresses = addressService.getNumAddresses();
        addressService.delete(ADDRESS.getId());
        Assert.assertEquals(addressService.getNumAddresses(), numAddresses - 1, "Deleted address must be removed from database");
    }

    @AfterMethod(alwaysRun = true)
    public void deleteAddress() {
        addressService.delete(addressService.getIDbyAddress(ADDRESS));
    }
}
