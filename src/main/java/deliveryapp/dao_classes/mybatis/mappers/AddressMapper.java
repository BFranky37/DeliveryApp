package deliveryapp.dao_classes.mybatis.mappers;

import deliveryapp.models.people.Address;
import org.apache.ibatis.annotations.Param;

public interface AddressMapper {
    Address getAddressByID(int id);

    int getIDbyAddress(Address p);

    int createAddress(@Param("street")String street, @Param("city")String city, @Param("zipcode")int zipcode);
}
