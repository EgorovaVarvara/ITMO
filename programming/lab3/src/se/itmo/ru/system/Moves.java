package se.itmo.ru.system;

import se.itmo.ru.stuff.*;

class Moves {
    static boolean isFlewAway(MoonStone m, Neznayka n){
        if(m.isFly()){
            if (n.lucky() < 8){
                System.out.println("\nПо неосторожности " + n.name + " " + m.name + " унёсся на Луну.");
                return true;
            }else{
                System.out.println("\nНесмотря на неосторожность " + n.name + ", " + m.name + " не улетел.");
                return false;
            }
        }else{
            return false;
        }
    }
    static boolean getKnowledge(Entity e, MoonStone m){
        if (m.isLearned()){
            System.out.println("\n" + e.name + " изучил " + m.name);
            return true;
        }else{
            System.out.println("\n" + e.name + " не изучил " + m.name);
            return false;
        }
    }
    static void getInterest(Entity a, Professor b){
        System.out.println("\n" + a.name + " имеет интерес: " + b.interests());
    }
    static void getFriendliness(Entity a){
        System.out.println("\nУровень дружелюбности " + a.name + ": " + a.friendliness);
    }
    static void upFriendliness(Entity a){
        a.friendliness += 1;
        System.out.println("\nПовышен уровень дружелюбности.");
        getFriendliness(a);
    }

    static boolean areFriends(Entity a, Entity b){
        if (a.friendliness >= 3 & b.friendliness >= 3){
            System.out.println("\nПерсонажи дружат.");
            return true;
        }else{
            System.out.println("\nПерсонажи не дружат.");
            return false;
        }
    }

    static boolean areArgue(Entity a, Entity b){
        double var = Math.random();
        if ((var > 0.1 & (a.friendliness >= 5 & b.friendliness >= 5)) | (var > 0.5 & (a.friendliness < 5 | b.friendliness < 5))){
            a.friendliness--;
            b.friendliness--;
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
        if (((a.friendliness >= 6 & b.friendliness >= 6) & var > 0.05) | ((a.friendliness < 6 | b.friendliness < 6) & var > 0.4)){
            a.friendliness--;
            b.friendliness--;
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
                if (!areFriends(a, b)) {
                    return false;
                } else {
                    return true;
                }
            }else{
                return true;
            }
        }else{
            return true;
        }
    }
}
