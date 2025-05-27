package exceptions;

public class InsufficientMoneyError extends InvalidPurchase {

    InsufficientMoneyError(String msg) {
        super(msg);
    }

    public InsufficientMoneyError () {
        super("insufficient money");
    }
}
