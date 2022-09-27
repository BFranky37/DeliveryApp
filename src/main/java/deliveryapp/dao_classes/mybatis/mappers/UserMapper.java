package deliveryapp.dao_classes.mybatis.mappers;

import deliveryapp.models.people.User;

public interface UserMapper {
    User getUserByID(int id);

    int getIDbyUser(User p);

    int createUser(User p);
}
