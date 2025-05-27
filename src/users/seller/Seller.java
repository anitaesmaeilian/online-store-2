package users.seller;

import products.Product;
import products.Product;
import unrelated.factor.BuyingFactor;
import unrelated.factor.SellingFactor;
import users.Account;

import java.io.Serializable;
import java.util.ArrayList;

public class Seller extends Account implements Serializable {
    private String companysName;
    private ArrayList<SellingFactor> sellingFactors;
    private int credit;
    private ArrayList<Product> onSaleProducts;
    private static ArrayList<Seller> sellersList = new ArrayList<Seller>();

    public Seller(String userName, String firstName, String lastName, String email, String phoneNumber, String password,
                  String companysName) {
        super(userName, firstName, lastName, email, phoneNumber, password, "Seller");
        this.companysName = companysName;
        this.sellingFactors = new ArrayList<SellingFactor>();
        this.credit = 0;
        this.onSaleProducts = new ArrayList<Product>();
    }

    public static ArrayList<Seller> getSellersList() {
        return sellersList;
    }

    public static void setSellersList(ArrayList<Seller> sellersList) {
        Seller.sellersList = sellersList;
    }

    public String getCompanysName() {
        return companysName;
    }

    public void setCompanysName(String companysName) {
        this.companysName = companysName;
    }

    public ArrayList<SellingFactor> getSellingFactors() {
        return sellingFactors;
    }

    public void setSellingFactors(ArrayList<SellingFactor> sellingFactors) {
        this.sellingFactors = sellingFactors;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public ArrayList<Product> getOnSaleProducts() {
        return onSaleProducts;
    }

    public void setOnSaleProducts(ArrayList<Product> onSaleProducts) {
        this.onSaleProducts = onSaleProducts;
    }

    @Override
    public String toString() {
        return  super.toString()+
                "companysName='" + companysName + '\'' +
                ", sellingFactors=" + sellingFactors +
                ", credit=" + credit +
                ", onSaleProducts=" + onSaleProducts ;
    }
}
