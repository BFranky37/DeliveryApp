package deliveryapp.services;

import deliveryapp.models.orders.Package;

public interface PackageService {
    public Package getPackageByID(int id);

    public int getIDbyPackage(Package u);

    public void createPackage(Package u);

    public Package getPackageInfo();

    public void updatePackage(Package u);
}
