package products.food;


import products.Product;
import users.seller.Seller;

import java.io.Serializable;

public class Food extends Product implements Serializable {
    private String productionDate;
    private String expirationDate;

    public Food(int productID, String name, String brand, int price, Seller seller, String description,
                String productionDate, String expirationDate) {
        super(productID, name, brand, price, seller, description);
        this.productionDate = productionDate;
        this.expirationDate = expirationDate;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "productionDate='" + productionDate + '\'' +
                ", expirationDate='" + expirationDate ;
    }
}
