package unrelated;


import products.Product;
import products.ProductsGroupingCommands;
import users.admin.AdminCommands;
import users.buyer.BuyerCommands;
import users.seller.SellerCommands;

import java.io.IOException;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Menu implements Serializable {
    public static boolean loggedIn = false;
    static Scanner sc = new Scanner(System.in);

    public static void menu() throws IOException {
        System.out.println("Choose where you want to go.");
        System.out.println("1-users section     2-products section     3-admin section");
        int x = 0;
        try {
            x = sc.nextInt();
        }
        catch(InputMismatchException error){
            System.out.println("invalid input");
            sc = new Scanner(System.in);
            menu();
        }
        sc.nextLine();

        if(x==1)
            usersSection();
        else if(x==2)
            productsSection();
        else if(x==3)
            AdminCommands.adminSection();
        else
            menu();
    } // safheye asli

    static void usersSection() throws IOException {
        if (loggedIn) {
            if (Commands.loggedBuyer == null) {
                System.out.println(Commands.loggedSeller.toString());
                SellerCommands.showSellersCommands();
            } else if (Commands.loggedSeller == null) {
                System.out.println(Commands.loggedBuyer.toString());
                BuyerCommands.showBuyersCommands();
            }
        }

            System.out.println("Do you want to log in or make a new account?");
            System.out.println("1-log in     2-make a new account");
            int y = 0;
            try {
                y = sc.nextInt();
            }
            catch(InputMismatchException error){
                System.out.println("invalid input");
                sc = new Scanner(System.in);
                usersSection();
            }
            sc.nextLine();

            if(y==1)
                Commands.logIn();
            else if(y==2)
                Commands.register(); // in try
            else
                usersSection();
        }
     // safheye forushande va moshtari ha

    static void productsSection(){
        System.out.println("1-show all products   2-search for products   3-use sort and filters");

        int x = 0;
        try{
            x = sc.nextInt();
        }
        catch(InputMismatchException error){
            System.out.println("invalid input");
            sc = new Scanner(System.in);
            productsSection();
        }
        sc.nextLine();

        if (x==1)
            BuyerCommands.showProducts();
        else if (x==2)
            ProductsGroupingCommands.search(Product.getProductsList());
        else if (x==3)
            ProductsGroupingCommands.filter();
        else
            productsSection();
    } // safheye kala ha
}



