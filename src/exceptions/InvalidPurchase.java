package exceptions;

public class InvalidPurchase extends RuntimeException {
    InvalidPurchase (String msg){
        super ("invalid purchase due to " + msg);
    }
}

