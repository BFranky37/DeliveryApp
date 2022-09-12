package DeliveryApp.orders;

import org.apache.log4j.Logger;
import java.util.Objects;

public class Shipment {
    private static final Logger LOGGER = Logger.getLogger(Shipment.class.getName());
    //MEMBERS
    private int id;
    private int senderID;
    private int recipientID;
    private int packageID;
    private int insuranceID;
    private int routeID;
    private int vehicleID;

    private boolean priority;
    private double totalPrice;

    //Constructor
    public Shipment(int send, int receive, int pack, int plan, boolean prio) {
        senderID = send;
        recipientID = receive;
        packageID = pack;
        insuranceID = plan;
        priority = prio;

        //routeID = new Route(send.getAddress(), receive.getAddress());
        //determineShippingPlan();
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int send) {
        senderID = send;
        //LOGGER.info("As the sender for this shipment has been changed, we must re-verify the shipping method and update the price.");
        //setRoute();
    }

    public int getRecipientID() {
        return recipientID;
    }

    public void setRecipientID(int receive) {
        recipientID = receive;
        //LOGGER.info("As the recipient for this shipment has been changed, we must re-verify the shipping method and update the price.");
        //setRoute();
    }

    public int getPackageID() {
        return packageID;
    }

    public void setPackageID(int pack) {
        packageID = pack;
        //LOGGER.info("As the package for this shipment has been changed, we must re-verify the shipping method and update the price.");
        //determineShippingPlan();
    }

    public int getInsuranceID() {
        return insuranceID;
    }

    public void setInsuranceID(int plan) {
        insuranceID = plan;
        //calculatePrice();
    }

    public int getRouteID() {
        return routeID;
    }

    public void setRoute(int newRoute) {
        routeID = newRoute;
        //determineShippingPlan();
    }

    public int getVehicleID() {
        return vehicleID;
    }

    private void setVehicleID(int newVehicle) {
        vehicleID = newVehicle;
    }

    public boolean getPrio() {
        return priority;
    }

    public void setPrio(boolean prio) {
        priority = prio;
        //determineShippingPlan();
    }

    public double getPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double price) {
        totalPrice = price;
    }

    //Class Overrides
    @Override
    public String toString() {
        return ("Sender: " + senderID + "\nRecipient: " + recipientID +
                "\nRoute: " + routeID +
                "\nShipping method: " + vehicleID + "\nTotal Price: " + Math.round(totalPrice * 100.0) / 100.0);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Shipment)) return false;
        Shipment ship = (Shipment) obj;

        return (this.senderID == ship.senderID &&
                this.recipientID == ship.recipientID &&
                this.packageID == ship.packageID &&
                this.insuranceID == ship.insuranceID &&
                this.vehicleID == ship.vehicleID &&
                this.totalPrice == ship.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(senderID, recipientID, packageID, insuranceID, vehicleID, totalPrice);
    }
}
