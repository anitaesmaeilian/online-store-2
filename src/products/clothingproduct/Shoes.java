package products.clothingproduct;


import users.seller.Seller;

import java.io.Serializable;

public class Shoes extends ClothingProduct implements Serializable {
    public enum type {BOOTS, SNEAKERS, HEELS, SANDALS}

    private type shoesType;
    private int size;

    public Shoes(int productID, String name, String brand, int price, Seller seller, String description, String producerCountry,
                 String material, type shoesType, int size) {
        super(productID, name, brand, price, seller, description, producerCountry, material);
        this.shoesType = shoesType;
        this.size = size;
    }

    public type getShoesType() {
        return shoesType;
    }

    public void setShoesType(type shoesType) {
        this.shoesType = shoesType;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return super.toString() +
                "shoesType=" + shoesType +
                ", size=" + size;
    }
}

