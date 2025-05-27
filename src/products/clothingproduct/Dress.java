package products.clothingproduct;


import users.seller.Seller;

import java.io.Serializable;

public class Dress extends ClothingProduct implements Serializable {
    public enum type
    {SHIRT, JEANS, SKIRT, PANTS}
    public type clothesType;
    private int size;

    public Dress(int productID, String name, String brand, int price, Seller seller, String description, String producerCountry,
                 String material, type clothesType, int size) {
        super(productID, name, brand, price, seller, description, producerCountry, material);
        this.clothesType = clothesType;
        this.size = size;
    }

    public type getClothesType() {
        return clothesType;
    }

    public void setClothesType(type clothesType) {
        this.clothesType = clothesType;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "clothesType=" + clothesType +
                ", size=" + size ;
    }
}

