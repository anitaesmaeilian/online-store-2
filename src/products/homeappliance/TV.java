package products.homeappliance;

import users.seller.Seller;

public class TV extends HomeAppliance{
    private int resolution;
    private int screenSize;

    public TV(int productID, String name, String brand, int price, Seller seller, String description,
              int energyConsumptionDegree, boolean haveGuarantee, int resolution, int screenSize) {
        super(productID, name, brand, price, seller, description, energyConsumptionDegree, haveGuarantee);
        this.resolution = resolution;
        this.screenSize = screenSize;
    }

    public int getResolution() {
        return resolution;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    public int getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "resolution=" + resolution +
                ", screenSize=" + screenSize ;
    }
}