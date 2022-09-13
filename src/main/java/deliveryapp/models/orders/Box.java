package deliveryapp.models.orders;

import deliveryapp.utils.SizeMeasurement;
import org.apache.log4j.Logger;

import java.util.Objects;

public class Box {
    private static final Logger LOGGER = Logger.getLogger(Box.class.getName());

    private int id;
    //Dimensions measured in inches
    private double length;
    private double width;
    private double height;
    private static final double sizeLimit = 500000;

    //Constructors
    public Box() {
    }
    public Box(double l, double w, double h) {
        length = Math.round(SizeMeasurement.CENTIMETERS.convert(l) * 100.0) / 100.0;
        width = Math.round(SizeMeasurement.CENTIMETERS.convert(w) * 100.0) / 100.0;
        height = Math.round(SizeMeasurement.CENTIMETERS.convert(h) * 100.0) / 100.0;
    }

    public int getId() {
        return id;
    }

    public void setId(int newID) {
        id = newID;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double l) {
        length = l;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double w) {
        width = w;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double h) {
        height = h;
    }

    //Class Overrides
    @Override
    public String toString() {
        return "Length: " + length + " inches, Width: " + width + " inches,  Height: " + height + " inches";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Box)) return false;
        Box per = (Box) obj;

        return (this.length == per.length &&
                this.width == per.width &&
                this.height == per.height);
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, width, height);
    }
}
