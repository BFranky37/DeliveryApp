package deliveryapp.services;

import deliveryapp.models.people.Profile;
import deliveryapp.models.people.User;
import deliveryapp.utils.exceptions.InvalidInputException;

import java.util.ArrayList;

public interface ProfileService {
    public Profile addUserProfile();

    public Profile addRecipientProfile();

    public Profile getProfileByID(int id);

    public int getIDbyProfile(Profile p);

    public Profile getProfileSelection(ArrayList<Profile> profiles) throws InvalidInputException;

    public Profile selectRecipient(User user);
}
