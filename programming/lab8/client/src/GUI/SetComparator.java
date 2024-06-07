package GUI;

import baseClasses.MusicBand;

import java.util.HashSet;

public class SetComparator {
    public static boolean compare(HashSet<MusicBand> a, HashSet<MusicBand> b) {
        if (a.size() != b.size()) return false;
        boolean flag = false;
        for (MusicBand m1 : b) {
            boolean check = false;
            for (MusicBand m2 : a) {
                if (m1.equals(m2)){
                    check = true;
                    flag = true;
                    break;
                }
            }
            if (!check) flag = false;
        }
        for (MusicBand m1 : a){
            boolean check = false;
            for (MusicBand m2 : b){
                if (m2.equals(m1)){
                    check = true;
                    flag = true;
                    break;
                }
            }
            if (!check) flag = false;
        }
        return flag;
    }
}
