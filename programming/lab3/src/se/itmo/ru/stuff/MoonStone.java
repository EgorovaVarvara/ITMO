package se.itmo.ru.stuff;

public class MoonStone {
    public String name = "Лунный " + Types.STONE.getType();
    public boolean isLearned(){
        return (Math.random() >= 0.2);
    }
    public boolean isFly(){
        return (Math.random() >= 0.3);
    }
}
