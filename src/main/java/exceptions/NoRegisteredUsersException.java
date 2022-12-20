package main.java.exceptions;

public class NoRegisteredUsersException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Não há nenhum usuário cadastrado.\nPor favor efetue o cadastro antes de continuar.";
    }
}
