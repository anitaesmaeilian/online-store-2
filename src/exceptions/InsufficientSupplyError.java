package exceptions;

public class InsufficientSupplyError extends InvalidPurchase {

    InsufficientSupplyError(String msg) {
        super(msg);
    }

    public InsufficientSupplyError () {
        super("insufficient supply");
    }
}
