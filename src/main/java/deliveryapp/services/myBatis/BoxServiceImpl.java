package deliveryapp.services.myBatis;

import deliveryapp.dao_classes.java.BoxDAOimpl;
import deliveryapp.models.orders.Box;
import deliveryapp.services.BoxService;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class BoxServiceImpl implements BoxService {
    private final BoxDAOimpl boxDAOimpl = new BoxDAOimpl();;
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
            LOGGER.error(e.getMessage());
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
    public void updateBox(Box u) {
        try {
            boxDAOimpl.update(u);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
