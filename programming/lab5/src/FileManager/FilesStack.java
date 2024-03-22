package FileManager;

import java.util.Stack;

/**
 * The {@code FilesStack} class stores stack of files that user working with.
 *
 * @author Egorova Varvara
 */
public class FilesStack {
    /**
     * Stack of files.
     */
    private static final Stack<String> filesStack = new Stack<>();

    /**
     * Constructor for class objects.
     */
    public FilesStack(){
    }

    /**
     * @return filesStack
     */
    public static Stack<String> getFilesStack() {
        return filesStack;
    }

    /**
     * Adds new file to stack.
     * @param filename of new file
     */
    public static void addFile(String filename){
        filesStack.push(filename);
    }

    /**
     * Deletes file from stack.
     */
    public static void removeFile(){
        filesStack.pop();
    }
}
