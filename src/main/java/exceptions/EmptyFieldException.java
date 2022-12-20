package main.java.exceptions;

public class EmptyFieldException extends RuntimeException {
    @Override
    public String getMessage() {
        return "o campo não pode ser vazio, tente novamente";
    }
}
