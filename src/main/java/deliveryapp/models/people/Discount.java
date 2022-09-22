package deliveryapp.models.people;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.log4j.Logger;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "discount")
@XmlType(propOrder = { "id", "name", "discountRate" })
@XmlAccessorType(XmlAccessType.FIELD)
public class Discount {
    @XmlTransient
    private static final Logger LOGGER = Logger.getLogger(Discount.class.getName());

    @JsonProperty("id")
    @XmlAttribute
    private int id;
    @JsonProperty("name")
    @XmlElement
    private String name;
    @JsonProperty("rate")
    @XmlElement(name = "rate")
    private double discountRate;

    public Discount (String name, double discountRate) {
        this.name = name;
        this.discountRate = discountRate;
    }

    public Discount() {
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
