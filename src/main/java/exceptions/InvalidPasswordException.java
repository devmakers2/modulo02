package main.java.exceptions;

public class InvalidPasswordException extends RuntimeException {
    @Override
    public String getMessage() {
        return "senha não corresponde à informada";
    }
}
