package main.java.exceptions;

public class UserNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "nome de usuário inexistente";
    }
}
