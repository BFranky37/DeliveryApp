package deliveryapp.services;

import deliveryapp.models.people.Profile;

public interface ProfileService {
    public Profile addUserProfile(int addressID);

    public Profile getProfileByID(int id);

    public int getIDbyProfile(Profile p);

    public void addRecipientProfile(int addressID);
}
