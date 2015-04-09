package util;

import java.io.File;
import java.util.Arrays;

/**
 * Created by Zed on 4/8/2015.
 */
public class FileUtils {
    /**
     * Checks whether a File exists, is a file, and has a valid extension.
     *
     * @param file the file to be checked
     * @param validExtensions an array of strings containing valid file extensions
     * @return true if the file is valid and has a valid extension, false otherwise
     */
    public static boolean isValidFile(File file, String... validExtensions) {
        if (isValidFile(file)) {
            String fileExt = file.getName().substring(file.getName().lastIndexOf('.') + 1);

            return Arrays.asList(validExtensions).contains(fileExt);
        }

        return false;
    }

    /**
     * Checks whether a File exists and is a file.
     *
     * @param file the file to be checked
     * @return true if the file exists and is a file, false otherwise
     */
    public static boolean isValidFile(File file) {
        return file.exists() && file.isFile();
    }
}
