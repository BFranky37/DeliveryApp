package deliveryapp.services;

import deliveryapp.dao_classes.AddressDAO;
import deliveryapp.dao_classes.DiscountDAO;
import org.apache.log4j.Logger;

public class DiscountServiceImpl implements DiscountService {
    private DiscountDAO discountDAO  = new DiscountDAO();;
    private static final Logger LOGGER = Logger.getLogger(DiscountServiceImpl.class.getName());

    @Override
    public void parseFromXML() {

    }
}
