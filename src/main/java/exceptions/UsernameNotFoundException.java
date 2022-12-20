package main.java.exceptions;

public class UsernameNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "nome de usuário inexistente";
    }
}
