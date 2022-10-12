package deliveryapp.models.orders;

import deliveryapp.models.people.Discount;
import deliveryapp.models.vehicles.Route;
import deliveryapp.models.vehicles.Vehicle;
import deliveryapp.models.vehicles.VehicleType;
import deliveryapp.services.*;
import deliveryapp.services.jdbc.*;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class Shipment {
    private static final Logger LOGGER = Logger.getLogger(Shipment.class.getName());
    RouteService routeService = new RouteServiceImpl();
    PackageService packageService = new PackageServiceImpl();
    BoxService boxService = new BoxServiceImpl();
    VehicleService vehicleService = new VehicleServiceImpl();
    VehicleTypeService vehicleTypeService = new VehicleTypeServiceImpl();
    InsuranceService insuranceService = new InsuranceServiceImpl();
    DiscountService discountService = new DiscountServiceImpl();

    //MEMBERS
    private int id;
    private int senderID;
    private int recipientID;
    private int packageID;
    private int insuranceID;
    private int routeID;
    private int vehicleID;

    private boolean priority;
    private double totalPrice;

    //Constructor
    public Shipment(int send, int receive, int pack, int plan, boolean prio) {
        senderID = send;
        recipientID = receive;
        packageID = pack;
        insuranceID = plan;
        priority = prio;

        setRoute();
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int newID) {
        id = newID;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int send) {
        senderID = send;
        //LOGGER.info("As the sender for this shipment has been changed, we must re-verify the shipping method and update the price.");
        //setRoute();
    }

    public int getRecipientID() {
        return recipientID;
    }

    public void setRecipientID(int receive) {
        recipientID = receive;
        //LOGGER.info("As the recipient for this shipment has been changed, we must re-verify the shipping method and update the price.");
        //setRoute();
    }

    public int getPackageID() {
        return packageID;
    }

    public void setPackageID(int pack) {
        packageID = pack;
        //LOGGER.info("As the package for this shipment has been changed, we must re-verify the shipping method and update the price.");
        //determineShippingPlan();
    }

    public int getInsuranceID() {
        return insuranceID;
    }

    public void setInsuranceID(int plan) {
        insuranceID = plan;
        //calculatePrice();
    }

    public int getRouteID() {
        return routeID;
    }

    public void setRouteID(int newRoute) {
        routeID = newRoute;
        //determineShippingPlan();
    }

    public void setRoute() {
        AddressService addressService = new AddressServiceImpl();
        Route route = new Route(addressService.getAddressByUserID(senderID).getId(), addressService.getAddressByProfileID(recipientID).getId());
        routeService.createRoute(route);
        routeID = route.getId();
        //determineShippingPlan();
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int newVehicle) {
        vehicleID = newVehicle;
    }

    public boolean getPrio() {
        return priority;
    }

    public void setPrio(boolean prio) {
        priority = prio;
        //determineShippingPlan();
    }

    public double getPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double price) {
        totalPrice = price;
    }

    public void calculatePrice() {
        Route travelRoute = routeService.getRouteByID(routeID);
        Package shippingPackage = packageService.getPackageByID(packageID);
        VehicleType vehicleType = vehicleTypeService.getVehicleTypeByVehicleID(vehicleID);
        Insurance insurance = insuranceService.getInsuranceByID(insuranceID);
        totalPrice = Double.parseDouble(String.format("%.2f", (shippingPackage.getPrice() + travelRoute.getPrice() + vehicleType.getRate()
                + insurance.calculatePrice(shippingPackage.getValue()))));
        //Apply discount
        List<Discount> discounts = discountService.getDiscountsByUser(senderID);
        for (Discount d : discounts) {
            totalPrice -= totalPrice * d.getDiscountRate();
        }
    }

    public void determineShippingPlan() {
        //This is the function that will prompt the user for the desired priority of the delivery,
        //then it will choose a vehicle based on factors such as distance, priority and size of package
        // it will either be vehicle = new Truck() or vehicle = new Plane(), and then fill in the remaining fields with a vehicle preset
        int vehicleNumber = (int)Math.floor(Math.random()*(999999-100000+1)+100000);
        Route travelRoute = routeService.getRouteByID(routeID);
        Package shippingPackage = packageService.getPackageByID(packageID);
        Box box = boxService.getBoxByID(shippingPackage.getBoxID());

        if (!priority && travelRoute.getDistance() < 1000 && shippingPackage.getWeight() <= 150 && box.getArea() <= 65000) {
            Vehicle vehicle = new Vehicle(vehicleTypeService.getVehicleTypeByName("Standard Delivery Car").getId(), "00-" + vehicleNumber);
            vehicleService.createVehicle(vehicle);
            vehicleID = vehicle.getId();
        }
        else if (!priority && travelRoute.getDistance() < 1000 && shippingPackage.getWeight() <= 300 && box.getArea() <= 90000) {
            Vehicle vehicle = new Vehicle(vehicleTypeService.getVehicleTypeByName("Heavy Delivery Truck").getId(), "00-" + vehicleNumber);
            vehicleService.createVehicle(vehicle);
            vehicleID = vehicle.getId();
        }
        else if (priority && travelRoute.getDistance() < 1000 && shippingPackage.getWeight() <= 150 && box.getArea() <= 65000) {
            Vehicle vehicle = new Vehicle(vehicleTypeService.getVehicleTypeByName("Priority Delivery Car").getId(), "00-" + vehicleNumber);
            vehicleService.createVehicle(vehicle);
            vehicleID = vehicle.getId();
        }
        else if (!priority && shippingPackage.getWeight() <= 1000 && box.getArea() <= 500000.0) {
            Vehicle vehicle = new Vehicle(vehicleTypeService.getVehicleTypeByName("Delivery Freight Truck").getId(), "00-" + vehicleNumber);
            vehicleService.createVehicle(vehicle);
            vehicleID = vehicle.getId();
        }

        else if (shippingPackage.getWeight() <= 300 && box.getArea() <= 90000) {
            Vehicle vehicle = new Vehicle(vehicleTypeService.getVehicleTypeByName("Standard Delivery Plane").getId(), "00-" + vehicleNumber);
            vehicleService.createVehicle(vehicle);
            vehicleID = vehicle.getId();
        }
        else {
            Vehicle vehicle = new Vehicle(vehicleTypeService.getVehicleTypeByName("Heavy Cargo Plane").getId(), "00-" + vehicleNumber);
            vehicleService.createVehicle(vehicle);
            vehicleID = vehicle.getId();
        }

        calculatePrice();
    }

    //Class Overrides
    @Override
    public String toString() {
        return ("Sender: " + senderID + "\nRecipient: " + recipientID +
                "\nRoute: " + routeID +
                "\nShipping method: " + vehicleID + "\nTotal Price: " + Math.round(totalPrice * 100.0) / 100.0);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Shipment)) return false;
        Shipment ship = (Shipment) obj;

        return (this.senderID == ship.senderID &&
                this.recipientID == ship.recipientID &&
                this.packageID == ship.packageID &&
                this.insuranceID == ship.insuranceID &&
                this.vehicleID == ship.vehicleID &&
                this.totalPrice == ship.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(senderID, recipientID, packageID, insuranceID, vehicleID, totalPrice);
    }
}
