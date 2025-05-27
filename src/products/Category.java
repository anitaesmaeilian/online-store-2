package products;


import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable {
    private String name;
    private ArrayList<Product> productsList;
    public static Category digitalProductList = new Category("digital products list");
    public static Category mobilesList = new Category("mobiles list");
    public static Category laptopsList = new Category("laptops list");
    public static Category clothesList = new Category("Clothes list");
    public static Category dressList = new Category("dress list");
    public static Category shoesList = new Category("shoes list");
    public static Category homeApplianceList = new Category("home appliance list");
    public static Category tvsList = new Category("TVs list");
    public static Category refrigeratorsList = new Category("refrigerators list");
    public static Category stovesList = new Category("stoves list");
    public static Category foodsList = new Category("foods list");

    public Category(String name) {
        this.name = name;
        this.productsList = new ArrayList<Product>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(ArrayList<Product> productsList) {
        this.productsList = productsList;
    }
}
