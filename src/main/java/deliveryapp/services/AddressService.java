package deliveryapp.services;

import deliveryapp.models.people.Address;

import java.util.List;

public interface AddressService {
    public Address addUserAddress();

    public Address addRecipientAddress();

    public Address getAddressByID(int id);

    public Address getAddressByUserID(int id);

    public Address getAddressByProfileID(int id);

    public int getIDbyAddress(Address a);

    public Address create(Address newAddress);
    public void delete(int id);

    public void updateAddress(Address a);

    public int getNumAddresses();
}
