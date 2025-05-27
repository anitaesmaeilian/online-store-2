package users.admin;

import products.Product;
import unrelated.Comment;
import users.Account;
import users.seller.Seller;

import java.io.Serializable;
import java.util.ArrayList;

public class Admin extends Account implements Serializable {
    private static Admin admin;
    private static ArrayList<Seller> sellersJoinRequests = new ArrayList<Seller>();
    private static ArrayList<Product> productsEditRequests = new ArrayList<Product>();
    private static ArrayList<Product> productsAddingRequests = new ArrayList<Product>();
    private static ArrayList<Product> productsRemovingRequests = new ArrayList<Product>();
    private static ArrayList<Comment> commentsRequests = new ArrayList<Comment>();

    private Admin() {
        super("admin", "admin", "admin", "admin", "admin", "admin", "admin");
    }

    public static Admin getAdmin(){
        if(admin == null)
            admin = new Admin();
        return admin;
    }


    public static void setAdmin(Admin admin) {
        Admin.admin = admin;
    }

    public static ArrayList<Seller> getSellersJoinRequests() {
        return sellersJoinRequests;
    }

    public static void setSellersJoinRequests(ArrayList<Seller> sellersJoinRequests) {
        Admin.sellersJoinRequests = sellersJoinRequests;
    }

    public static ArrayList<Product> getProductsEditRequests() {
        return productsEditRequests;
    }

    public static void setProductsEditRequests(ArrayList<Product> productsEditRequests) {
        Admin.productsEditRequests = productsEditRequests;
    }

    public static ArrayList<Product> getProductsAddingRequests() {
        return productsAddingRequests;
    }

    public static void setProductsAddingRequests(ArrayList<Product> productsAddingRequests) {
        Admin.productsAddingRequests = productsAddingRequests;
    }

    public static ArrayList<Product> getProductsRemovingRequests() {
        return productsRemovingRequests;
    }

    public static void setProductsRemovingRequests(ArrayList<Product> productsRemovingRequests) {
        Admin.productsRemovingRequests = productsRemovingRequests;
    }

    public static ArrayList<Comment> getCommentsRequests() {
        return commentsRequests;
    }

    public static void setCommentsRequests(ArrayList<Comment> commentsRequests) {
        Admin.commentsRequests = commentsRequests;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
