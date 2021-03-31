package seedu.smartlib.commons.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Writes and reads files
 */
public class FileUtil {

    private static final String CHARSET = "UTF-8";

    /**
     * Indicates whether a file exists in the specified file path.
     *
     * @param file the specified file path.
     * @return true if a file exists in the specified file path, and false otherwise
     */
    public static boolean isFileExists(Path file) {
        return Files.exists(file) && Files.isRegularFile(file);
    }

    /**
     * Returns true if {@code path} can be converted into a {@code Path} via {@link Paths#get(String)},
     * otherwise returns false.
     *
     * @param path A string representing the file path. Cannot be null.
     * @return true if path can be converted into a Path object, and false otherwise.
     */
    public static boolean isValidPath(String path) {
        try {
            Paths.get(path);
        } catch (InvalidPathException ipe) {
            return false;
        }
        return true;
    }

    /**
     * Creates a file if it does not exist along with its missing parent directories.
     *
     * @param file the path leading to the file.
     * @throws IOException if the file or directory cannot be created.
     */
    public static void createIfMissing(Path file) throws IOException {
        if (!isFileExists(file)) {
            createFile(file);
        }
    }

    /**
     * Creates a file if it does not exist along with its missing parent directories.
     *
     * @param file the path leading to the file.
     * @throws IOException if the file or directory cannot be created.
     */
    public static void createFile(Path file) throws IOException {
        if (Files.exists(file)) {
            return;
        }

        createParentDirsOfFile(file);

        Files.createFile(file);
    }

    /**
     * Creates parent directories of file if it has a parent directory
     *
     * @param file the path leading to the file.
     * @throws IOException if the file or directory cannot be created.
     */
    public static void createParentDirsOfFile(Path file) throws IOException {
        Path parentDir = file.getParent();

        if (parentDir != null) {
            Files.createDirectories(parentDir);
        }
    }

    /**
     * Reads the data from a file.
     * Assumes the file exists.
     *
     * @param file the path leading to the file.
     * @return data from the file, formatted as a String.
     * @throws IOException if the file or directory cannot be accessed.
     */
    public static String readFromFile(Path file) throws IOException {
        return new String(Files.readAllBytes(file), CHARSET);
    }

    /**
     * Writes given string to a file.
     * Will create the file if it does not exist yet.
     *
     * @param file the path leading to the file.
     * @param content string to be written to the file.
     * @throws IOException if the file or directory cannot be accessed.
     */
    public static void writeToFile(Path file, String content) throws IOException {
        Files.write(file, content.getBytes(CHARSET));
    }

}
