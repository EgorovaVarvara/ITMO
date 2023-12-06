package se.itmo.ru.stuff;

public class Zvezdochkin extends Entity implements Professor {
    public Zvezdochkin(){
        this.dna = 12;
        this.name = Types.PROFESSOR.getType() + " Звездочкин";
        this.friendliness = (int) (Math.random() * 9 + 1);
    }
    @Override
    public String interests() {
        return "Луна";
    }

    @Override
    public String favQuote(){
        return "Силён не тот, кто не упал, а тот, кто падал и вставал.";
    }
}
