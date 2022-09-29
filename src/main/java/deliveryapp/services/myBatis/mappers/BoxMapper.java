package deliveryapp.services.myBatis.mappers;

import deliveryapp.models.orders.Box;

public interface BoxMapper {
    Box getBoxByID(int id);
}
