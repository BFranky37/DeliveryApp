package deliveryapp.utils.threads;

import deliveryapp.models.orders.Shipment;
import deliveryapp.models.orders.ShipmentStatus;
import deliveryapp.models.people.Profile;
import deliveryapp.services.ProfileService;
import deliveryapp.services.ShipmentStatusService;
import deliveryapp.services.jdbc.ProfileServiceImpl;
import deliveryapp.services.jdbc.ShipmentStatusServiceImpl;
import org.apache.log4j.Logger;

public class DropOff implements Runnable{
    private static final Logger LOGGER = Logger.getLogger(Travel.class.getName());
    private final ShipmentStatusService shipmentStatusService = new ShipmentStatusServiceImpl();
    private final ProfileService profileService = new ProfileServiceImpl();

    Shipment shipment;
    final Object lock1;
    final Object lock2;

    public DropOff(Shipment shipment, Object Lock1, Object Lock2) {
        this.shipment = shipment;
        lock1 = Lock1;
        lock2 = Lock2;
    }

    @Override
    public void run() {
        synchronized (lock1) { //lock will be held until shipment has been transported to the recipient
            //LOGGER.info("Holding delivery lock2");
            ShipmentStatus shipmentStatus = shipmentStatusService.getShipmentStatusByShipment(shipment.getId());
            Profile recipient = profileService.getProfileByID(shipment.getRecipientID());
            //synchronized (lock1) { //Will create a Deadlock
            //    LOGGER.info("Holding delivery lock1");
            shipmentStatus.setDelivered(true);
            java.util.Date utilDate = new java.util.Date();
            shipmentStatus.setDateArrived(new java.sql.Date(utilDate.getTime()));
            shipmentStatusService.updateShipmentStatus(shipmentStatus);
            LOGGER.info("Recipient " + recipient.getName() + " has received their package.");
            //}
        }
    }
}
