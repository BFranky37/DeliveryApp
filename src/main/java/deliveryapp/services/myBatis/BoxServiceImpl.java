package deliveryapp.services.myBatis;

import deliveryapp.dao_classes.mybatis.BoxDAOimpl;
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
        return boxDAOimpl.getObjectByID(id);
    }

    @Override
    public int getIDbyBox(Box u) {
        return boxDAOimpl.getIDbyObject(u);
    }

    @Override
    public void createBox(Box u) {
        u.setId(boxDAOimpl.create(u));
    }

    @Override
    public void updateBox(Box u) {
        boxDAOimpl.update(u);
    }
}
