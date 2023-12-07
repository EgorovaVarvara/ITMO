package se.itmo.ru.system;

import se.itmo.ru.stuff.*;

public class Action {
    public static void go(){
        Entity znayka = new Znayka();
        Entity zvezdochkin = new Zvezdochkin();
        Entity neznayka = new Neznayka();
        MoonStone moonStone = new MoonStone();

        System.out.println(znayka.getName() + " делится мыслями с " + zvezdochkin.getName() + ".");
        Moves.getFriendliness(znayka);
        Moves.getFriendliness(zvezdochkin);

        System.out.println(znayka.getName() + ((Professor) znayka).learn() + " и " + zvezdochkin.getName() + ((Professor) zvezdochkin).learn());
        if (!(Moves.areFriends(znayka, zvezdochkin))){
            System.out.println("Из-за напряженных отношений они перестают проводить совместные исследования.");
        }else{

            if (!Moves.areColleagues(znayka, zvezdochkin)){
                System.out.println(znayka.getName() + " и " + zvezdochkin.getName() + " не смогли найти общий язык.");
            }else{
                Moves.upFriendliness(znayka);
                Moves.upFriendliness(zvezdochkin);
                System.out.println(znayka.getName() + " и " + zvezdochkin.getName() + " нашли общий язык и продолжают исследования.");

                if (Moves.areArgue(znayka, zvezdochkin)){
                    System.out.println("Они не теряют уважения друг к другу.");
                    Moves.upFriendliness(znayka);
                    Moves.upFriendliness(zvezdochkin);
                    System.out.println("Как любил говорить " + znayka.getName() + ": " + znayka.getFavQuote());
                }

                Moves.getInterest(znayka);
                if (!Moves.getKnowledge(znayka, moonStone)){
                    System.out.println(znayka.getName() + " продолжает изучать " + moonStone.name);
                }else{
                    System.out.println(neznayka.getName() + ((Simp) neznayka).play());
                    if (Moves.isFlewAway(moonStone, neznayka)){
                        System.out.println(znayka.getName() + " и " + zvezdochkin.getName() + " продолжают исследования только со слов " + znayka.getName());
                    }else{
                        System.out.println(znayka.getName() + " и " + zvezdochkin.getName() + " продолжают исследования.");
                    }
                }
            }
        }
    }
}
