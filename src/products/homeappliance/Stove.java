package products.homeappliance;


import users.seller.Seller;

public class Stove extends HomeAppliance{
    private int burnersNumber;
    private String material;
    private boolean haveOven;

    public Stove(int productID, String name, String brand, int price, Seller seller, String description,
                 int energyConsumptionDegree, boolean haveGuarantee, int burnersNumber, String material, boolean haveOven) {
        super(productID, name, brand, price, seller, description, energyConsumptionDegree, haveGuarantee);
        this.burnersNumber = burnersNumber;
        this.material = material;
        this.haveOven = haveOven;
    }

    public int getBurnersNumber() {
        return burnersNumber;
    }

    public void setBurnersNumber(int burnersNumber) {
        this.burnersNumber = burnersNumber;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public boolean getHaveOven() {
        return haveOven;
    }

    public void setHaveOven(boolean haveOven) {
        this.haveOven = haveOven;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "burnersNumber=" + burnersNumber +
                ", material='" + material + '\'' +
                ", haveOven=" + haveOven ;
    }
}
