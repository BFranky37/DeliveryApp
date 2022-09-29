package deliveryapp.services.jdbc;

import deliveryapp.daoClasses.RouteDAO;
import deliveryapp.daoClasses.java.RouteDAOimpl;
import deliveryapp.models.vehicles.Route;
import deliveryapp.services.RouteService;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class RouteServiceImpl implements RouteService {
    private final RouteDAO routeDAO = new RouteDAOimpl();;
    private static final Logger LOGGER = Logger.getLogger(RouteServiceImpl.class.getName());
    private static final Scanner input = new Scanner(System.in);

    @Override
    public Route getRouteByID(int id) {
        try {
            return routeDAO.getObjectByID(id);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public int getIDbyRoute(Route u) {
        try {
            return routeDAO.getIDbyObject(u);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return -1;
        }
    }

    @Override
    public void createRoute(Route u) {
        try {
            u.setId(routeDAO.create(u));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void updateRoute(Route u) {
        try {
            routeDAO.update(u);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
