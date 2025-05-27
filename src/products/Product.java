package products;

import unrelated.Comment;
import unrelated.Score;
import users.seller.Seller;

import java.io.Serializable;
import java.util.ArrayList;

abstract public class Product implements Comparable, Serializable {
    private int productID;
    private String name;
    private String brand;
    private int price;
    private Seller seller;
    private boolean availability;
    private String description;
    private int averageScore;
    static ArrayList<Score> scoresList = new ArrayList<>();
    private ArrayList<Comment> commentsList;
    private static ArrayList<Product> productsList = new ArrayList<>();

    public Product(int productID, String name, String brand, int price, Seller seller, String description) {
        this.productID = productID;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.seller = seller;
        this.availability = true;
        this.description = description;
        this.averageScore = 0;
        this.commentsList = new ArrayList<Comment>();
    }

    public static ArrayList<Score> getScoresList() {
        return scoresList;
    }

    public static void setScoresList(ArrayList<Score> scoresList) {
        Product.scoresList = scoresList;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(int averageScore) {
        this.averageScore = averageScore;
    }

    public ArrayList<Comment> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(ArrayList<Comment> commentsList) {
        this.commentsList = commentsList;
    }

    public boolean isAvailability() {
        return availability;
    }

    public static ArrayList<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(ArrayList<Product> productsList) {
        this.productsList = productsList;
    }

    @Override
    public String toString() {
        return "productID=" + productID +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", seller=" + seller.getFirstName() +
                ", availability=" + availability +
                ", description='" + description + '\'' +
                ", averageScore=" + averageScore +
                ", commentsList=" + commentsList ;
    }

    public int compareTo(Object o) {
        Product o1 = (Product) o;
        if ((int)this.name.charAt(0) < (int)o1.name.charAt(0))
            return -1;
        else if ((int)this.name.charAt(0) == (int)o1.name.charAt(0)) {
            if (this.averageScore > o1.averageScore)
                return -1;
            else if (this.averageScore == o1.averageScore) {
                if (this.price > o1.price)
                    return -1;
                else if(this.price == o1.price){
                    if ((this.availability = true) && (o1.availability = false))
                        return -1;
                    else
                        return 1;
                }
                else
                    return 1;
            } else
                return 1;
        } else
            return 1;
    }
}
