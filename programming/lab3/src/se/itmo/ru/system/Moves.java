package se.itmo.ru.system;

import se.itmo.ru.stuff.*;

class Moves {
    static boolean isFlewAway(MoonStone m, Entity n){
        if(m.isFly()){
            if (n.getLucky() < 8){
                System.out.println("\nПо неосторожности " + n.getName() + " " + m.name + " унёсся на Луну.");
                return true;
            }else{
                System.out.println("\nНесмотря на неосторожность " + n.getName() + ", " + m.name + " не улетел.");
                return false;
            }
        }else{
            return false;
        }
    }
    static boolean getKnowledge(Entity e, MoonStone m){
        if (m.isLearned()){
            System.out.println("\n" + e.getName() + " изучил " + m.name);
            return true;
        }else{
            System.out.println("\n" + e.getName() + " не изучил " + m.name);
            return false;
        }
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

    static boolean areFriends(Entity a, Entity b){
        if (a.getFriendliness() >= 3 & b.getFriendliness() >= 3){
            System.out.println("\nПерсонажи дружат.");
            return true;
        }else{
            System.out.println("\nПерсонажи не дружат.");
            return false;
        }
    }

    static boolean areArgue(Entity a, Entity b){
        double var = Math.random();
        if ((var > 0.1 & (a.getFriendliness() >= 5 & b.getFriendliness() >= 5)) | (var > 0.5 & (a.getFriendliness() < 5 | b.getFriendliness() < 5))){
            a.changeFriendliness(-1);
            b.changeFriendliness(-1);
            System.out.println("\nПерсонажи спорят.\nПонижен уровень дружелюбности.");
            getFriendliness(a);
            getFriendliness(b);
            return true;
        }else{
            return false;
        }
    }

    static boolean areFight(Entity a, Entity b){
        double var = Math.random();
        if (((a.getFriendliness() >= 6 & b.getFriendliness() >= 6) & var > 0.05) | ((a.getFriendliness() < 6 | b.getFriendliness() < 6) & var > 0.4)){
            a.changeFriendliness(-1);
            b.changeFriendliness(-1);
            System.out.println("\nПерсонажи дерутся.\nПонижен уровень дружелюбности.");
            getFriendliness(a);
            getFriendliness(b);
            return true;
        }else{
            return false;
        }
    }

    static boolean areColleagues(Entity a, Entity b) {
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
