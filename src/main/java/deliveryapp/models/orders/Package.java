package deliveryapp.models.orders;

import deliveryapp.utils.WeightMeasurement;
import org.apache.log4j.Logger;

import java.util.Objects;

public class Package {
    private static final Logger LOGGER = Logger.getLogger(Package.class.getName());
    //Members
    private int id;
    private int boxID;
    private double weight;
    private boolean fragility;
    private double value;
    private double cost;
    final static double costRate = 2.3;
    public static final double weightLimit = 3000;

    //Constructors
    public Package() {
    }
    public Package(int bx, double wgt, double val, boolean fragile) {
        boxID = bx;
        weight = WeightMeasurement.KILOGRAMS.convert(wgt); //weight is initially given in Kilos. Convert to pounds
        value = val;
        fragility = fragile;
        //calculatePrice();
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int newID) {
        id = newID;
    }

    public int getBoxID() {
        return boxID;
    }

    public void setBoxID(int bx) {
        boxID = bx;
        //calculatePrice();
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double wgt) {
        weight = wgt;
    }

    public boolean getFragility() {
        return fragility;
    }

    public void setFragility(boolean fragile) {
        fragility = fragile;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double val) {
        value = val;
    }

    public double getPrice() {
        return cost;
    }

    public void setPrice(double price) {
        cost = price;
    }

    //Class Overrides
    @Override
    public String toString() {
        return ("\nWeight: " + weight + " pounds, Fragile: " + fragility);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Package)) return false;
        Package pkg = (Package) obj;

        return (this.boxID == pkg.boxID && this.weight == pkg.weight && this.fragility == pkg.fragility);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boxID, weight, fragility);
    }
}
