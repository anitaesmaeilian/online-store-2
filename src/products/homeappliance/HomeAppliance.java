package products.homeappliance;

import products.Product;
import users.seller.Seller;

abstract class HomeAppliance extends Product {
    private int energyConsumptionDegree;
    private boolean haveGuarantee;

    public HomeAppliance(int productID, String name, String brand, int price, Seller seller, String description,
                         int energyConsumptionDegree, boolean haveGuarantee) {
        super(productID, name, brand, price, seller, description);
        this.energyConsumptionDegree = energyConsumptionDegree;
        this.haveGuarantee = haveGuarantee;
    }

    public int getEnergyConsumptionDegree() {
        return energyConsumptionDegree;
    }

    public void setEnergyConsumptionDegree(int energyConsumptionDegree) {
        this.energyConsumptionDegree = energyConsumptionDegree;
    }

    public boolean getHaveGuarantee() {
        return haveGuarantee;
    }

    public void setHaveGuarantee(boolean haveGuarantee) {
        this.haveGuarantee = haveGuarantee;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "energyConsumptionDegree=" + energyConsumptionDegree +
                ", haveGuarantee=" + haveGuarantee ;
    }
}
