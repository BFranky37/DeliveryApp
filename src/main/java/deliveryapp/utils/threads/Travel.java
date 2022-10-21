package deliveryapp.utils.threads;

import deliveryapp.models.orders.Shipment;
import deliveryapp.models.orders.ShipmentStatus;
import deliveryapp.models.vehicles.Route;
import deliveryapp.models.vehicles.Vehicle;
import deliveryapp.services.RouteService;
import deliveryapp.services.ShipmentStatusService;
import deliveryapp.services.VehicleService;
import deliveryapp.services.jdbc.RouteServiceImpl;
import deliveryapp.services.jdbc.ShipmentStatusServiceImpl;
import deliveryapp.services.jdbc.VehicleServiceImpl;
import org.apache.log4j.Logger;

public class Travel implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(Travel.class.getName());
    private final RouteService routeService = new RouteServiceImpl();
    private final VehicleService vehicleService = new VehicleServiceImpl();

    Shipment shipment;
    final Object lock1;
    final Object lock2;

    public Travel(Shipment shipment, Object Lock1, Object Lock2) {
        this.shipment = shipment;
        lock1 = Lock1;
        lock2 = Lock2;
    }

    @Override
    public void run() {
        synchronized (lock1) {
            LOGGER.info("Holding delivery lock1");
            try {
                Route route = routeService.getRouteByID(shipment.getRouteID());
                Vehicle vehicle = vehicleService.getVehicleByID(shipment.getVehicleID());
                //Delivery vehicle traveling the distance of the route
                Thread.sleep(1000L * route.getDistance());
                //synchronized (lock2) { //Creates a Deadlock
                //    LOGGER.info("Holding delivery lock2");
                LOGGER.info("Vehicle " + vehicle.getVehicleNumber() + " has completed delivery of your shipment");
                //}
            } catch (InterruptedException e) {
                LOGGER.info("Delivery Vehicle interrupted while on route.");
            }
        }
    }
}
