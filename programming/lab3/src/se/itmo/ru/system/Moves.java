package se.itmo.ru.system;

import se.itmo.ru.stuff.*;

class Moves {
    static boolean isFlewAway(MoonStone m, Neznayka n){
        if(m.isFly()){
            if (n.lucky() < 0.8){
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

    static boolean areFriends(int a, int b){
        if (a >= 3 & b >= 3){
            System.out.println("\nПерсонажи дружат.");
            return true;
        }else{
            System.out.println("\nПерсонажи не дружат.");
            return false;
        }
    }

    static boolean areArgue(int f1, int f2){
        double var = Math.random();
        if ((var > 0.1 & (f1 >= 5 & f2 >= 5)) | (var > 0.5 & (f1 < 5 | f2 < 5))){
            System.out.println("\nПерсонажи спорят.");
            return true;
        }else{
            return false;
        }
    }

    static boolean areFight(int f1, int f2){
        double var = Math.random();
        if (((f1 >= 6 & f1 >= 6) & var > 0.05) | ((f1 < 6 | f2 < 6) & var > 0.4)){
            System.out.println("\nПерсонажи дерутся.");
            return true;
        }else{
            return false;
        }
    }

    static boolean areColleagues(int f1, int f2) {
        if (areArgue(f1, f2)) {
            f1--;
            f2--;
            if (areFight(f1, f2)) {
                f1--;
                f2--;
                if (!areFriends(f1, f2)) {
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
