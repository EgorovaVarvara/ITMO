package console;

import java.util.Scanner;

public class Console implements ReaderWriter{
    private static boolean fileMode = false;
    public Console(){}
    public static boolean isFileMode() {
        return fileMode;
    }
    public static void setFileMode(boolean fileMode) {
        Console.fileMode = fileMode;
    }
    @Override
    public Integer readInt() {
        Scanner scanner = new Scanner(System.in);
        return Integer.valueOf(scanner.nextLine().trim());
    }

    @Override
    public Long readLong() {
        Scanner scanner = new Scanner(System.in);
        return Long.valueOf(scanner.nextLine().trim());
    }

    @Override
    public Float readFloat() {
        Scanner scanner = new Scanner(System.in);
        return Float.valueOf(scanner.nextLine().trim());
    }

    @Override
    public String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }

    @Override
    public void write(String text) {
        System.out.println(text);
    }

    @Override
    public void printError(String text) {
        System.err.println(text);
    }

    @Override
    public String getValidatedValue(String message) {
        write(message);
        while (true) {
            String userPrint = readLine();
            if (!userPrint.isEmpty() && !userPrint.isBlank()) {
                return userPrint;
            }
        }
    }
}
