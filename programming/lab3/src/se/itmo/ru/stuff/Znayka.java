package se.itmo.ru.stuff;

public class Znayka extends Entity implements Professor {
    public Znayka(){
        super.setFriendliness((int) (Math.random() * 9 + 1));
        super.setDna(11);
        super.setName(Types.PROFESSOR.getType() + " Знайка");
        super.setInterest("Луна");
        super.setFavQuote("Истина рождается в споре.");
    }
    @Override
    public String learn(){
        return " изучает ";
    }
}
