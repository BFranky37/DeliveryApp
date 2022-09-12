package DeliveryApp.people;

import DeliveryApp.orders.Shipment;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Objects;

public class User extends Person{
    private static final Logger LOGGER = Logger.getLogger(User.class.getName());
    //Members
    private int id;
    private int profileID;
    private Discount discount;

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

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
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

        return (Objects.equals(this.getName(), per.getName()) &&
                Objects.equals(this.getNumber(), per.getNumber()) &&
                this.getAddress() == per.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getNumber(), this.getAddress());
    }
}
