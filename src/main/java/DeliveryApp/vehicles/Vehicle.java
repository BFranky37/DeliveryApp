package DeliveryApp.vehicles;

import java.util.Objects;

public class Vehicle {
    //Members
    private int id;
    private int vehicleTypeID;
    private String vehicleNumber;

    //Constructors
    public Vehicle() {
    }
    public Vehicle(String num, int vehicleTypeID) {
        this.vehicleTypeID = vehicleTypeID;
        vehicleNumber = num;
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int newID) {
        id = newID;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String newNum) {
        vehicleNumber = newNum;
    }

    public int getVehicleTypeID() {
        return vehicleTypeID;
    }

    public void setVehicleTypeID(int vehicleTypeID) {
        this.vehicleTypeID = vehicleTypeID;
    }

    //Class Overrides
    @Override
    public String toString() {
        return ("Vehicle number: " + vehicleNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vehicle)) return false;
        Vehicle car = (Vehicle) obj;

        return (this.vehicleTypeID == car.vehicleTypeID &&
                Objects.equals(this.vehicleNumber, car.vehicleNumber));
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleTypeID, vehicleNumber);
    }
}
