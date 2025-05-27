package exceptions;

public class PhoneNumberError extends InvalidInput {
    PhoneNumberError(String msg) {
        super(msg);
    }

    public PhoneNumberError() {
        super("invalid phone number");
    }

}
