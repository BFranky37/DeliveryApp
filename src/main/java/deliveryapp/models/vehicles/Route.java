package deliveryapp.models.vehicles;

import deliveryapp.models.people.Address;
import deliveryapp.services.AddressService;
import deliveryapp.services.jdbc.AddressServiceImpl;
import deliveryapp.utils.functionalInterfaces.IReturnOperation;
import org.apache.log4j.Logger;

import java.util.Objects;

public class Route {
    private static final Logger LOGGER = Logger.getLogger(Route.class.getName());
    AddressService addressService = new AddressServiceImpl();
    //Members
    private int id;
    private int distance;
    private double price;
    private int fromLocationID;
    private int toLocationID;
    private static final double basePrice = .10;

    //Constructors
    public Route() {

    }
    public Route(int from, int to) {
        fromLocationID = from;
        toLocationID = to;

        calculatePrice();
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int newID) {
        id = newID;
    }

    public int getFromLocation() {
        return fromLocationID;
    }

    public void setFromLocation(int from) {
        fromLocationID = from;
        calculatePrice();
    }

    public int getToLocation() {
        return toLocationID;
    }

    public void setToLocation(int to) {
        toLocationID = to;
        calculatePrice();
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int dis) {
        distance = dis;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void calculatePrice() { //Generally speaking, the bigger the difference in Zip Code, the further away the post offices are
        Address fromLocation = addressService.getAddressByID(fromLocationID);
        Address toLocation = addressService.getAddressByID(toLocationID);
        IReturnOperation<Integer> getDistance = (from, to) -> from - to;
        distance = Math.abs(getDistance.returnResult(fromLocation.getZipcode(), toLocation.getZipcode()));
        if (distance < 100) {
            price = (Math.round(distance * 100.0) / 100000.0) + basePrice;
        }
        else {
            price = (Math.round(distance * 100.0) / 2000000.0) + basePrice;
        }
    }


    //Class Overrides
    @Override
    public String toString() {
        Address fromLocation = addressService.getAddressByID(fromLocationID);
        Address toLocation = addressService.getAddressByID(toLocationID);
        return ("From " + fromLocation + " to " + toLocation);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Route)) return false;
        Route route = (Route) obj;

        return (this.fromLocationID == route.fromLocationID &&
                this.toLocationID == route.toLocationID &&
                this.price == route.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromLocationID, toLocationID, price);
    }
}
