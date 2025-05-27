package unrelated;


import com.sun.org.apache.bcel.internal.generic.GOTO;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import exceptions.EmailError;
import exceptions.PhoneNumberError;
import users.Account;
import users.admin.*;
import users.buyer.Buyer;
import users.seller.*;

import java.io.*;
import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Commands implements Serializable {
    static Scanner sc = new Scanner(System.in);
    public static Seller loggedSeller;
    public static Buyer loggedBuyer;

    static void register() throws IOException {
        // gereftane etelaate sabtenam
        System.out.println("What role are you registering for?");
        System.out.println("1-Seller     2-Buyer");

        int x = 0;
        try{
            x = sc.nextInt();
        }
        catch(InputMismatchException error){
            System.out.println("invalid input");
            sc = new Scanner(System.in);
            register();
        }
        sc.nextLine();

        System.out.println("Please fill the following information.");
        System.out.print("username: ");
        String userName = sc.nextLine();
        System.out.print("first name: ");
        String firstName = sc.nextLine();
        System.out.print("last name: ");
        String lastName = sc.nextLine();

        System.out.print("email: ");
        String email = sc.nextLine();
        String emailRegax = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern emailPattern = Pattern.compile(emailRegax);
        Matcher matcher = emailPattern.matcher(email);
        if (!matcher.matches()) {
            try {
                throw new EmailError();
            } catch (EmailError emailError) {
                System.out.println(emailError.getMessage());
                register();
            }
        }

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
                register();
            }
        }

        System.out.print("password: ");
        String password = sc.nextLine();
        // gereftane etelaate motafavete naghsh ha
        if (x==1) { // sabte nam baraye forushande
            System.out.print("company's name: ");
            String companysName = sc.nextLine();
            // check kardane tekrari nabudane username dar forushande ha
            for (Seller i : Seller.getSellersList())
                if (userName.equals(i.getUserName())) {
                    System.out.print("please enter another username:");
                    userName = sc.nextLine();
                }
            // sakhtane yek forushandeye jadid va gharar dadan dar liste darkhast haye modir
            Seller newSeller = new Seller(userName, firstName, lastName, email, phoneNumber, password, companysName);
            Admin.getSellersJoinRequests().add(newSeller);
            FileOutputStream productsAdd = new FileOutputStream("Saved Data/Users/Admin/requests/Sellers Requests/join requests.txt", true) ;
            ObjectOutputStream outs = new ObjectOutputStream(productsAdd);
            outs.writeObject(newSeller);
            outs.flush();
            productsAdd.close();
            outs.close();
        }else if(x==2) { // sabte nam baraye moshtari
            // check kardane tekrari nabudane username dar moshtari ha
            for (Buyer i : Buyer.getBuyersList())
                if (userName.equals(i.getUserName())) {
                    System.out.print("please enter another username:");
                    userName = sc.nextLine();
                }
            // sakhtane yek moshtarie jadid va gharar dadan dar liste moshtari ha
            Buyer newBuyer = new Buyer(userName, firstName, lastName, email, phoneNumber, password);
            Buyer.getBuyersList().add(newBuyer);
            File newBuyerFile = new File("Saved Data/Users/Buyers/Buyer " + String.valueOf(Buyer.getBuyersList().size()));
            newBuyerFile.mkdir();
            FileWriter buyingFactorsList = new FileWriter(newBuyerFile.getPath() + "/Buying Factors List.txt");
            FileWriter information = new FileWriter(newBuyerFile.getPath() + "/Information.txt");
            FileWriter shoppingBasket = new FileWriter(newBuyerFile.getPath() + "/Shopping Basket.txt");
            Formatter formatter = new Formatter(information);
            formatter.format("%s", newBuyer.toString());
            formatter.close();
        }
        else{
            System.out.println("you must choose your role correctly");
            register();
        }
        System.out.println("registered successfully.you have to log in in order to use your account.");
    } // sabte nam

    static void logIn() throws IOException {
        // gereftane etelaat
        System.out.println("What is your role?");
        System.out.println("1-Seller     2-Buyer     3-Admin");

        int x = 0;
        try{
            x = sc.nextInt();
        }
        catch(InputMismatchException error){
            System.out.println("invalid input");
            sc = new Scanner(System.in);
            logIn();
        }
        sc.nextLine();

        System.out.println("Please fill the following information.");
        System.out.print("username: ");
        String userName = sc.nextLine();
        System.out.print("password: ");
        String password = sc.nextLine();
        // jostojo baraye account va vorud
        if(x==1)  //vorud be accounte forushande
            for (Seller i : Seller.getSellersList()) {
                if (userName.equals(i.getUserName()) && password.equals(i.getPassword())) {
                    Menu.loggedIn = true;
                    loggedBuyer = null;
                    loggedSeller = i;
                    System.out.println("logged in successfully.");
                    System.out.println(i.toString());
                    SellerCommands.showSellersCommands();
                } else
                    System.out.println("user doesn't exist!");
            }

        else if(x==2)  //vorud be accounte kharidar
            for (Buyer i : Buyer.getBuyersList()) {
                if (userName.equals(i.getUserName()) && password.equals(i.getPassword())) {
                    Menu.loggedIn = true;
                    loggedSeller = null;
                    loggedBuyer = i;
                    System.out.println("logged in successfully.");
                    System.out.println(i.toString());
                } else
                    System.out.println("user doesn't exist!");
            }

        else if(x==3) { //vorud be accounte modir
            Menu.loggedIn=true;
            loggedSeller = null;
            loggedBuyer = null;
            System.out.println("logged in successfully.");
            System.out.println(Admin.getAdmin().toString());
            AdminCommands.showAdminsCommands();
        }
        else{
            System.out.println("you must choose your role correctly");
            logIn();
        }
    }// vorud be account

    public static void changePersonalInfo(Account account){
        System.out.print("username: ");
        String userName = sc.nextLine();
        if(loggedBuyer == null)
            for (Seller i : Seller.getSellersList())
                if (userName.equals(i.getUserName())) {
                    System.out.print("please enter another username:");
                    userName = sc.nextLine();
                }
                else if(loggedSeller == null)
                    for (Buyer j : Buyer.getBuyersList())
                        if (userName.equals(j.getUserName())) {
                            System.out.print("please enter another username:");
                            userName = sc.nextLine();
                        }
        account.setUserName(userName);
        System.out.print("first name: ");
        String firstName = sc.nextLine();
        account.setFirstName(firstName);
        System.out.print("last name: ");
        String lastName = sc.nextLine();
        account.setLastName(lastName);

        System.out.print("email: ");
        String email = sc.nextLine();
        String emailRegax = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern emailPattern = Pattern.compile(emailRegax);
        Matcher matcher = emailPattern.matcher(email);
        if (!matcher.matches()) {
            try {
                throw new EmailError();
            } catch (EmailError emailError) {
                System.out.println(emailError.getMessage());
                changePersonalInfo(account);
            }
        }
        account.setEmail(email);

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
                changePersonalInfo(account);
            }
        }
        account.setPhoneNumber(phoneNumber);

        System.out.print("password: ");
        String password = sc.nextLine();
        account.setPassword(password);
    } // taghire etelaate moshtarake se naghsh
}

