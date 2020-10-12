package Exceptions;

//don't have to throw or catch runtime exceptions, but we can
public class InvalidNumberException extends Exception {
    public InvalidNumberException(String message){
        super(message);
    }
}
