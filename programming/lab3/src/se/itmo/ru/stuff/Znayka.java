package se.itmo.ru.stuff;

public class Znayka extends Entity implements Professor {
    public Znayka(){
        this.dna = 11;
        this.name = Types.PROFESSOR.getType() + " Знайка";
        this.friendliness = (int) (Math.random() * 9 + 1);
    }
    @Override
    public String interests() {
        return "Луна";
    }

    @Override
    public String favQuote(){
        return "Истина рождается в споре.";
    }
}
