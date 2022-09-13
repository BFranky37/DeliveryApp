package deliveryapp.models.people;

import org.apache.log4j.Logger;

public class Discount {
    private static final Logger LOGGER = Logger.getLogger(Discount.class.getName());

    private int id;
    private String name;
    private double discountRate;

    Discount (String name, double discountRate) {
        this.name = name;
        this.discountRate = discountRate;
    }

    Discount() {
    }

    public int getId() {
        return id;
    }

    public void setId(int newID) {
        id = newID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double rate) {
        this.discountRate = rate;
    }
}
