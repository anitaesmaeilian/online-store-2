package files;

import users.admin.Admin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Formatter;

public class Files implements Serializable {
    public static void createFiles() throws IOException {
        File savedData = new File("Saved Data");
        if (!savedData.exists())
            savedData.mkdir();

        File users = new File(savedData, "Users");
        if (!users.exists())
            users.mkdir();
        File admin = new File(users, "Admin");
        if (!admin.exists())
            admin.mkdir();
        File requests = new File(admin, "requests");
        if (!requests.exists())
            requests.mkdir();
        File sellersRequests = new File(requests, "Sellers Requests");
        if (!sellersRequests.exists())
            sellersRequests.mkdir();



        FileWriter sellersJoin = new FileWriter(sellersRequests.getPath() + "/join requests.txt");

        FileWriter sellersEdit = new FileWriter(sellersRequests.getPath() + "/edit requests.txt");

        File productsRequests = new File(requests, "Products requests");
        if (!productsRequests.exists())
            productsRequests.mkdir();
        FileWriter productsAdd = new FileWriter(productsRequests.getPath() + "/add requests.txt");

        FileWriter productsEdit = new FileWriter(productsRequests.getPath() + "/edit requests.txt");

        FileWriter productsRemove = new FileWriter(productsRequests.getPath() + "/remove requests.txt");


        FileWriter requestsList = new FileWriter(admin.getPath() + "/Requests List.txt");
        FileWriter information = new FileWriter(admin.getPath() + "/Information.txt");
        Formatter formatter = new Formatter(information);
        formatter.format("%s", Admin.getAdmin().toString());
        formatter.close();
        File buyers = new File(users, "Buyers");
        if (!buyers.exists())
            buyers.mkdir();
        File sellers = new File(users, "Sellers");
        if (!sellers.exists())
            sellers.mkdir();
        File categories = new File(savedData, "Categories");
        if (!categories.exists())
            categories.mkdir();
        File clothes = new File(categories, "Clothes");
        if (!clothes.exists())
            clothes.mkdir();
        File shoes = new File(clothes, "Shoes");
        if (!shoes.exists())
            shoes.mkdir();
        File dress = new File(clothes, "Dress");
        if (!dress.exists())
            dress.mkdir();
        File food = new File(categories, "Food");
        if (!food.exists())
            food.mkdir();
        File digitalProducts = new File(categories, "Digital Products");
        if (!digitalProducts.exists())
            digitalProducts.mkdir();
        File laptop = new File(digitalProducts, "Laptop");
        if (!laptop.exists())
            laptop.mkdir();
        File mobile = new File(digitalProducts, "Mobile");
        if (!mobile.exists())
            mobile.mkdir();
        File homeAppliance = new File(categories, "Home Appliance");
        if (!homeAppliance.exists())
            homeAppliance.mkdir();
        File TV = new File(homeAppliance, "TV");
        if (!TV.exists())
            TV.mkdir();
        File stove = new File(homeAppliance, "Stove");
        if (!stove.exists())
            stove.mkdir();
        File refrigerator = new File(homeAppliance, "Refrigerator");
        if (!refrigerator.exists())
            refrigerator.mkdir();
    }
}