package deliveryapp.models.people;

import org.apache.log4j.Logger;

import java.util.Date;

public class UserStatus {
    private static final Logger LOGGER = Logger.getLogger(UserStatus.class.getName());
    //Members
    int userID;
    int discountID;
    Date dateAcquired;

    public UserStatus() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getDiscountID() {
        return discountID;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }

    public Date getDateAcquired() {
        return dateAcquired;
    }

    public void setDateAcquired(Date dateAcquired) {
        this.dateAcquired = dateAcquired;
    }
}
