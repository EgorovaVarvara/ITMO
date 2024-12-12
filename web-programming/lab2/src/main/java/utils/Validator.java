package utils;

public class Validator {
    public static boolean validateX(float x) {
        return x >= -3 && x <= 5;
    }

    public static boolean validateY(float y) {
        return y >= -3 && y <= 5;
    }

    public static boolean validateR(float r) {
        return r >= 1 && r <= 3;
    }
}
