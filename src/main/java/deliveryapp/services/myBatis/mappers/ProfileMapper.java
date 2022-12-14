package deliveryapp.services.myBatis.mappers;

import deliveryapp.models.people.Profile;

public interface ProfileMapper {
    Profile getProfileByID(int id);

    int getIDbyProfile(Profile p);

    int createProfile(Profile p);
}
