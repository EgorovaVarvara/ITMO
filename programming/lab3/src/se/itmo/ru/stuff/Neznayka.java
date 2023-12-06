package se.itmo.ru.stuff;
public class Neznayka extends Entity implements Simp{
    public Neznayka(){
        this.dna = 13;
        this.name = Types.SIMP.getType() + " Незнайка";
    }

    @Override
    public int lucky() {
        return (int) (Math.random() * 9 + 1);
    }

}
