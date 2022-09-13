package deliveryapp.services;

import deliveryapp.models.people.User;

public interface UserService {
    public User getUserByID(int id);

    public int getIDbyUser(User u);

    public void createUser(User u);

    public void updateUser(User u);
}
