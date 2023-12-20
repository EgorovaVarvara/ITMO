package se.itmo.ru.system;

import se.itmo.ru.errors.*;
import se.itmo.ru.stuff.*;

import java.util.Queue;

class Moves {
    protected static class Check{
        static void checkAmountOfEntities(Queue<Entity> q) throws WrongAmountOfEntitiesException {
            int len = q.size();
            if (len > 3){
                throw new WrongAmountOfEntitiesException("WrongAmountOfEntitiesException: Слишком много сущностей!");
            }else if (len < 3){
                throw new WrongAmountOfEntitiesException("WrongAmountOfEntitiesException: Неодстаточно сущностей!");
            }
        }
        static boolean isFlewAway(MoonStone m, Entity e){
            if(m.getFlyable()){
                if (e.getLucky() < 8){
                    System.out.println("\nПо неосторожности " + e.getName() + " " + m.getName() + " унёсся на Луну.");
                    return true;
                }else{
                    System.out.println("\nНесмотря на неосторожность " + e.getName() + ", " + m.getName() + " не улетел.");
                    return false;
                }
            }else{
                return false;
            }
        }
        static boolean getKnowledge(Entity e, MoonStone m){
            if (m.getDegreeOfKnowledge()){
                System.out.println("\n" + e.getName() + " изучил " + m.getName());
                return true;
            }else{
                System.out.println("\n" + e.getName() + " не изучил " + m.getName());
                return false;
            }
        }
        static boolean areFriends(Entity a, Entity b) throws SameEntityException {
            if (a.equals(b)) {
                throw new SameEntityException("SameEntityException: Сущность не может дружить сама с собой!");
            }
            if (a.getFriendliness() >= 3 & b.getFriendliness() >= 3){
                System.out.println("\nПерсонажи дружат.");
                return true;
            }else{
                System.out.println("\nПерсонажи не дружат.");
                return false;
            }
        }
        static boolean areArgue(Entity a, Entity b) throws SameEntityException {
            if (a.equals(b)) {
                throw new SameEntityException("SameEntityException: Сущность не может спорить сама с собой!");
            }
            double var = Math.random();
            if ((var > 0.1 & (a.getFriendliness() >= 5 & b.getFriendliness() >= 5)) | (var > 0.5 & (a.getFriendliness() < 5 | b.getFriendliness() < 5))){
                a.changeFriendliness(-1);
                b.changeFriendliness(-1);
                System.out.println("\nПерсонажи спорят.\nПонижен уровень дружелюбности.");
                Doing.getFriendliness(a);
                Doing.getFriendliness(b);
                return true;
            }else{
                return false;
            }
        }
        static boolean areFight(Entity a, Entity b) throws SameEntityException {
            if (a.equals(b)) {
                throw new SameEntityException("SameEntityException: Сущность не может драться сама с собой!");
            }
            double var = Math.random();
            if (((a.getFriendliness() >= 6 & b.getFriendliness() >= 6) & var > 0.05) | ((a.getFriendliness() < 6 | b.getFriendliness() < 6) & var > 0.4)){
                a.changeFriendliness(-2);
                b.changeFriendliness(-2);
                System.out.println("\nПерсонажи дерутся.\nПонижен уровень дружелюбности.");
                Doing.getFriendliness(a);
                Doing.getFriendliness(b);
                return true;
            }else{
                return false;
            }
        }
        static boolean areColleagues(Entity a, Entity b) throws SameEntityException {
            if (a.equals(b)) {
                throw new SameEntityException("SameEntityException: Сущность не может сотрудничать сама с собой!");
            }
            if (areArgue(a, b)) {
                if (areFight(a, b)) {
                    return areFriends(a, b);
                }else{
                    return true;
                }
            }else{
                return true;
            }
        }
    }


    protected static class Doing{
        static void getKnowleges(Entity n){
            System.out.println(n.getName() + " знает " + n.getSciences());
        }

        static void getInterest(Entity a){
            System.out.println("\n" + a.getName() + " имеет интерес: " + a.getInterest());
        }
        static void getFriendliness(Entity a){
            System.out.println("\nУровень дружелюбности " + a.getName() + ": " + a.getFriendliness());
        }
        static void upFriendliness(Entity a){
            a.changeFriendliness(1);
            System.out.println("\nПовышен уровень дружелюбности.");
            getFriendliness(a);
        }
    }

}
