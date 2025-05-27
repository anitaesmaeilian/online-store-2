package products.homeappliance;


import users.seller.Seller;

public class Refrigerator extends HomeAppliance{
    private int capacity;
    private String type;
    private boolean haveFridge;

    public Refrigerator(int productID, String name, String brand, int price, Seller seller, String description,
                        int energyConsumptionDegree, boolean haveGuarantee, int capacity, String type, boolean haveFridge) {
        super(productID, name, brand, price, seller, description, energyConsumptionDegree, haveGuarantee);
        this.capacity = capacity;
        this.type = type;
        this.haveFridge = haveFridge;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getHaveFridge() {
        return haveFridge;
    }

    public void setHaveFridge(boolean haveFridge) {
        this.haveFridge = haveFridge;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "capacity=" + capacity +
                ", type='" + type + '\'' +
                ", haveFridge=" + haveFridge ;
    }
}

