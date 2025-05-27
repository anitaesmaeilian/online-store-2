package users.admin;


import products.Category;
import products.Product;
import products.clothingproduct.Dress;
import products.clothingproduct.Shoes;
import products.digitalproduct.Laptop;
import products.digitalproduct.Mobile;
import products.food.Food;
import products.homeappliance.Refrigerator;
import products.homeappliance.Stove;
import products.homeappliance.TV;
import unrelated.Commands;
import unrelated.Comment;
import unrelated.Menu;
import users.buyer.Buyer;
import users.seller.Seller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class AdminCommands implements Serializable {
    static Scanner sc = new Scanner(System.in);
    public static void adminSection() throws IOException {
        if (Menu.loggedIn && Commands.loggedBuyer == null && Commands.loggedSeller == null) {
            System.out.println(Admin.getAdmin().toString());
            AdminCommands.showAdminsCommands();
        } else
            System.out.println("you have to log in first.");
    } // panele admin

    public static void showAdminsCommands() throws IOException {
        // printe ekhtiarat va gereftane shomareye dastor
        System.out.println("1-manage sellers join requests   2-remove user   3-manage products add requests   " +
                "4-manage products remove requests   manage products edit requests   6-manage comments   7-show users   " +
                "8-change personal info   9-log out ");
        int x = 0;
        try{
            x = sc.nextInt();
        }
        catch(InputMismatchException error){
            System.out.println("invalid input");
            sc = new Scanner(System.in);
            showAdminsCommands();
        }
        sc.nextLine();
        // ejraye dastor bar asase shomare
        switch (x) {
            case 1:
                manageSellersJoinRequests();
                break;
            case 2:
                removeUser();
                break;
            case 3:
                manageProductAddRequests();
                break;
            case 4:
                manageProductRemoveRequests();
                break;
            case 5:
                manageProductEditRequests();
                break;
            case 6:
                manageComments();
                break;
            case 7:
                showUsers();
                break;
            case 8:
                Commands.changePersonalInfo(Admin.getAdmin());
                break;
            case 9:
                Menu.loggedIn = false;
                break;
            default:
                showAdminsCommands();
                break;
        }
    } // neshan dadan va ejraye dastorate modir

    static void manageSellersJoinRequests() throws IOException {
        for (Seller i : Admin.getSellersJoinRequests()) {
            System.out.println("do you want to let this seller join?");
            System.out.println(i.toString());
            System.out.println("1-yes     2-no");

            int x = 0;
            try{
                x = sc.nextInt();
            }
            catch(InputMismatchException error){
                System.out.println("invalid input");
                sc = new Scanner(System.in);
                manageSellersJoinRequests();
            }
            sc.nextLine();

            if (x == 1) {  // ezafe kardane forushande
                Seller.getSellersList().add(i);
                File newSeller = new File("Saved Data/Users/Sellers/Seller " + String.valueOf(Seller.getSellersList().size()));
                newSeller.mkdir();
                File productsList = new File(newSeller, "Products List");
                productsList.mkdir();
                FileWriter sellingFactorsList = new FileWriter(newSeller.getPath() + "/Selling Factors List.txt");
                FileWriter information = new FileWriter(newSeller.getPath() + "/Information.txt");
                Formatter formatter = new Formatter(information);
                formatter.format("%s", i.toString());
                formatter.close();
            }
            else if (x>2) {
                System.out.println("you have to choose either yes or no");
                manageSellersJoinRequests();
            }
        }
        Admin.getSellersJoinRequests().clear();
    } // residegi be darkhast haye sabte name forushande

    static void manageComments() {
        for (Comment i : Admin.getCommentsRequests()) {
            System.out.println("do you want to approve this comment?");
            System.out.println(i.toString());
            System.out.println("1-yes   2-no");

            int x = 0;
            try{
                x = sc.nextInt();
            }
            catch(InputMismatchException error){
                System.out.println("invalid input");
                sc = new Scanner(System.in);
                manageComments();
            }
            sc.nextLine();

            if (x == 1) {
                i.setCommentStatus(Comment.commentStatus.APPROVED);
                i.getCommentedProduct().getCommentsList().add(i);

            } else if (x == 2)
                i.setCommentStatus(Comment.commentStatus.UNAPPROVED);
            else {
                System.out.println("you have to choose either yes or no");
                manageComments();
            }
        }
        Admin.getCommentsRequests().clear();
    } // residegi be comment ha

    static void manageProductAddRequests() throws IOException {
        for (Product i : Admin.getProductsAddingRequests()) {
            System.out.println("do you want to let this product join?");
            System.out.println(i.toString());
            System.out.println("1-yes     2-no");

            int x = 0;
            try{
                x = sc.nextInt();
            }
            catch(InputMismatchException error){
                System.out.println("invalid input");
                sc = new Scanner(System.in);
                manageProductAddRequests();
            }
            sc.nextLine();

            if (x == 1) { // ezafe kardane kala
                i.getSeller().getOnSaleProducts().add(i);
                Product.getProductsList().add(i);
                putInRightCategory(i);
            }
            else if (x>2) {
                System.out.println("you have to choose either yes or no");
                manageProductAddRequests();
            }
        }
        Admin.getProductsAddingRequests().clear();
    } // residegi be darkhast haye ezafe kardane mahsol

    static void manageProductRemoveRequests() {
        for (Product i : Admin.getProductsRemovingRequests()) {
            System.out.println("do you want to let this product be removed?");
            System.out.println(i.toString());
            System.out.println("1-yes     2-no");

            int x = 0;
            try{
                x = sc.nextInt();
            }
            catch(InputMismatchException error){
                System.out.println("invalid input");
                sc = new Scanner(System.in);
                manageProductRemoveRequests();
            }
            sc.nextLine();

            if (x == 1) { // hazfe kala
                i.getSeller().getOnSaleProducts().remove(i);
                Product.getProductsList().remove(i);
                removeFromCategory(i);
            }
            else if (x>2) {
                System.out.println("you have to choose either yes or no");
                manageProductRemoveRequests();
            }
        }
        Admin.getProductsRemovingRequests().clear();
    } // residegi be darkhast haye hazfe mahsol

    static void manageProductEditRequests() throws IOException {
        Product tempOldProduct = null;
        Product tempNewProduct = null;
        for (Product i : Admin.getProductsEditRequests()) {
            System.out.println("do you want to let this product be edited?");
            for (Product j : i.getSeller().getOnSaleProducts()) // printe etelaate kala ghabl va bade edit
                if (i.getProductID() == j.getProductID()) {
                    System.out.println("before:");
                    System.out.println(j.toString());
                    System.out.println("after:");
                    System.out.println(i.toString());
                    tempOldProduct = j;
                }
            System.out.println("1-yes     2-no");

            int x = 0;
            try{
                x = sc.nextInt();
            }
            catch(InputMismatchException error){
                System.out.println("invalid input");
                sc = new Scanner(System.in);
                manageProductEditRequests();
            }
            sc.nextLine();

            if (x == 1) {// hazfe kalaye ghable edit va ezafe kardane kalaye jadid
                tempNewProduct = i;
                Product.getProductsList().remove(tempOldProduct);
                Product.getProductsList().add(tempNewProduct);
                tempNewProduct.getSeller().getOnSaleProducts().remove(tempOldProduct);
                tempNewProduct.getSeller().getOnSaleProducts().add(tempNewProduct);
                removeFromCategory(tempOldProduct);
                putInRightCategory(tempNewProduct);
            }
            else if (x>2) {
                System.out.println("you have to choose either yes or no");
                manageProductEditRequests();
            }
        }
        Admin.getProductsEditRequests().clear();
    }// residegi be darkhast haye edite mahsol

    static void putInRightCategory(Product product) throws IOException {
        if (product instanceof Mobile) {
            Category.mobilesList.getProductsList().add(product);
            Category.digitalProductList.getProductsList().add(product);
            File newMobile = new File("Saved Data/Categories/Digital Products/Mobile/mobile " + String.valueOf(Mobile.getProductsList().size()));
            newMobile.mkdir();
            FileWriter scores = new FileWriter(newMobile.getPath() + "/Scores.txt");
            FileWriter information = new FileWriter(newMobile.getPath() + "/Information.txt");
            Formatter formatter = new Formatter(information);
            formatter.format("%s", product.toString());
            formatter.close();
        } else if (product instanceof Laptop) {
            Category.laptopsList.getProductsList().add(product);
            Category.digitalProductList.getProductsList().add(product);
            File newLaptop = new File("Saved Data/Categories/Digital Products/Laptop/laptop " + String.valueOf(Laptop.getProductsList().size()));
            newLaptop.mkdir();
            FileWriter scores = new FileWriter(newLaptop.getPath() + "/Scores.txt");
            FileWriter information = new FileWriter(newLaptop.getPath() + "/Information.txt");
            Formatter formatter = new Formatter(information);
            formatter.format("%s", product.toString());
            formatter.close();
        } else if (product instanceof Dress) {
            Category.dressList.getProductsList().add(product);
            Category.clothesList.getProductsList().add(product);
            File newDress = new File("Saved Data/Categories/Clothes/Dress/dress " + String.valueOf(Dress.getProductsList().size()));
            newDress.mkdir();
            FileWriter scores = new FileWriter(newDress.getPath() + "/Scores.txt");
            FileWriter information = new FileWriter(newDress.getPath() + "/Information.txt");
            Formatter formatter = new Formatter(information);
            formatter.format("%s", product.toString());
            formatter.close();
        } else if (product instanceof Shoes) {
            Category.shoesList.getProductsList().add(product);
            Category.clothesList.getProductsList().add(product);
            File newShoes = new File("Saved Data/Categories/Clothes/Shoes/shoes " + String.valueOf(Shoes.getProductsList().size()));
            newShoes.mkdir();
            FileWriter scores = new FileWriter(newShoes.getPath() + "/Scores.txt");
            FileWriter information = new FileWriter(newShoes.getPath() + "/Information.txt");
            Formatter formatter = new Formatter(information);
            formatter.format("%s", product.toString());
            formatter.close();
        } else if (product instanceof TV) {
            Category.tvsList.getProductsList().add(product);
            Category.homeApplianceList.getProductsList().add(product);
            File newTV = new File("Saved Data/Categories/Home Appliance/TV/tv " + String.valueOf(TV.getProductsList().size()));
            newTV.mkdir();
            FileWriter scores = new FileWriter(newTV.getPath() + "/Scores.txt");
            FileWriter information = new FileWriter(newTV.getPath() + "/Information.txt");
            Formatter formatter = new Formatter(information);
            formatter.format("%s", product.toString());
            formatter.close();
        } else if (product instanceof Refrigerator) {
            Category.refrigeratorsList.getProductsList().add(product);
            Category.homeApplianceList.getProductsList().add(product);
            File newRef = new File("Saved Data/Categories/Home Appliance/Refrigerator/refrigerator " + String.valueOf(Refrigerator.getProductsList().size()));
            newRef.mkdir();
            FileWriter scores = new FileWriter(newRef.getPath() + "/Scores.txt");
            FileWriter information = new FileWriter(newRef.getPath() + "/Information.txt");
            Formatter formatter = new Formatter(information);
            formatter.format("%s", product.toString());
            formatter.close();
        } else if (product instanceof Stove) {
            Category.stovesList.getProductsList().add(product);
            Category.homeApplianceList.getProductsList().add(product);
            File newStove = new File("Saved Data/Categories/Home Appliance/Stove/stove " + String.valueOf(Stove.getProductsList().size()));
            newStove.mkdir();
            FileWriter scores = new FileWriter(newStove.getPath() + "/Scores.txt");
            FileWriter information = new FileWriter(newStove.getPath() + "/Information.txt");
            Formatter formatter = new Formatter(information);
            formatter.format("%s", product.toString());
            formatter.close();
        } else if (product instanceof Food) {
            Category.foodsList.getProductsList().add(product);
            File newFood = new File("Saved Data/Categories/Food/food " + String.valueOf(Food.getProductsList().size()));
            newFood.mkdir();
            FileWriter scores = new FileWriter(newFood.getPath() + "/Scores.txt");
            FileWriter information = new FileWriter(newFood.getPath() + "/Information.txt");
            Formatter formatter = new Formatter(information);
            formatter.format("%s", product.toString());
            formatter.close();
        }
    } // gharar dadane kala dar daste haye mortabet

    static void removeFromCategory(Product product) {
        if (product instanceof Mobile) {
            Category.mobilesList.getProductsList().remove(product);
            Category.digitalProductList.getProductsList().remove(product);
        } else if (product instanceof Laptop) {
            Category.laptopsList.getProductsList().remove(product);
            Category.digitalProductList.getProductsList().remove(product);
        } else if (product instanceof Dress) {
            Category.dressList.getProductsList().remove(product);
            Category.clothesList.getProductsList().remove(product);
        } else if (product instanceof Shoes) {
            Category.shoesList.getProductsList().remove(product);
            Category.clothesList.getProductsList().remove(product);
        } else if (product instanceof TV) {
            Category.tvsList.getProductsList().remove(product);
            Category.homeApplianceList.getProductsList().remove(product);
        } else if (product instanceof Refrigerator) {
            Category.refrigeratorsList.getProductsList().remove(product);
            Category.homeApplianceList.getProductsList().remove(product);
        } else if (product instanceof Stove) {
            Category.stovesList.getProductsList().remove(product);
            Category.homeApplianceList.getProductsList().remove(product);
        } else if (product instanceof Food)
            Category.foodsList.getProductsList().remove(product);
    } // remove kardane kala az daste bad az hazf

    static void removeUser() {
        showUsers();
        System.out.println("enter the role of the user you want to remove.");
        System.out.println("1-Seller    2-Buyer");

        int x = 0;
        try{
            x = sc.nextInt();
        }
        catch(InputMismatchException error){
            System.out.println("invalid input");
            sc = new Scanner(System.in);
            removeUser();
        }
        sc.nextLine();

        System.out.println("enter the username of the user you want to remove.");
        String username = sc.nextLine();
        Seller tempSeller = null;
        Buyer tempBuyer = null;
        if (x == 1) {
            for (Seller i : Seller.getSellersList())
                if (username.equals(i.getUserName()))
                    tempSeller = i;
            Seller.getSellersList().remove(tempSeller);
            System.out.println("removed successfully.");
        } else if (x == 2) {
            for (Buyer i : Buyer.getBuyersList())
                if (username.equals(i.getUserName()))
                    tempBuyer = i;
            Buyer.getBuyersList().remove(tempBuyer);
            System.out.println("removed successfully.");
        }
        else{
            System.out.println("you have to choose either yes or no");
            removeUser();
        }
    } // ekhraje karbar

    static void showUsers() {
        System.out.println("Sellers:");
        for (Seller i : Seller.getSellersList())
            System.out.println(i.toString());

        System.out.println("Buyers:");
        for (Buyer i : Buyer.getBuyersList())
            System.out.println(i.toString());
    } // neshan dadane karbaran
}

