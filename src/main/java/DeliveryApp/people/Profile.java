package DeliveryApp.people;

public class Profile {
    //Members
    private int id;
    private String name;
    private String phoneNumber;
    private int addressID;
    private int addedByUserID;

    public Profile() {

    }

    public Profile(String fullname, String phoneNum, int fulladdress, int addedbyID) {
        name = fullname;
        phoneNumber = phoneNum;
        addressID = fulladdress;
        addedByUserID = addedbyID;
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String fullname) {
        name = fullname;
    }

    public String getNumber() {
        return phoneNumber;
    }

    public void setNumber(String num) {
        phoneNumber = num;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int newAddress) {
        addressID = newAddress;
    }

    public int getAddedByUserID() {
        return addedByUserID;
    }

    public void setAddedByUserID(int addedByUserID) {
        this.addedByUserID = addedByUserID;
    }

    //Class Overrides
    @Override
    public String toString() {
        return ("Name: " + name + ", Phone Number: " + phoneNumber);
    }

}
