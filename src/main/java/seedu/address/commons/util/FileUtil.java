package seedu.address.commons.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.application.Application;

/**
 * Writes and reads files
 */
public class FileUtil {

    private static final String CHARSET = "UTF-8";

    public static boolean isFileExists(Path file) {
        return Files.exists(file) && Files.isRegularFile(file);
    }

    /**
     * Returns true if {@code path} can be converted into a {@code Path} via {@link Paths#get(String)},
     * otherwise returns false.
     *
     * @param path A string representing the file path. Cannot be null.
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
     * @throws IOException if the file or directory cannot be created.
     */
    public static void createIfMissing(Path file) throws IOException {
        if (!isFileExists(file)) {
            createFile(file);
        }
    }

    /**
     * Creates a file if it does not exist along with its missing parent directories.
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
     */
    public static void createParentDirsOfFile(Path file) throws IOException {
        Path parentDir = file.getParent();

        if (parentDir != null) {
            Files.createDirectories(parentDir);
        }
    }

    /**
     * Assumes file exists
     */
    public static String readFromFile(Path file) throws IOException {
        return new String(Files.readAllBytes(file), CHARSET);
    }

    /**
     * Writes given string to a file.
     * Will create the file if it does not exist yet.
     */
    public static void writeToFile(Path file, String content) throws IOException {
        Files.write(file, content.getBytes(CHARSET));
    }

    /**
     * Assumes source file exists
     */
    public static void copyFile(Path source, Path destination) throws IOException {
        createParentDirsOfFile(destination);
        Files.copy(source, destination);
    }

    /**
     * Extracts extension of file
     */
    public static String extractExtension(Path filePath) {
        String fileName = filePath.toString();
        return extractExtension(fileName);
    }

    /**
     * Extracts extension of file
     */
    public static String extractExtension(String fileName) {
        int lastIndexOf = fileName.lastIndexOf('.');
        return fileName.substring(lastIndexOf);
    }

    /**
     * Checks if filePath contains given extension
     */
    public static boolean hasExtension(Path filePath, String[] allowedExtensions) {
        String ext = FileUtil.extractExtension(filePath);
        return Arrays.stream(allowedExtensions)
                .map(ext::equals)
                .reduce(false, (x, y) -> x || y);
    }

    /**
     * Checks if the content of the file is prefixed with any magic number from a given set.
     *
     * @throws IOException If the file cannot be read.
     */
    public static boolean hasMagicNumber(Path filePath, Set<Byte[]> allowedMagicNumber) throws IOException {
        byte[] bytes = Files.readAllBytes(filePath);
        Byte current;
        for (Byte[] magicNumber : allowedMagicNumber) {
            for (int i = 0; i < magicNumber.length; i++) {
                current = magicNumber[i];
                if (current != null && bytes[i] != current) {
                    break;
                }
                if (i == magicNumber.length - 1 && (current == null || current == bytes[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the file at the path given is below {@code maxSize}
     */
    public static boolean belowSizeLimit(Path path, long maxSize) throws IOException {
        long bytes = Files.size(path);
        return bytes <= maxSize;
    }

    /**
     * Returns the content of a resource as String. Only works on text based resources.
     */
    public static String getResourceAsString(String resourceName) {
        InputStream inputStream = Application.class.getResourceAsStream(resourceName);
        return new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));
    }

    public static String joinPath(String ... tokens) {
        return Arrays.stream(tokens).collect(Collectors.joining(getSeparator()));
    }

    public static String getSeparator() {
        return System.getProperty("file.separator");
    }

    /**
     * Returns a {@code Byte} array given an {@code int} array. Wildcard bytes should be
     * represented by a 32 bit integer with its MSB set.
     *
     * @param intArray the array to convert from.
     * @return a {@code Byte[]} based on the values in {@code intArray}
     */
    public static Byte[] intArrayToByteArray(int[] intArray) {
        Byte[] bytes = new Byte[intArray.length];
        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] < 0) {
                bytes[i] = null;
            } else {
                bytes[i] = (Byte) ((byte) intArray[i]);
            }
        }
        return bytes;
    }
}
