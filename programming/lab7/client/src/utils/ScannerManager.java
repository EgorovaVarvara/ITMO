package utils;

import java.util.Scanner;

public class ScannerManager {
    public static Scanner userScanner = new Scanner(System.in);

    public static Scanner getUserScanner() {
        return userScanner;
    }
}
