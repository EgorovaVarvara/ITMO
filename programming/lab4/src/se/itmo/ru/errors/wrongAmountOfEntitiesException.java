package se.itmo.ru.errors;

import se.itmo.ru.stuff.Entity;

import java.util.Queue;

public class wrongAmountOfEntitiesException extends Exception{
    private Queue<Entity> entities;
    public wrongAmountOfEntitiesException(String message, Throwable cause, Queue<Entity> entities){
        super(message, cause);
        this.entities = entities;
    }
    public wrongAmountOfEntitiesException(String message){
        super(message);
    }
}
