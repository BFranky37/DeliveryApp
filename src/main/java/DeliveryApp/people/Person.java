package DeliveryApp.people;

public class Person {
    //Members
    private int id;
    private String name;
    private String phoneNumber;
    private int addressID;

    public Person() {

    }

    public Person(String fullname, String phoneNum, int fulladdress) {
        name = fullname;
        phoneNumber = phoneNum;
        addressID = fulladdress;
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

    public int getAddress() {
        return addressID;
    }

    public void setAddress(int newAddress) {
        addressID = newAddress;
    }

    //Class Overrides
    @Override
    public String toString() {
        return ("Name: " + name + ", Phone Number: " + phoneNumber);
    }
}
