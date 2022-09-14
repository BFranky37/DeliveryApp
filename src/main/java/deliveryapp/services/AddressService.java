package deliveryapp.services;

import deliveryapp.models.people.Address;

public interface AddressService {
    public Address addAddress();

    public Address getAddressByID(int id);

    public int getIDbyAddress(Address a);
}
