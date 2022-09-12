package DeliveryApp.people;

import org.apache.log4j.Logger;

import java.util.Objects;

public class User {
    private static final Logger LOGGER = Logger.getLogger(User.class.getName());
    //Members
    private int id;
    private int profileID;

    //Constructors
    public User(int profileID) {
        this.profileID = profileID;
        //discount = Discount.NO_DISCOUNT;
    }

    public User() {
    }

    //Getters and setters
    public int getId() {
        return id;
    }

    public int getProfileID() {
        return profileID;
    }

    public void setProfileID(int profileID) {
        this.profileID = profileID;
    }

    //Class Overrides
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;
        User per = (User) obj;

        return (Objects.equals(this.id, per.id) &&
                Objects.equals(this.profileID, per.profileID));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, profileID);
    }
}
