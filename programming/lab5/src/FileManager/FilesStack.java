package FileManager;

import java.util.Stack;

public class FilesStack {
    private static final Stack<String> filesStack = new Stack<>();
    public FilesStack(){
    }

    public static Stack<String> getFilesStack() {
        return filesStack;
    }
    public static void addFile(String filename){
        filesStack.push(filename);
    }
    public static void removeFile(){
        filesStack.pop();
    }
}
