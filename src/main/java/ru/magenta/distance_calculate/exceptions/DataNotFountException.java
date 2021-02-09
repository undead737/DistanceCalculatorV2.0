package ru.magenta.distance_calculate.exceptions;

public class DataNotFountException extends Exception{
    public DataNotFountException() {
        super("not found");
    }

    public DataNotFountException(String message) {
        super(message + " not found");
    }
}
