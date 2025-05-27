package products.digitalproduct;

import users.seller.Seller;

import java.io.Serializable;

public class Laptop extends DigitalProduct implements Serializable {
    private String cpuType;
    private boolean isGaming;

    public Laptop(int productID, String name, String brand, int price, Seller seller, String description,
                  int memoryCapacity, int ram, String operatingSystem, int weight, String dimensions, String cpuType,
                  boolean isGaming) {
        super(productID, name, brand, price, seller, description, memoryCapacity, ram, operatingSystem, weight, dimensions);
        this.cpuType = cpuType;
        this.isGaming = isGaming;
    }

    public String getCpuType() {
        return cpuType;
    }

    public void setCpuType(String cpuType) {
        this.cpuType = cpuType;
    }

    public boolean getIsGaming() {
        return isGaming;
    }

    public void setIsGaming(boolean isGaming) {
        this.isGaming = isGaming;
    }

    @Override
    public String toString() {
        return super.toString() +
                "cpuType='" + cpuType + '\'' +
                ", isGaming=" + isGaming ;
    }
}
