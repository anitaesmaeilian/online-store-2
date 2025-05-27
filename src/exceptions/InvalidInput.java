package exceptions;

public class InvalidInput extends RuntimeException{
    InvalidInput (String msg){
        super ("invalid input due to " + msg);
    }
}

