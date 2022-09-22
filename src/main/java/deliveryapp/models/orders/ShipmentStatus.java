package deliveryapp.models.orders;

import org.apache.log4j.Logger;

import java.sql.Date;

public class ShipmentStatus {
    private static final Logger LOGGER = Logger.getLogger(ShipmentStatus.class.getName());
    //MEMBERS
    int id;
    int shipmentID;
    boolean delivered;
    Date dateDeparted;
    Date dateArrived;

    public ShipmentStatus(Shipment shipment) {
        shipmentID = shipment.getId();
        delivered = false;
        java.util.Date utilDate = new java.util.Date();
        dateDeparted = new java.sql.Date(utilDate.getTime());;
    }

    public ShipmentStatus() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShipmentID() {
        return shipmentID;
    }

    public void setShipmentID(int shipmentID) {
        this.shipmentID = shipmentID;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public Date getDateDeparted() {
        return dateDeparted;
    }

    public void setDateDeparted(Date dateDeparted) {
        this.dateDeparted = dateDeparted;
    }

    public Date getDateArrived() {
        return dateArrived;
    }

    public void setDateArrived(Date dateArrived) {
        this.dateArrived = dateArrived;
    }
}
