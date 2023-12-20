package se.itmo.ru.stuff;

public interface Interaction {
    void toTalk(Entity e1, Entity e2);
    void makeConclusion(Entity e, String message);
}
