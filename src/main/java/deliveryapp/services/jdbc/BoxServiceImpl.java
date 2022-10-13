package deliveryapp.services.jdbc;

import deliveryapp.daoClasses.BoxDAO;
import deliveryapp.daoClasses.java.BoxDAOimpl;
import deliveryapp.models.orders.Box;
import deliveryapp.services.BoxService;
import deliveryapp.utils.exceptions.ExceedsLimitsException;
import deliveryapp.utils.exceptions.NegativeValueException;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class BoxServiceImpl implements BoxService {
    private final BoxDAO boxDAOimpl = new BoxDAOimpl();;
    private static final Logger LOGGER = Logger.getLogger(BoxServiceImpl.class.getName());
    private static final Scanner input = new Scanner(System.in);

    @Override
    public Box getBoxByID(int id) {
        try {
            return boxDAOimpl.getObjectByID(id);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public int getIDbyBox(Box u) {
        try {
            return boxDAOimpl.getIDbyObject(u);
        } catch (SQLException e) {
            return -1;
        }
    }

    @Override
    public void createBox(Box u) {
        try {
            u.setId(boxDAOimpl.create(u));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public Box getBoxInfo() {
        //Getting Box info
        double l = 0, w = 0, h = 0;
        double weight = 0;
        boolean valid = false;
        do {
            try {
                valid = false;
                LOGGER.info("Please enter the length, width, and height of your package in centimeters, one by one: ");
                l = input.nextDouble();
                w = input.nextDouble();
                h = input.nextDouble();
                Box.validateSize(l, w, h);
                valid = true;
            } catch (ExceedsLimitsException e) {
                //e.printStackTrace();
                LOGGER.warn(e.getMessage());
                LOGGER.info("This Box size exceeds our limits. Try using a smaller box or breaking your item up into smaller packages.");
            } catch (NegativeValueException e) {
                //e.printStackTrace();
                LOGGER.warn(e.getMessage());
                LOGGER.info("Please enter a valid size and weight.");
            }
        } while (!valid);

        //Create Box and add it to database if not exists
        Box box = new Box(l, w, h);
        int boxID = getIDbyBox(box);
        if (boxID < 0)
            createBox(box);
        else box.setId(boxID);

        return box;
    }

    @Override
    public void updateBox(Box u) {
        try {
            boxDAOimpl.update(u);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
