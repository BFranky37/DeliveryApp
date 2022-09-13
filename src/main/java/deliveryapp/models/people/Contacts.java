package deliveryapp.models.people;

import org.apache.log4j.Logger;

import java.sql.Date;

public class Contacts {
    private static final Logger LOGGER = Logger.getLogger(Contacts.class.getName());
    //Members
    int userID;
    int profileID;
    String relationship;
    Date dateAdded;

    public Contacts() {

    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getProfileID() {
        return profileID;
    }

    public void setProfileID(int profileID) {
        this.profileID = profileID;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}
