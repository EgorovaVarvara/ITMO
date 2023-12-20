package se.itmo.ru.errors;

public class sameEntityException extends RuntimeException{
    private String entity;
    public sameEntityException(String message, Throwable cause, String entity){
        super(message, cause);
        this.entity = entity;
    }
    public sameEntityException(String message){
        super(message);
    }
}
