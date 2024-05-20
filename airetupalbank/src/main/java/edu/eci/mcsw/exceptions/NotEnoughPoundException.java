package edu.eci.mcsw.exceptions;

public class NotEnoughPoundException extends RuntimeException{
    public NotEnoughPoundException(){
        super("Not Enough Pounds inside the account");
    }
}
