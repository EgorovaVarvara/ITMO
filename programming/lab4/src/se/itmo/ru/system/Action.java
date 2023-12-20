package se.itmo.ru.system;

import se.itmo.ru.errors.*;
import se.itmo.ru.stuff.*;

import java.util.LinkedList;
import java.util.Queue;

public class Action {
    private static final Queue<Entity> entities = new LinkedList<>();
    private static MoonStone moonStone;
    public class Settings{
        public static void setMoonStone(MoonStone m){moonStone = m;}
        public static void addCharacter(Entity e){entities.add(e);}
    }



    public void go(){
        try{
            Moves.Check.checkAmountOfEntities(entities);
        }catch(WrongAmountOfEntitiesException e){
            System.err.println(e.getMessage());
            System.exit(0);
        }
        Entity znayka = entities.poll();
        Entity zvezdochkin = entities.poll();
        Entity neznayka = entities.poll();
        moonStone.setStats();

        Interaction i = new Interaction(){
            @Override
            public void toTalk(Entity e1, Entity e2){
                if (e1.equals(e2)) {
                    throw new SameEntityException("SameEntityException: Сущность не может разговаривать сама с собой!");
                }
                System.out.println(e1.getName() + " общается с " + e2.getName() + ".");
            }
            @Override
            public void makeConclusion(Entity e, String message){
                System.out.println(e.getName() + " сделал вывод, что " + message);
            }
        };



        System.out.println(znayka.getName() + " изнывает от нетерпения.");
        System.out.println(znayka.getName() + ((Professor) znayka).learn() + "невесомость.");
        System.out.println(moonStone.getEnergyInteraction() + "\n");


        System.out.println(znayka.getName() + " делится мыслями с " + zvezdochkin.getName() + ".");
        Moves.Doing.getFriendliness(znayka);
        Moves.Doing.getFriendliness(zvezdochkin);

        System.out.println(znayka.getName() + ((Professor) znayka).learn() + " и " + zvezdochkin.getName() + ((Professor) zvezdochkin).learn());
        try {
            if (!(Moves.Check.areFriends(znayka, zvezdochkin))) {
                System.out.println("Из-за напряженных отношений они перестают проводить совместные исследования.");
            } else {

                if (!Moves.Check.areColleagues(znayka, zvezdochkin)) {
                    System.out.println(znayka.getName() + " и " + zvezdochkin.getName() + " не смогли найти общий язык.");
                } else {
                    Moves.Doing.upFriendliness(znayka);
                    Moves.Doing.upFriendliness(zvezdochkin);
                    System.out.println(znayka.getName() + " и " + zvezdochkin.getName() + " нашли общий язык и продолжают исследования.");

                    if (Moves.Check.areArgue(znayka, zvezdochkin)) {
                        System.out.println("Они не теряют уважения друг к другу.");
                        Moves.Doing.upFriendliness(znayka);
                        Moves.Doing.upFriendliness(zvezdochkin);
                        System.out.println("Как любил говорить " + znayka.getName() + ": " + znayka.getFavQuote());
                    }

                    Moves.Doing.getInterest(znayka);
                    if (!Moves.Check.getKnowledge(znayka, moonStone)) {
                        System.out.println(znayka.getName() + " продолжает изучать " + moonStone.getName());
                    } else {
                        System.out.println(neznayka.getName() + ((Simp) neznayka).play());
                        if (Moves.Check.isFlewAway(moonStone, neznayka)) {
                            System.out.println(znayka.getName() + " и " + zvezdochkin.getName() + " продолжают исследования только со слов " + znayka.getName() + "\n");
                        } else {
                            System.out.println(znayka.getName() + " и " + zvezdochkin.getName() + " продолжают исследования.\n");
                        }
                        Moves.Doing.getKnowleges(zvezdochkin);
                        i.makeConclusion(zvezdochkin, moonStone.getName() + " распространён на Луне");
                        i.toTalk(zvezdochkin, znayka);
                        i.makeConclusion(znayka, "залежи лунита могут быть использованы как на Земле, так и на Луне.");
                    }
                }
            }
        }catch (SameEntityException e){
            System.err.println(e.getMessage());
        }
    }
}
