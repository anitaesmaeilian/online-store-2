package products.clothingproduct;

import products.Product;
import users.seller.Seller;

import java.io.Serializable;

abstract class ClothingProduct extends Product implements Serializable {
    private String producerCountry;
    private String material;

    public ClothingProduct(int productID, String name, String brand, int price, Seller seller, String description,
                           String producerCountry, String material) {
        super(productID, name, brand, price, seller, description);
        this.producerCountry = producerCountry;
        this.material = material;
    }

    public String getProducerCountry() {
        return producerCountry;
    }

    public void setProducerCountry(String producerCountry) {
        this.producerCountry = producerCountry;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "producerCountry='" + producerCountry + '\'' +
                ", material='" + material ;
    }
}
