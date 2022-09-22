package deliveryapp.services;

import deliveryapp.models.orders.Box;

public interface BoxService {
    public Box getBoxByID(int id);

    public int getIDbyBox(Box u);

    public void createBox(Box u);

    public void updateBox(Box u);
}
