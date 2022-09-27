package deliveryapp.dao_classes.mybatis.mappers;

import deliveryapp.models.orders.Box;

public interface BoxMapper {
    Box getBoxByID(int id);
}
