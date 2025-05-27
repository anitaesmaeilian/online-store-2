package unrelated.factor;

import products.Product;

import java.util.ArrayList;

public class SellingFactor{
    private int factorID;
    private String date;
    private int receivedPrice;
    private ArrayList<Product> soldProducts;
    private String buyersName;
    private boolean isSent;

    public SellingFactor(int factorID, String date, int receivedPrice, ArrayList<Product> soldProducts, String buyersName, boolean isSent) {
        this.factorID = factorID;
        this.date = date;
        this.receivedPrice = receivedPrice;
        this.soldProducts = soldProducts;
        this.buyersName = buyersName;
        this.isSent = isSent;
    }

    public int getFactorID() {
        return factorID;
    }

    public void setFactorID(int factorID) {
        this.factorID = factorID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getReceivedPrice() {
        return receivedPrice;
    }

    public void setReceivedPrice(int receivedPrice) {
        this.receivedPrice = receivedPrice;
    }

    public ArrayList<Product> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(ArrayList<Product> soldProducts) {
        this.soldProducts = soldProducts;
    }

    public String getBuyersName() {
        return buyersName;
    }

    public void setBuyersName(String buyersName) {
        this.buyersName = buyersName;
    }

    public boolean getIsSent() {
        return isSent;
    }

    public void setSent(boolean isSent) {
        this.isSent = isSent;
    }
}
