package deliveryapp.dao_classes.mybatis.mappers;

import deliveryapp.models.people.Address;

public interface AddressMapper {
    Address getAddressByID(int id);

    int getIDbyAddress(Address p);

    int createAddress(Address p);
}
