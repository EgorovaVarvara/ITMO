package utils;

import java.io.File;

public final class FileUtil {
    public static boolean isFileExist(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return false;
        }
        return file.isFile();
    }
}
