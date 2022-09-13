package deliveryapp.services;

import deliveryapp.models.people.Address;
import deliveryapp.utils.ValidateInput;
import deliveryapp.utils.exceptions.InvalidInputException;
import org.apache.commons.lang3.StringUtils;

import java.sql.SQLException;

public interface AddressService {
    public Address addAddress();

    public Address getAddressByID(int id);

    public int getIDbyAddress(Address a);
}
