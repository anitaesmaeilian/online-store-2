package products;


import com.sun.org.apache.bcel.internal.generic.GOTO;
import unrelated.Commands;
import users.buyer.BuyerCommands;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class ProductsGroupingCommands implements Serializable {
    static Scanner sc = new Scanner(System.in);
    static void digitalProductsMenu() {
        System.out.println("1-mobile   2-laptop   3-showAll");
        Scanner sc = new Scanner(System.in);

        int y = 0;
        try{
            y = sc.nextInt();
        }
        catch(InputMismatchException error){
            System.out.println("invalid input");
            sc = new Scanner(System.in);
            digitalProductsMenu();
        }
        sc.nextLine();

        switch (y) {
            case 1:
                for (Product i : Category.mobilesList.getProductsList())
                    System.out.println((i.toString()));
                detailedMenu(Category.mobilesList.getProductsList());
                break;
            case 2:
                for (Product i : Category.laptopsList.getProductsList())
                    System.out.println((i.toString()));
                detailedMenu(Category.laptopsList.getProductsList());
                break;
            case 3:
                for (Product i : Category.digitalProductList.getProductsList())
                    System.out.println((i.toString()));
                detailedMenu(Category.digitalProductList.getProductsList());
                break;
            default:
                digitalProductsMenu();
                break;
        }
    } // menuye kalahaye digital
    static void clothesMenu() {
        System.out.println("1-dress   2-shoes   3-showAll");

        int y = 0;
        try{
            y = sc.nextInt();
        }
        catch(InputMismatchException error){
            System.out.println("invalid input");
            sc = new Scanner(System.in);
            clothesMenu();
        }
        sc.nextLine();

        switch (y) {
            case 1:
                for (Product i : Category.dressList.getProductsList())
                    System.out.println((i.toString()));
                detailedMenu(Category.dressList.getProductsList());
                break;
            case 2:
                for (Product i : Category.shoesList.getProductsList())
                    System.out.println((i.toString()));
                detailedMenu(Category.shoesList.getProductsList());
                break;
            case 3:
                for (Product i : Category.clothesList.getProductsList())
                    System.out.println((i.toString()));
                detailedMenu(Category.clothesList.getProductsList());
                break;
            default:
                clothesMenu();
                break;
        }
    } // menuye lebas ha

    static void homeApplianceMenu(){
        System.out.println("1-TV   2-refrigerator   3-stove   4-showAll");

        int y = 0;
        try{
            y = sc.nextInt();
        }
        catch(InputMismatchException error){
            System.out.println("invalid input");
            sc = new Scanner(System.in);
            homeApplianceMenu();
        }
        sc.nextLine();

        switch (y) {
            case 1:
                for(Product i: Category.tvsList.getProductsList())
                    System.out.println((i.toString()));
                detailedMenu(Category.tvsList.getProductsList());
                break;
            case 2:
                for(Product i: Category.refrigeratorsList.getProductsList())
                    System.out.println((i.toString()));
                detailedMenu(Category.refrigeratorsList.getProductsList());
                break;
            case 3:
                for(Product i: Category.stovesList.getProductsList())
                    System.out.println((i.toString()));
                detailedMenu(Category.stovesList.getProductsList());
                break;
            case 4:
                for(Product i: Category.homeApplianceList.getProductsList())
                    System.out.println((i.toString()));
                detailedMenu(Category.homeApplianceList.getProductsList());
                break;
            default:
                homeApplianceMenu();
                break;
        }
    } // menuye kalahaye khanegi
    static void foodsMenu(){
        for(Product i: Category.foodsList.getProductsList())
            System.out.println((i.toString()));
        detailedMenu(Category.foodsList.getProductsList());
    } // menuye ghazaha
    static void detailedMenu(ArrayList<Product> list){
        System.out.println("reach the product through searching in order to buy.");
        System.out.println("1-search   2-sort");

        int x = 0;
        try{
            x = sc.nextInt();
        }
        catch(InputMismatchException error){
            System.out.println("invalid input");
            sc = new Scanner(System.in);
            detailedMenu(list);
        }
        sc.nextLine();

        if(x==1)
            search(list);
        else if(x==2) {
            System.out.println("1-by score   2-by price   3-default");

            int y = 0;
            try{
                y = sc.nextInt();
            }
            catch(InputMismatchException error){
                System.out.println("invalid input");
                sc = new Scanner(System.in);
                detailedMenu(list);
            }
            sc.nextLine();

            if (y == 1)
                scoreSort(list);
            else if (y == 2)
                priceSort(list);
            else if (y == 3)
                sort(list);
            else
                detailedMenu(list);

            System.out.println("reach the product through searching in order to buy.");
            System.out.println("1-search   2-remove filters   3-home");

            int z = 0;
            try{
                z = sc.nextInt();
            }
            catch(InputMismatchException error){
                System.out.println("invalid input");
                sc = new Scanner(System.in);
                detailedMenu(list);
            }
            sc.nextLine();

            switch (z) {
                case 1:
                    search(list);
                    break;
                case 2:
                    BuyerCommands.showProducts();
                    break;
                case 3:
                    break;
                default:
                    detailedMenu(list);
                    break;
            }
        }
        else
            detailedMenu(list);
    } // kharid be vasileye search va sort

    public static void search(ArrayList<Product> list){
        System.out.println("please fill the following information.");
        System.out.println("product ID:");
        int productID = 0;
        try{
            productID = sc.nextInt();
        }
        catch(InputMismatchException error){
            System.out.println("invalid input");
            sc = new Scanner(System.in);
            search(list);
        }
        sc.nextLine();

        for(Product i:list)
            if(productID == i.getProductID()) {
                System.out.println(i.toString());
                System.out.println("1-add to shopping basket   2-comment   3-home");
                int x = 0;
                try{
                    x = sc.nextInt();
                }
                catch(InputMismatchException error){
                    System.out.println("invalid input");
                    sc = new Scanner(System.in);
                    search(list);
                }
                sc.nextLine();
                switch (x){
                    case 1:
                        if (Commands.loggedBuyer == null)
                            System.out.println("you have to log in to your buyer account first");
                        else {
                            Commands.loggedBuyer.getShoppingBasket().add(i);
                            System.out.println("added successfully.");
                        }
                        break;
                    case 2:
                        BuyerCommands.comment(i);
                        break;
                    case 3:
                        break;
                    default:
                        search(list);
                        break;
                }
            }
    } // jostojo dar daste haye mokhtalefe kala
    static void scoreSort(ArrayList<Product> list){
        ArrayList<Product> tempScoresList = list;
        for(int i = tempScoresList.size()-1; i>0; i--)
            for(int j=0; j<i; j++)
                if(tempScoresList.get(j).getAverageScore()>tempScoresList.get(j+1).getAverageScore())
                    Collections.swap(tempScoresList, j, j+1);
        for (Product i : tempScoresList)
            System.out.println((i.toString()));
    } // bubble sort bar asase emtiaz
    static void priceSort(ArrayList<Product> list){
        ArrayList<Product> tempPricesList = list;
        for(int i = tempPricesList.size()-1; i>0; i--)
            for(int j=0; j<i; j++)
                if(tempPricesList.get(j).getPrice()>tempPricesList.get(j+1).getPrice())
                    Collections.swap(tempPricesList, j, j+1);
        for (Product i : tempPricesList)
            System.out.println((i.toString()));
    } // bubble sort bar asase gheymat
    static void sort(ArrayList<Product> list){
        for(int i=0; i<list.size(); i++)
            for(int j=i+1; j<list.size(); j++)
                if(list.get(i).compareTo((list).get(j)) > 0){
                    list.add(list.get(i));
                    list.remove(i);
                }
    }
    public static void filter(){
        System.out.println("What product are you looking for?");
        System.out.println("1-digital products   2-clothes   3-home appliances   4-food");
        int x = 0;
        try{
            x = sc.nextInt();
        }
        catch(InputMismatchException error){
            System.out.println("invalid input");
            sc = new Scanner(System.in);
            filter();
        }
        sc.nextLine();
        if(x==1)
            ProductsGroupingCommands.digitalProductsMenu();
        else if(x==2)
            ProductsGroupingCommands.clothesMenu();
        else if(x==3)
            ProductsGroupingCommands.homeApplianceMenu();
        else if(x==4)
            ProductsGroupingCommands.foodsMenu();
        else filter();
    } // filtere daste ha
}
