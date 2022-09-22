package deliveryapp.services.jdbc;

import deliveryapp.dao_classes.BoxDAO;
import deliveryapp.models.orders.Box;
import deliveryapp.services.BoxService;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class BoxServiceImpl implements BoxService {
    private final BoxDAO boxDAO = new BoxDAO();;
    private static final Logger LOGGER = Logger.getLogger(BoxServiceImpl.class.getName());
    private static final Scanner input = new Scanner(System.in);

    @Override
    public Box getBoxByID(int id) {
        try {
            return boxDAO.getObjectByID(id);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public int getIDbyBox(Box u) {
        try {
            return boxDAO.getIDbyObject(u);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    @Override
    public void createBox(Box u) {
        try {
            u.setId(boxDAO.create(u));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void updateBox(Box u) {
        try {
            boxDAO.update(u);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
