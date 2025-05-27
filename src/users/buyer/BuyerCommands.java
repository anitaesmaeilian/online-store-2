package users.buyer;


import exceptions.InsufficientMoneyError;
import exceptions.InsufficientSupplyError;
import exceptions.PhoneNumberError;
import products.Product;
import products.ProductsGroupingCommands;
import unrelated.Commands;
import unrelated.Comment;
import unrelated.Menu;
import unrelated.Score;
import unrelated.factor.BuyingFactor;
import unrelated.factor.SellingFactor;
import users.admin.Admin;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class BuyerCommands implements Serializable {
    static Scanner sc = new Scanner(System.in);
    public static void showBuyersCommands(){
        System.out.println("1-show products   2-filter");
        System.out.println("3-show shopping basket(in order to buy)    4-score");
        System.out.println("5-log out   6-edit shopping basket");
        Scanner sc = new Scanner(System.in);

        int x = 0;
        try{
            x = sc.nextInt();
        }
        catch(InputMismatchException error){
            System.out.println("invalid input");
            sc = new Scanner(System.in);
            showBuyersCommands();
        }
        sc.nextLine();

        switch (x){
            case 1:
                showProducts();
                break;
            case 2:
                ProductsGroupingCommands.filter();
                break;
            case 3:
                showShoppingBasket();
                break;
            case 4:
                score();
                break;
            case 5:
                Commands.loggedBuyer = null;
                Menu.loggedIn = false;
                break;
            case 6:
                editShoppingBasket();
                break;
            default:
                showBuyersCommands();
                break;
        }
    } // neshan dadan va ejraye dastorate moshtari
    public static void showProducts(){
        int number = 1;
        for(Product i : Product.getProductsList()){
            System.out.println(number + "-" + i.toString());
            number++;
        }
        System.out.println("enter the number of the product in order to comment.");

        int y = 0;
        try{
            y = sc.nextInt();
        }
        catch(InputMismatchException error){
            System.out.println("invalid input");
            sc = new Scanner(System.in);
            showProducts();
        }
        sc.nextLine();

        if(y>Product.getProductsList().size()){
            System.out.println("there is no such product");
            showProducts();
        }

        System.out.println(Product.getProductsList().get(y-1).toString());
        System.out.println("1-comment   2-home  (reach through searching in order to add to shopping basket)");

        int z = 0;
        try{
            z = sc.nextInt();
        }
        catch(InputMismatchException error){
            System.out.println("invalid input");
            sc = new Scanner(System.in);
            showProducts();
        }
        sc.nextLine();

        if(z==1)
            comment(Product.getProductsList().get(y - 1));
        else if (z > 2){
            System.out.println("wrong input");
            showProducts();
        }

    } // neshan dadane hameye kalaha va comment gereftan

    static void showShoppingBasket(){
        System.out.println("items:");
        for(Product i : Commands.loggedBuyer.getShoppingBasket())
            System.out.println(i.toString());
        System.out.println("finished?");
        System.out.println("1-yes     2-no");
        int x = 0;
        try{
            x = sc.nextInt();
        }
        catch(InputMismatchException error){
            System.out.println("invalid input");
            sc = new Scanner(System.in);
            showShoppingBasket();
        }
        sc.nextLine();
        if(x==1)
            shipping();
        else if (x > 2){
            System.out.println("wrong input");
            showShoppingBasket();
        }
    } // neshan dadane sabade kharid
    static void shipping(){
        for (Product i : Commands.loggedBuyer.getShoppingBasket()){
            if (i.getAvailability() == false){
                try {
                    throw new InsufficientSupplyError();
                }catch (InsufficientSupplyError insufficientSupplyError){
                    System.out.println(insufficientSupplyError.getMessage());
                    showBuyersCommands();
                }
            }
        }
        System.out.println("please fill the following information.");
        System.out.println("address:");
        String x = sc.nextLine();
        System.out.println("postal code:");
        String y = sc.nextLine();

        System.out.print("phone number: ");
        String phoneNumber = sc.nextLine();
        String phoneNumberRegax = "\\d{11}$";
        Pattern phoneNumberPattern = Pattern.compile(phoneNumberRegax);
        Matcher matcher1 = phoneNumberPattern.matcher(phoneNumber);
        if (!matcher1.matches()) {
            try {
                throw new PhoneNumberError();
            } catch (PhoneNumberError phoneNumberError) {
                System.out.println(phoneNumberError.getMessage());
                shipping();
            }
        }

        String z = sc.nextLine();
        int wholePrice =0;
        for(Product i : Commands.loggedBuyer.getShoppingBasket())
            wholePrice += i.getPrice();
        if (wholePrice < Commands.loggedBuyer.getCredit()){
            try{
                throw new InsufficientMoneyError();
            } catch (InsufficientMoneyError insufficientMoneyError){
                System.out.println(insufficientMoneyError.getMessage());
                shipping();
            }
        }
        else{
            System.out.println("please fill the following information.");
            System.out.println("factor ID:");

            int factorID = 0;
            try{
                factorID = sc.nextInt();
            }
            catch(InputMismatchException error){
                System.out.println("invalid input");
                sc = new Scanner(System.in);
                shipping();
            }
            sc.nextLine();

            System.out.println("date:");
            String date = sc.nextLine();
            System.out.println("is it received?");
            System.out.println("1-yes     2-no");

            int t = 0;
            try{
                t = sc.nextInt();
            }
            catch(InputMismatchException error){
                System.out.println("invalid input");
                sc = new Scanner(System.in);
                shipping();
            }
            sc.nextLine();

            boolean isReceived = false;
            if(t==1)
                isReceived = true;
            else if(t==2)
                isReceived=false;
            else {
                System.out.println("wrong input");
                shipping();
            }
            // dorost kardane factor va add kardan be sabegheye moshtari va forushande
            BuyingFactor tempBuyingFactor = new BuyingFactor(factorID, date, wholePrice, Commands.loggedBuyer.getShoppingBasket(),
                    isReceived);
            SellingFactor tempSellingFactor = new SellingFactor(factorID, date, wholePrice, Commands.loggedBuyer.getShoppingBasket(),
                    Commands.loggedBuyer.getFirstName(),isReceived);
            Commands.loggedBuyer.getBuyingFactors().add(tempBuyingFactor);
            Commands.loggedSeller.getSellingFactors().add(tempSellingFactor);
            Commands.loggedBuyer.setCredit(Commands.loggedBuyer.getCredit() - wholePrice);
            System.out.println("shipping done");
        }
    } // gereftane etelaat va ersale kharid

    static void editShoppingBasket (){
        for (Product i : Commands.loggedBuyer.getShoppingBasket()){
            System.out.println(i.toString());
            System.out.println("1-keep   2-delete");
            int x = 0;
            try{
                x = sc.nextInt();
            }
            catch(InputMismatchException error){
                System.out.println("invalid input");
                sc = new Scanner(System.in);
                showBuyersCommands();
            }
            sc.nextLine();
            if (x==2){
                Commands.loggedBuyer.getShoppingBasket().remove(i);
            }
            else if (x>2)
                showBuyersCommands();
        }
    }

    static void score(){
        int factorNumber = 1;
        System.out.println("which item do you want to score?");
        for(BuyingFactor i:Commands.loggedBuyer.getBuyingFactors()) {
            int itemNumber = 1;
            for (Product j : i.getBoughtProducts()) {
                int number = factorNumber*10+itemNumber;
                System.out.println(number + "-" + j.toString());
                itemNumber++;
            }
            factorNumber++;
        }
        // dahgane adade mahsol shomareye factore an va yekan shomareye item dar factor ast
        int x = 0;
        try{
            x = sc.nextInt();
        }
        catch(InputMismatchException error){
            System.out.println("invalid input");
            sc = new Scanner(System.in);
            score();
        }
        sc.nextLine();

        if (x > Product.getProductsList().size()){
            System.out.println("there is no such product");
            score();
        }

        System.out.print("score: ");
        int score = 0;
        try{
            score = sc.nextInt();
        }
        catch(InputMismatchException error){
            System.out.println("invalid input");
            sc = new Scanner(System.in);
            score();
        }
        sc.nextLine();

        Score newScore = new Score(Commands.loggedBuyer, score, Commands.loggedBuyer.getBuyingFactors().get(x/10).getBoughtProducts().get(x%10));
        Commands.loggedBuyer.getBuyingFactors().get(x/10).getBoughtProducts().get(x%10).getScoresList().add(newScore);
        // miangin gereftan az hameye score ha
        int scoresSum = 0;
        for(Score i:Commands.loggedBuyer.getBuyingFactors().get(x/10).getBoughtProducts().get(x%10).getScoresList())
            scoresSum += i.getScore();
        int averageScore = scoresSum/Commands.loggedBuyer.getBuyingFactors().get(x/10).getBoughtProducts().get(x%10).getScoresList().size();
        Commands.loggedBuyer.getBuyingFactors().get(x/10).getBoughtProducts().get(x%10).setAverageScore(averageScore);
        System.out.println("the average score was updated");
    } // emtiaz dadan

    public static void comment(Product commentedProduct){
        System.out.println("please fill the following information.");
        System.out.print("username: ");
        String username = sc.nextLine();
        System.out.print("comment: ");
        String comment = sc.nextLine();
        Buyer commentingUser = null;
        for(Buyer i:Buyer.getBuyersList())
            if(i.getUserName().equals(username)) {
                commentingUser = i;
                break;
            }
        Boolean haveBought = false;
        for(BuyingFactor i:commentingUser.getBuyingFactors())
            for(Product j: i.getBoughtProducts())
                if(commentedProduct.getProductID() == j.getProductID())
                    haveBought = true;
        Comment newComment = new Comment(commentingUser, commentedProduct, comment, haveBought);
        Admin.getCommentsRequests().add(newComment);
        System.out.println("the request was sent to the admin.");
    } // comment gozashtan
}

