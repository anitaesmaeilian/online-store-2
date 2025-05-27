package products.digitalproduct;

import users.seller.Seller;

import java.io.Serializable;

public class Mobile extends DigitalProduct implements Serializable{
    private int simcardsNumber;
    private int cameraResolution;

    public Mobile(int productID, String name, String brand, int price, Seller seller, String description,
                  int memoryCapacity, int ram, String operatingSystem, int weight, String dimensions,
                  int simcardsNumber, int cameraResolution) {
        super(productID, name, brand, price, seller, description, memoryCapacity, ram, operatingSystem, weight, dimensions);
        this.simcardsNumber = simcardsNumber;
        this.cameraResolution = cameraResolution;
    }

    public int getSimcardsNumber() {
        return simcardsNumber;
    }

    public void setSimcardsNumber(int simcardsNumber) {
        this.simcardsNumber = simcardsNumber;
    }

    public int getCameraResolution() {
        return cameraResolution;
    }

    public void setCameraResolution(int cameraResolution) {
        this.cameraResolution = cameraResolution;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "simcardsNumber=" + simcardsNumber +
                ", cameraResolution=" + cameraResolution ;
    }
}
