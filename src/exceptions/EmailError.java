package exceptions;

public class EmailError extends InvalidInput {
    EmailError (String msg){
        super (msg);
    }


    public EmailError(){
        super("invalid email");
    }
}
