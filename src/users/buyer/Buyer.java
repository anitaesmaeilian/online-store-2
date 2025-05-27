package users.buyer;

import products.Product;
import unrelated.factor.BuyingFactor;
import users.Account;

import java.io.Serializable;
import java.util.ArrayList;

public class Buyer extends Account implements Serializable {
    private ArrayList<Product> shoppingBasket;
    private ArrayList<BuyingFactor> buyingFactors;
    private int credit;
    private static ArrayList<Buyer> buyersList = new ArrayList<Buyer>();;

    public Buyer(String userName, String firstName, String lastName, String email, String phoneNumber, String password) {
        super(userName, firstName, lastName, email, phoneNumber, password, "Buyer");
        this.shoppingBasket = new ArrayList<Product>();
        this.buyingFactors = new ArrayList<BuyingFactor>();
        this.credit = 0;
    }

    public ArrayList<Product> getShoppingBasket() {
        return shoppingBasket;
    }

    public void setShoppingBasket(ArrayList<Product> shoppingBasket) {
        this.shoppingBasket = shoppingBasket;
    }

    public ArrayList<BuyingFactor> getBuyingFactors() {
        return buyingFactors;
    }

    public void setBuyingFactors(ArrayList<BuyingFactor> buyingFactors) {
        this.buyingFactors = buyingFactors;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public static ArrayList<Buyer> getBuyersList() {
        return buyersList;
    }

    public void setBuyersList(ArrayList<Buyer> buyersList) {
        this.buyersList = buyersList;
    }

    @Override
    public String toString() {
        return super.toString()+
                "shoppingBasket=" + shoppingBasket +
                ", buyingFactors=" + buyingFactors +
                ", credit=" + credit ;
    }
}
