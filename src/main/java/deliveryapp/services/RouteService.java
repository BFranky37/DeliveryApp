package deliveryapp.services;

import deliveryapp.models.vehicles.Route;

public interface RouteService {
    public Route getRouteByID(int id);

    public int getIDbyRoute(Route u);

    public void createRoute(Route u);

    public void updateRoute(Route u);
}
