package se.itmo.ru.stuff;
public class Neznayka extends Entity implements Simp{
    public Neznayka(){
        super.setDna(13);
        super.setName(Types.SIMP.getType() + " Незнайка");
        super.setLucky((int) (Math.random() * 9 + 1));
    }

    @Override
    public String play() {
        return " балуется.";
    }

}
