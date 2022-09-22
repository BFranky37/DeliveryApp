package deliveryapp.models.vehicles;

import org.apache.log4j.Logger;

import java.util.Objects;

public class Route {
    private static final Logger LOGGER = Logger.getLogger(Route.class.getName());
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
        LOGGER.info("Route created.");

        //calculatePrice();
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
        //calculatePrice();
    }

    public int getToLocation() {
        return toLocationID;
    }

    public void setToLocation(int to) {
        toLocationID = to;
        //calculatePrice();
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


    //Class Overrides
    @Override
    public String toString() {
        return ("From " + fromLocationID + " to " + toLocationID);
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
