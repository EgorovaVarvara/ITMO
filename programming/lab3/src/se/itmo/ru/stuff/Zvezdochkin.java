package se.itmo.ru.stuff;

public class Zvezdochkin extends Entity implements Professor {
    public Zvezdochkin(){
        super.setFriendliness((int) (Math.random() * 9 + 1));
        super.setDna(12);
        super.setName(Types.PROFESSOR.getType() + " Звёздочкин");
        super.setInterest("Луна");
        super.setFavQuote("Силён не тот кто не упал, а тот, кто падал и вставал.");
    }
    @Override
    public String learn(){
        return " изучает ";
    }
}
