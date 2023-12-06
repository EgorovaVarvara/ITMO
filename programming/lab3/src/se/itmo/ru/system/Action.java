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
        int friendliness1 = znayka.friendliness;
        int friendliness2 = zvezdochkin.friendliness;

        System.out.println(name1 + " делится мыслями с " + name2 + ".");
        Moves.getFriendliness(znayka);
        Moves.getFriendliness(zvezdochkin);

        if (!(Moves.areFriends(friendliness1, friendliness2))){
            System.out.println("Из-за напряженных отношений они перестают проводить совместные исследования.");
        }else{

            if (!Moves.areColleagues(friendliness1, friendliness2)){
                System.out.println(name1 + " и " + name2 + " не смогли найти общий язык.");
            }else{
                friendliness1+=1;
                friendliness2+=1;
                Moves.upFriendliness(znayka);
                Moves.upFriendliness(zvezdochkin);
                System.out.println(name1 + " и " + name2 + " нашли общий язык и продолжают исследования.");

                if (Moves.areArgue(friendliness1, friendliness2)){
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
