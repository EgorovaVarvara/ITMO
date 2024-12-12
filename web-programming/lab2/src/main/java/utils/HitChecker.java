package utils;

public class HitChecker {
    public static boolean hit(float x, float y, float r) {
        if (x < 0 && y > 0) {
            return false;
        } else if (x >= 0 && y >= 0) {
            return x <= r/2 && y <= Math.sqrt((r * r)/4 - x * x);
        } else if (x >= 0) {
            return x <= r && Math.abs(y) <= r;
        } else {
            return Math.abs(x) <= r / 2 && y >= -x - r / 2;
        }
    }
}
