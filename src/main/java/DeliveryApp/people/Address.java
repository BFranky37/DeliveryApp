package DeliveryApp.people;

import org.apache.log4j.Logger;

import java.util.Objects;

public class Address {
    private static final Logger LOGGER = Logger.getLogger(Address.class.getName());
    //Members
    private int id;
    private String streetAddress;
    private String city;
    private int zipcode;

    //Constructors
    public Address() {
    }
    public Address(String saddress, String c, int zip) {
        streetAddress = saddress;
        city = c;
        zipcode = zip;
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int newID) {
        id = newID;
    }

    public String getAddress() {
        return streetAddress;
    }

    public void setAddress(String address) {
        streetAddress = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String newCity) {
        city = newCity;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zip) {
        zipcode = zip;
    }

    //Class Overrides
    @Override
    public String toString() {
        return streetAddress + ", " + city + ", " + zipcode + ", ";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Address)) return false;
        Address address = (Address) obj;

        return (Objects.equals(this.streetAddress, address.streetAddress) &&
                Objects.equals(this.city, address.city) &&
                this.zipcode == address.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetAddress, city, zipcode);
    }
}
