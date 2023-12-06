package se.itmo.ru.system;

import se.itmo.ru.stuff.*;

public class Action {
    public static void go(){
        Znayka znayka = new Znayka();
        Zvezdochkin zvezdochkin = new Zvezdochkin();
        Neznayka neznayka = new Neznayka();
        MoonStone moonStone = new MoonStone();

        System.out.println(znayka.name + " делится мыслями с " + zvezdochkin.name + ".");
        Moves.getFriendliness(znayka);
        Moves.getFriendliness(zvezdochkin);

        if (!(Moves.areFriends(znayka, zvezdochkin))){
            System.out.println("Из-за напряженных отношений они перестают проводить совместные исследования.");
        }else{

            if (!Moves.areColleagues(znayka, zvezdochkin)){
                System.out.println(znayka.name + " и " + zvezdochkin.name + " не смогли найти общий язык.");
            }else{
                Moves.upFriendliness(znayka);
                Moves.upFriendliness(zvezdochkin);
                System.out.println(znayka.name + " и " + zvezdochkin.name + " нашли общий язык и продолжают исследования.");

                if (Moves.areArgue(znayka, zvezdochkin)){
                    System.out.println("Они не теряют уважения друг к другу.");
                    Moves.upFriendliness(znayka);
                    Moves.upFriendliness(zvezdochkin);
                    System.out.println("Как любил говорить " + znayka.name + ": " + znayka.favQuote());
                }

                Moves.getInterest(znayka, znayka);
                if (!Moves.getKnowledge(znayka, moonStone)){
                    System.out.println(znayka.name + " продолжает изучать " + moonStone.name);
                }else{
                    if (Moves.isFlewAway(moonStone, neznayka)){
                        System.out.println(znayka.name + " и " + zvezdochkin.name + " продолжают исследования только со слов " + znayka.name);
                    }else{
                        System.out.println(znayka.name + " и " + zvezdochkin.name + " продолжают исследования.");
                    }
                }
            }
        }
    }
}
