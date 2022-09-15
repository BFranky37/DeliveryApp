package deliveryapp.models.orders;

import org.apache.log4j.Logger;

public class Insurance {
    private static final Logger LOGGER = Logger.getLogger(Insurance.class.getName());

    //MEMBERS
    int id;
    private String name;
    private double baseCost; //base price of insurance
    private double priceRate; //extra cost added on based on value of the object being shipped (Usually between 1.5 - 5%)

    //Constructors
    public Insurance() {
    }
    public Insurance(String nam, double cost, double rate) {
        name = nam;
        baseCost = cost;
        priceRate = rate;
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

    public void setName(String nam) {
        name = nam;
    }

    public double getCost() {
        return baseCost;
    }

    public void setCost(double cos) {
        baseCost = cos;
    }

    public double getRate() {
        return priceRate;
    }
    public void setRate(double rate) {
        priceRate = rate;
    }

    //Class Overrides
    @Override
    public String toString() {
        return ("Name: " + name + ", Base Cost: " + baseCost + ", Price Rate: " + (priceRate * 100) + "%");
    }
}
