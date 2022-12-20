package main.java.exceptions;

public class UsernameNotAvailableException extends RuntimeException {
    @Override
    public String getMessage() {
        return "o nome de usuário escolhido já está sendo usado, tente novamente";
    }
}
