package unrelated.factor;

import products.Product;

import java.util.ArrayList;

public class BuyingFactor{
    private int factorID;
    private String date;
    private int paidPrice;
    private ArrayList<Product> boughtProducts;
    private boolean isReceived;

    public BuyingFactor(int factorID, String date, int paidPrice, ArrayList<Product> boughtProducts, boolean isReceived) {
        this.factorID = factorID;
        this.date = date;
        this.paidPrice = paidPrice;
        this.boughtProducts = boughtProducts;
        this.isReceived = isReceived;
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

    public int getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(int paidPrice) {
        this.paidPrice = paidPrice;
    }

    public ArrayList<Product> getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(ArrayList<Product> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }

    public boolean getIsReceived() {
        return isReceived;
    }

    public void setIsReceived(boolean isReceived) {
        this.isReceived = isReceived;
    }

    public boolean isReceived() {
        return isReceived;
    }

    public void setReceived(boolean received) {
        isReceived = received;
    }
}
