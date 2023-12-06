package se.itmo.ru.system;

import se.itmo.ru.stuff.*;

public class Action {
    public static void go(){
        Znayka znayka = new Znayka();
        Zvezdochkin zvezdochkin = new Zvezdochkin();
        Neznayka neznayka = new Neznayka();
        MoonStone moonStone = new MoonStone();
        String name1 = znayka.name;
        String name2 = zvezdochkin.name;

        System.out.println(name1 + " делится мыслями с " + name2 + ".");
        Moves.getFriendliness(znayka);
        Moves.getFriendliness(zvezdochkin);

        if (!(Moves.areFriends(znayka, zvezdochkin))){
            System.out.println("Из-за напряженных отношений они перестают проводить совместные исследования.");
        }else{

            if (!Moves.areColleagues(znayka, zvezdochkin)){
                System.out.println(name1 + " и " + name2 + " не смогли найти общий язык.");
            }else{
                Moves.upFriendliness(znayka);
                Moves.upFriendliness(zvezdochkin);
                System.out.println(name1 + " и " + name2 + " нашли общий язык и продолжают исследования.");

                if (Moves.areArgue(znayka, zvezdochkin)){
                    System.out.println("Они не теряют уважения друг к другу.");
                    Moves.upFriendliness(znayka);
                    Moves.upFriendliness(zvezdochkin);
                    System.out.println("Как любил говорить " + name1 + ": " + znayka.favQuote());
                }

                Moves.getInterest(znayka, znayka);
                if (!Moves.getKnowledge(znayka, moonStone)){
                    System.out.println(name1 + " продолжает изучать " + moonStone.name);
                }else{
                    if (Moves.isFlewAway(moonStone, neznayka)){
                        System.out.println(name1 + " и " + name2 + " продолжают исследования только со слов " + name1);
                    }else{
                        System.out.println(name1 + " и " + name2 + " продолжают исследования.");
                    }
                }
            }
        }
    }
}
