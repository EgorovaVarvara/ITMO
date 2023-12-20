import se.itmo.ru.stuff.*;
import se.itmo.ru.system.Action;

public class Main {
    public static void main(String[] args){
        Action a = new Action();
        Entity znayka = new Znayka();
        Entity zvezdochkin = new Zvezdochkin();
        Entity neznayka = new Neznayka();
        MoonStone moonStone = new MoonStone();
        Action.Settings.addCharacter(znayka);
        Action.Settings.addCharacter(zvezdochkin);
        Action.Settings.addCharacter(neznayka);
        Action.Settings.setMoonStone(moonStone);
        a.go();
    }
}
