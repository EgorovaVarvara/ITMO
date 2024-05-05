package utils;

import console.UserInput;

import java.io.*;
import java.util.ArrayDeque;

public class ExecuteFileManager implements UserInput {
    private static final ArrayDeque<String> pathQueue = new ArrayDeque<>();
    private static final ArrayDeque<BufferedReader> fileReaders = new ArrayDeque<>();

    public static void pushFile(String path) throws FileNotFoundException {
        pathQueue.push(new File(path).getAbsolutePath());
        fileReaders.push(new BufferedReader(new InputStreamReader(new FileInputStream(path))));
    }

    public static File getFile() {
        return new File(pathQueue.getFirst());
    }

    public static String readLine() throws IOException {
        return fileReaders.getFirst().readLine();
    }
    public static void popFile() throws IOException {
        fileReaders.getFirst().close();
        fileReaders.pop();
        if(!pathQueue.isEmpty()) {
            pathQueue.pop();
        }
    }

    public static void popRecursion(){
        if(!pathQueue.isEmpty()) {
            pathQueue.pop();
        }
    }

    public static boolean fileRepeat(String path){
        return pathQueue.contains(new File(path).getAbsolutePath());
    }

    @Override
    public String nextLine() {
        try{
            return readLine();
        } catch (IOException e){
            return "";
        }
    }
}
