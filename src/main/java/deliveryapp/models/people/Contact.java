package deliveryapp.models.people;

import java.sql.Date;

public class Contact {
    int userID;
    int profileID;
    String relationship;
    Date dateAdded;

    public Contact(int user, int profile, String relation){
        userID = user;
        profileID = profile;
        relationship = relation;
        dateAdded = new java.sql.Date(new java.util.Date().getTime());
    }

    public Contact() {}

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
