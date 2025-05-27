package products.digitalproduct;

import products.Product;
import users.seller.Seller;

import java.io.Serializable;

abstract class DigitalProduct extends Product implements Serializable{
    private int memoryCapacity;
    private int ram;
    private String operatingSystem;
    private int weight;
    private String dimensions;

    public DigitalProduct(int productID, String name, String brand, int price, Seller seller, String description,
                          int memoryCapacity, int ram, String operatingSystem, int weight, String dimensions) {
        super(productID, name, brand, price, seller, description);
        this.memoryCapacity = memoryCapacity;
        this.ram = ram;
        this.operatingSystem = operatingSystem;
        this.weight = weight;
        this.dimensions = dimensions;
    }

    public int getMemoryCapacity() {
        return memoryCapacity;
    }

    public void setMemoryCapacity(int memoryCapacity) {
        this.memoryCapacity = memoryCapacity;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "memoryCapacity=" + memoryCapacity +
                ", ram=" + ram +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", weight=" + weight +
                ", dimensions='" + dimensions ;
    }
}
