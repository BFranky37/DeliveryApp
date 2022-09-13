package deliveryapp.models.people;

public class Profile {
    //Members
    private int id;
    private String name;
    private String phoneNumber;
    private int addressID;

    public Profile() {
    }

    public Profile(String fullname, String phoneNum, int fulladdress) {
        name = fullname;
        phoneNumber = phoneNum;
        addressID = fulladdress;
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int newID) {
        id = newID;
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

    //Class Overrides
    @Override
    public String toString() {
        return ("Name: " + name + ", Phone Number: " + phoneNumber);
    }

}
