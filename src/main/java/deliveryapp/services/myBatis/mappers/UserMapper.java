package deliveryapp.services.myBatis.mappers;

import deliveryapp.models.people.User;

public interface UserMapper {
    User getUserByID(int id);

    int getIDbyUser(User p);

    int createUser(User p);
}
