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
    public FilesStack(){ // TODO зачем конструктор когда все static + 0 объявлений
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
    public static void addFile(String filename){ //TODO -> FilesStack.getFilesStack().push(filename)
        filesStack.push(filename);
    }

    /**
     * Deletes file from stack.
     */
    public static void removeFile(){ //TODO -> FilesStack.getFilesStack().pop()
        filesStack.pop();
    }
}
