package deliveryapp.services.myBatis.mappers;

import deliveryapp.models.people.Address;
import org.apache.ibatis.annotations.Param;

public interface AddressMapper {
    Address getAddressByID(int id);

    int getIDbyAddress(Address p);

    void createAddress(String street, String city, int zipcode);
}
