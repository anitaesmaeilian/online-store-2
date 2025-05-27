package users.seller;


import files.*;
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
import unrelated.Menu;
import unrelated.factor.BuyingFactor;
import users.admin.Admin;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class SellerCommands implements Serializable{
    static Scanner sc = new Scanner(System.in);
    public static void showSellersCommands() throws IOException {
        // printe ekhtiarat va gereftane shomareye dastor
        System.out.println("1-add product request     2-remove product request");
        System.out.println("3-edit product request     4-change personal info");
        System.out.println("5-show on sale products     6-log out");
        int x = 0;
        try{
            x = sc.nextInt();
        }
        catch(InputMismatchException error){
            System.out.println("invalid input");
            sc = new Scanner(System.in);
            showSellersCommands();
        }
        sc.nextLine();
        // ejraye dastor bar asase shomare
        switch(x) {
            case 1:
                addProductRequest();
                break;
            case 2:
                removeProductRequest();
                break;
            case 3:
                editProductRequest();
                break;
            case 4:
                changeSellersInfo(Commands.loggedSeller);
                break;
            case 5:
                showOnSaleProducts();
                break;
            case 6:
                Commands.loggedSeller = null;
                Menu.loggedIn = false;
                break;
            default:
                showSellersCommands();
                break;
        }
    } // neshan dadan va ejraye dastorate forushande
    static void removeProductRequest() {
        while(true) {
            System.out.println("which product are you going to remove?");
            for (Product i : Commands.loggedSeller.getOnSaleProducts()) {
                int number = 1;
                System.out.println(i + "-" + i.getName());
                number++;
            }
            int x = 0;
            try{
                x = sc.nextInt();
            }
            catch(InputMismatchException error){
                System.out.println("invalid input");
                sc = new Scanner(System.in);
                removeProductRequest();
            }
            sc.nextLine();

            if ( x > Admin.getProductsRemovingRequests().size()) {
                System.out.println("there is no such product to be removed");
                removeProductRequest();
            }

            if (x == 0)
                break;
            System.out.println(Commands.loggedSeller.getOnSaleProducts().get(x - 1).toString());
            Admin.getProductsRemovingRequests().add(Commands.loggedSeller.getOnSaleProducts().get(x - 1));
            System.out.println("the request was sent.");
            System.out.println("type the number 0 when you're done.");
        }

    } // darkhaste hazf kardane kala


    static void addProductRequest() throws IOException  {
        System.out.println("What product are you going to add?");
        System.out.println("1-mobile  2-laptop  3-dress  4-shoes  5-tv  6-refrigerator  7-stove  8-food");

        int x = 0;
        try{
            x = sc.nextInt();
        }
        catch(InputMismatchException error){
            System.out.println("invalid input");
            sc = new Scanner(System.in);
            addProductRequest();
        }
        sc.nextLine();

        if (x>8){
            System.out.println("bad input");
            addProductRequest();
        }

        askProductsInfo(x ,1);
        System.out.println("the request was sent.");
    } // darkhaste ezafe kardane kala

    static void editProductRequest() throws IOException {
        System.out.println("which product are you going to edit?");
        for(Product i:Commands.loggedSeller.getOnSaleProducts()){
            int number=1;
            System.out.println(i+"-"+i.getName());
            number++;
        }

        int x = 0;
        try{
            x = sc.nextInt();
        }
        catch(InputMismatchException error){
            System.out.println("invalid input");
            sc = new Scanner(System.in);
            editProductRequest();
        }
        sc.nextLine();

        if (x>Admin.getProductsEditRequests().size()){
            System.out.println("there is no such product to be edited");
            editProductRequest();
        }

        System.out.println(Commands.loggedSeller.getOnSaleProducts().get(x-1));
        // gereftane etelaate jadide mahsol bar asase noe mahsol
        if(Commands.loggedSeller.getOnSaleProducts().get(x-1) instanceof Mobile)
            askProductsInfo(1, 0);
        else if(Commands.loggedSeller.getOnSaleProducts().get(x-1) instanceof Laptop)
            askProductsInfo(2, 0);
        else if(Commands.loggedSeller.getOnSaleProducts().get(x-1) instanceof Dress)
            askProductsInfo(3, 0);
        else if(Commands.loggedSeller.getOnSaleProducts().get(x-1) instanceof Shoes)
            askProductsInfo(4, 0);
        else if(Commands.loggedSeller.getOnSaleProducts().get(x-1) instanceof TV)
            askProductsInfo(5, 0);
        else if(Commands.loggedSeller.getOnSaleProducts().get(x-1) instanceof Refrigerator)
            askProductsInfo(6, 0);
        else if(Commands.loggedSeller.getOnSaleProducts().get(x-1) instanceof Stove)
            askProductsInfo(7, 0);
        else if(Commands.loggedSeller.getOnSaleProducts().get(x-1) instanceof Food)
            askProductsInfo(8, 0);
        System.out.println("the request was sent.");
    } // darkhaste edit kardane kala

    static void askProductsInfo(int x, int y) throws IOException {
        int productID = 0;
        if(y==1) {
            System.out.print("product ID: ");
            try{
                productID = sc.nextInt();
            }
            catch(InputMismatchException error){
                System.out.println("invalid input");
                sc = new Scanner(System.in);
                askProductsInfo(x, y);
            }
            sc.nextLine();

            for (Product i : Product.getProductsList())
                if (productID == i.getProductID()) {
                    System.out.print("please enter another ID:");
                    productID = sc.nextInt();
                }
        } // taghire username hengame edit emkan pazir nist
        System.out.print("name: ");
        String name = sc.nextLine();
        System.out.print("brand: ");
        String brand = sc.nextLine();
        System.out.print("price: ");
        int price = 0;
        try{
            price = sc.nextInt();
        }
        catch(InputMismatchException error){
            System.out.println("invalid input");
            sc = new Scanner(System.in);
            askProductsInfo(x, y);
        }
        sc.nextLine();
        System.out.print("description: ");
        String description = sc.nextLine();

        if(x==1 || x==2){ // gereftane etelaate kalaye digital
            System.out.print("memory capacity: ");
            int memoryCapacity = 0;
            try{
                memoryCapacity = sc.nextInt();
            }
            catch(InputMismatchException error){
                System.out.println("invalid input");
                sc = new Scanner(System.in);
                askProductsInfo(x, y);
            }
            sc.nextLine();
            System.out.print("ram: ");
            int ram = 0;
            try{
                ram = sc.nextInt();
            }
            catch(InputMismatchException error){
                System.out.println("invalid input");
                sc = new Scanner(System.in);
                askProductsInfo(x, y);
            }
            sc.nextLine();
            System.out.print("operating system: ");
            String operatingSystem = sc.nextLine();
            System.out.print("weight: ");
            int weight = 0;
            try{
                weight = sc.nextInt();
            }
            catch(InputMismatchException error){
                System.out.println("invalid input");
                sc = new Scanner(System.in);
                askProductsInfo(x, y);
            }
            sc.nextLine();
            System.out.print("dimensions: ");
            String dimensions = sc.nextLine();

            if(x==1) { // gereftane etelaate mobile
                System.out.print("simcards number: ");
                int simcardsNumber = 0;
                try{
                    simcardsNumber = sc.nextInt();
                }
                catch(InputMismatchException error){
                    System.out.println("invalid input");
                    sc = new Scanner(System.in);
                    askProductsInfo(x, y);
                }
                sc.nextLine();
                System.out.print("camera resolution: ");
                int cameraResolution = 0;
                try{
                    cameraResolution = sc.nextInt();
                }
                catch(InputMismatchException error){
                    System.out.println("invalid input");
                    sc = new Scanner(System.in);
                    askProductsInfo(x, y);
                }
                sc.nextLine();
                Mobile newMobile = new Mobile(productID, name, brand, price, Commands.loggedSeller, description,
                        memoryCapacity, ram, operatingSystem, weight, dimensions, simcardsNumber, cameraResolution);
                if(y==0) { // add kardane mobile be darkhasti haye edite kalaye modir
                    Admin.getProductsEditRequests().add(newMobile);

                    FileOutputStream productsAdd = new FileOutputStream("Saved Data/Users/Admin/requests/Products requests/edit requests.txt", true) ;
                    ObjectOutputStream outs = new ObjectOutputStream(productsAdd);
                    outs.writeObject(newMobile);
                    outs.flush();
                    productsAdd.close();
                    outs.close();
                }
                else if(y == 1) {  // add kardane mobile be darkhasti haye ezafe kardane kalaye modir
                    Admin.getProductsAddingRequests().add(newMobile);

                    FileOutputStream productsAdd = new FileOutputStream("Saved Data/Users/Admin/requests/Products requests/add requests.txt", true) ;
                    ObjectOutputStream outs = new ObjectOutputStream(productsAdd);
                    outs.writeObject(newMobile);
                    outs.flush();
                    productsAdd.close();
                    outs.close();
                }

            } else if(x==2){ // gereftane etelaate laptop
                System.out.print("cpu type: ");
                String cpuType = sc.nextLine();
                System.out.println("is it for gaming?");
                System.out.println("1-yes     2-no");
                int z = 0;
                try{
                    z = sc.nextInt();
                }
                catch(InputMismatchException error){
                    System.out.println("invalid input");
                    sc = new Scanner(System.in);
                    askProductsInfo(x, y);
                }
                sc.nextLine();
                Boolean gamingLaptop = null;
                if(z==1) // sakhtane laptop ba ghabeliate gaming
                    gamingLaptop = true;
                else if(z==2) // sakhtane laptop bedone ghabeliate gaming
                    gamingLaptop = false;
                else{
                    System.out.println("you have to choose either yes or no");
                    askProductsInfo(x, y);
                }

                Laptop newLaptop = new Laptop(productID, name, brand, price, Commands.loggedSeller, description,
                        memoryCapacity, ram, operatingSystem, weight, dimensions, cpuType, gamingLaptop);
                if(y==0) {  // add kardane laptop be darkhasti haye edite kalaye modir
                    Admin.getProductsEditRequests().add(newLaptop);
                    FileOutputStream productsAdd = new FileOutputStream("Saved Data/Users/Admin/requests/Products requests/edit requests.txt", true) ;
                    ObjectOutputStream outs = new ObjectOutputStream(productsAdd);
                    outs.writeObject(newLaptop);
                    outs.flush();
                    productsAdd.close();
                    outs.close();
                }
                else if(y == 1) { // add kardane laptop be darkhasti haye ezafe kardane kalaye modir
                    Admin.getProductsAddingRequests().add(newLaptop);
                    FileOutputStream productsAdd = new FileOutputStream("Saved Data/Users/Admin/requests/Products requests/add requests.txt", true) ;
                    ObjectOutputStream outs = new ObjectOutputStream(productsAdd);
                    outs.writeObject(newLaptop);
                    outs.flush();
                    productsAdd.close();
                    outs.close();
                }
            }
        }
        else if(x==3 || x==4){ // gereftane etelaate poshak
            System.out.print("producer country: ");
            String producerCountry = sc.nextLine();
            System.out.print("material: ");
            String material = sc.nextLine();
            if(x==3){ // gereftane etelaate lebas
                System.out.print("what type of dress is it?(write in capital) ");
                String clothesType = sc.nextLine();
                System.out.println("size: ");
                int size = 0;
                try{
                    size = sc.nextInt();
                }
                catch(InputMismatchException error){
                    System.out.println("invalid input");
                    sc = new Scanner(System.in);
                    askProductsInfo(x, y);
                }
                sc.nextLine();

                Dress newDress = new Dress(productID, name, brand, price, Commands.loggedSeller, description, producerCountry,
                        material, Dress.type.valueOf(clothesType), size);
                if(y==0) {  // add kardane lebas be darkhasti haye edite kalaye modir
                    Admin.getProductsEditRequests().add(newDress);
                    FileOutputStream productsAdd = new FileOutputStream("Saved Data/Users/Admin/requests/Products requests/edit requests.txt", true) ;
                    ObjectOutputStream outs = new ObjectOutputStream(productsAdd);
                    outs.writeObject(newDress);
                    outs.flush();
                    productsAdd.close();
                    outs.close();
                }
                else if(y == 1) { // add kardane lebas be darkhasti haye ezafe kardane kalaye modir
                    Admin.getProductsAddingRequests().add(newDress);
                    FileOutputStream productsAdd = new FileOutputStream("Saved Data/Users/Admin/requests/Products requests/add requests.txt", true) ;
                    ObjectOutputStream outs = new ObjectOutputStream(productsAdd);
                    outs.writeObject(newDress);
                    outs.flush();
                    productsAdd.close();
                    outs.close();
                }
            }else if(x==4){ // gereftane etelaate kafsh
                System.out.print("what type of shoes is it?(write in capital) ");
                String shoesType = sc.nextLine();
                System.out.println("size: ");
                int size = 0;
                try{
                    size = sc.nextInt();
                }
                catch(InputMismatchException error){
                    System.out.println("invalid input");
                    sc = new Scanner(System.in);
                    askProductsInfo(x, y);
                }
                sc.nextLine();

                Shoes newShoes = new Shoes(productID, name, brand, price, Commands.loggedSeller, description, producerCountry,
                        material, Shoes.type.valueOf(shoesType), size);
                if(y==0) { // add kardane kafsh be darkhasti haye edite kalaye modir
                    Admin.getProductsEditRequests().add(newShoes);
                    FileOutputStream productsAdd = new FileOutputStream("Saved Data/Users/Admin/requests/Products requests/edit requests.txt", true) ;
                    ObjectOutputStream outs = new ObjectOutputStream(productsAdd);
                    outs.writeObject(newShoes);
                    outs.flush();
                    productsAdd.close();
                    outs.close();
                }
                else if(y == 1) { // add kardane lebas be darkhasti haye ezafe kardane kalaye modir
                    Admin.getProductsAddingRequests().add(newShoes);
                    FileOutputStream productsAdd = new FileOutputStream("Saved Data/Users/Admin/requests/Products requests/add requests.txt", true) ;
                    ObjectOutputStream outs = new ObjectOutputStream(productsAdd);
                    outs.writeObject(newShoes);
                    outs.flush();
                    productsAdd.close();
                    outs.close();
                }
            }
        }
        else if(x==5 || x==6 || x==7){ // gereftane etelaate kalaye khanegi
            System.out.print("energy consumption degree: ");
            int energyConsumptionDegree = 0;
            try{
                energyConsumptionDegree = sc.nextInt();
            }
            catch(InputMismatchException error){
                System.out.println("invalid input");
                sc = new Scanner(System.in);
                askProductsInfo(x, y);
            }
            sc.nextLine();

            System.out.println("does it have guarantee?");
            System.out.println("1-yes     2-no");
            int t = 0;
            try{
                t = sc.nextInt();
            }
            catch(InputMismatchException error){
                System.out.println("invalid input");
                sc = new Scanner(System.in);
                askProductsInfo(x, y);
            }
            sc.nextLine();
            boolean tempGuarantee = false;
            if (t == 1)
                tempGuarantee = true;
            else if(t==2)
                tempGuarantee = false;
            else{
                System.out.println("you have to choose either yes or no");
                askProductsInfo(x, y);
            }

            if(x==5){ // gereftane etelaate television
                System.out.print("resolution: ");
                int resolution = 0;
                try{
                    resolution = sc.nextInt();
                }
                catch(InputMismatchException error){
                    System.out.println("invalid input");
                    sc = new Scanner(System.in);
                    askProductsInfo(x, y);
                }
                sc.nextLine();
                System.out.print("screen size: ");
                int screenSize = 0;
                try{
                    screenSize = sc.nextInt();
                }
                catch(InputMismatchException error){
                    System.out.println("invalid input");
                    sc = new Scanner(System.in);
                    askProductsInfo(x, y);
                }
                sc.nextLine();
                TV newTV = new TV(productID, name, brand, price, Commands.loggedSeller, description, energyConsumptionDegree,
                        tempGuarantee, resolution, screenSize);
                if(y==0) { // add kardane television be darkhasti haye edite kalaye modir
                    Admin.getProductsEditRequests().add(newTV);
                    FileOutputStream productsAdd = new FileOutputStream("Saved Data/Users/Admin/requests/Products requests/edit requests.txt", true) ;
                    ObjectOutputStream outs = new ObjectOutputStream(productsAdd);
                    outs.writeObject(newTV);
                    outs.flush();
                    productsAdd.close();
                    outs.close();
                }

                else if(y == 1) {// add kardane television be darkhasti haye ezafe kardane kalaye modir
                    Admin.getProductsAddingRequests().add(newTV);
                    FileOutputStream productsAdd = new FileOutputStream("Saved Data/Users/Admin/requests/Products requests/add requests.txt", true) ;
                    ObjectOutputStream outs = new ObjectOutputStream(productsAdd);
                    outs.writeObject(newTV);
                    outs.flush();
                    productsAdd.close();
                    outs.close();
                }
            } else if (x == 6) { // gereftane etelaate yakhchal
                System.out.print("capacity: ");
                int capacity = 0;
                try{
                    capacity = sc.nextInt();
                }
                catch(InputMismatchException error){
                    System.out.println("invalid input");
                    sc = new Scanner(System.in);
                    askProductsInfo(x, y);
                }
                sc.nextLine();
                System.out.print("type: ");
                String type = sc.nextLine();
                System.out.println("does it have fridge?");
                System.out.println("1-yes     2-no");
                int z = 0;
                try{
                    z = sc.nextInt();
                }
                catch(InputMismatchException error){
                    System.out.println("invalid input");
                    sc = new Scanner(System.in);
                    askProductsInfo(x, y);
                }
                sc.nextLine();

                Boolean fridge = false;
                if(z==1)
                    fridge = true;
                else if(z==2)
                    fridge=false;
                else{
                    System.out.println("you have to choose either yes or no");
                    askProductsInfo(x,y);
                }

                Refrigerator newRefrigerator = new Refrigerator(productID, name, brand, price, Commands.loggedSeller,
                        description, energyConsumptionDegree, tempGuarantee, capacity, type, fridge);
                if(y==0) { // add kardane yakhchal be darkhasti haye edite kalaye modir
                    Admin.getProductsEditRequests().add(newRefrigerator);
                    FileOutputStream productsAdd = new FileOutputStream("Saved Data/Users/Admin/requests/Products requests/edit requests.txt", true) ;
                    ObjectOutputStream outs = new ObjectOutputStream(productsAdd);
                    outs.writeObject(newRefrigerator);
                    outs.flush();
                    productsAdd.close();
                    outs.close();
                }
                else if(y == 1) { // add kardane television be darkhasti haye ezafe kardane kalaye modir
                    Admin.getProductsAddingRequests().add(newRefrigerator);

                    FileOutputStream productsAdd = new FileOutputStream("Saved Data/Users/Admin/requests/Products requests/add requests.txt", true) ;
                    ObjectOutputStream outs = new ObjectOutputStream(productsAdd);
                    outs.writeObject(newRefrigerator);
                    outs.flush();
                    productsAdd.close();
                    outs.close();
                }

            }else if(x == 7){ // gereftane etelaate gaz
                System.out.print("burners number: ");
                int burnersNumber = 0;
                try{
                    burnersNumber = sc.nextInt();
                }
                catch(InputMismatchException error){
                    System.out.println("invalid input");
                    sc = new Scanner(System.in);
                    askProductsInfo(x, y);
                }
                sc.nextLine();
                System.out.print("material: ");
                String material = sc.nextLine();
                System.out.println("does it have oven?");
                System.out.println("1-yes     2-no");
                int z = 0;
                try{
                    z = sc.nextInt();
                }
                catch(InputMismatchException error){
                    System.out.println("invalid input");
                    sc = new Scanner(System.in);
                    askProductsInfo(x, y);
                }
                sc.nextLine();
                Boolean oven = false;
                if(z==1)
                    oven=true;
                else if(z==2)
                    oven=false;
                else {
                    System.out.println("you have to choose either yes or no");
                    askProductsInfo(x,y);
                }

                Stove newStove = new Stove(productID, name, brand, price, Commands.loggedSeller, description,
                        energyConsumptionDegree, tempGuarantee, burnersNumber, material, oven);
                if(y==0) { // add kardane gaz be darkhasti haye edite kalaye modir
                    Admin.getProductsEditRequests().add(newStove);

                    FileOutputStream productsAdd = new FileOutputStream("Saved Data/Users/Admin/requests/Products requests/edit requests.txt", true) ;
                    ObjectOutputStream outs = new ObjectOutputStream(productsAdd);
                    outs.writeObject(newStove);
                    outs.flush();
                    productsAdd.close();
                    outs.close();
                }
                else if(y == 1) { // add kardane gaz be darkhasti haye ezafe kardane kalaye modir
                    Admin.getProductsAddingRequests().add(newStove);

                    FileOutputStream productsAdd = new FileOutputStream("Saved Data/Users/Admin/requests/Products requests/add requests.txt", true) ;
                    ObjectOutputStream outs = new ObjectOutputStream(productsAdd);
                    outs.writeObject(newStove);
                    outs.flush();
                    productsAdd.close();
                    outs.close();
                }
            }
        }
        else if(x==8){ // gereftane etelaate ghaza
            System.out.print("production date: ");
            String productionDate = sc.nextLine();
            System.out.print("expiration date: ");
            String expirationDate = sc.nextLine();
            Food newFood = new Food(productID, name, brand, price, Commands.loggedSeller, description, productionDate, expirationDate);
            if(y==0) { // add kardane ghaza be darkhasti haye edite kalaye modir
                Admin.getProductsEditRequests().add(newFood);
                FileOutputStream productsAdd = new FileOutputStream("Saved Data/Users/Admin/requests/Products requests/edit requests.txt", true) ;
                ObjectOutputStream outs = new ObjectOutputStream(productsAdd);
                outs.writeObject(newFood);
                outs.flush();
                productsAdd.close();
                outs.close();
            }
            else if(y == 1) { // add kardane ghaza be darkhasti haye ezafe kardane kalaye modir
                Admin.getProductsAddingRequests().add(newFood);

                FileOutputStream productsAdd = new FileOutputStream("Saved Data/Users/Admin/requests/Products requests/add requests.txt", true) ;
                ObjectOutputStream outs = new ObjectOutputStream(productsAdd);
                outs.writeObject(newFood);
                outs.flush();
                productsAdd.close();
                outs.close();
            }
        }
    } // gereftane etelaate mahsol
    static void changeSellersInfo(Seller seller) {
        Commands.changePersonalInfo(seller);
        System.out.print("company's name: ");
        String companysName = sc.nextLine();
        seller.setCompanysName(companysName);
    } // taghire etelaate forushande
    static void showOnSaleProducts(){
        for(Product i : Commands.loggedSeller.getOnSaleProducts())
            System.out.println(i.toString());
    } // forushe kala
}

// modiriat estesna random access read kardane etelaat 100 estesna 100 random khondan 300
