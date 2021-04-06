package seedu.address.model.person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.util.FileUtil;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class Picture {

    public static final String[] ALLOWED_FILE_EXTENSIONS = {".png", ".jpeg", ".jpg"};

    public static final String ALLOWED_FILE_EXTENSIONS_STRING = String.join(", ", ALLOWED_FILE_EXTENSIONS);

    public static final String MESSAGE_CONSTRAINTS = "An image file should exist at specified path. "
            + "Accepted file extensions: " + ALLOWED_FILE_EXTENSIONS_STRING;

    // 10 MB
    public static final long MAX_FILE_SIZE = 10 * 1024 * 1024;

    public static final Set<Byte[]> IMAGE_MAGIC_NUMBERS = new HashSet<>();

    // Initialize magic numbers of images. null represents any value for 2 bytes.
    static {
        // png
        IMAGE_MAGIC_NUMBERS.add(FileUtil.intArrayToByteArray(new int[] {0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A,
                0x0A}));
        // jpg, jpeg
        IMAGE_MAGIC_NUMBERS.add(FileUtil.intArrayToByteArray(new int[] {0xFF, 0xD8, 0xFF, 0xDB}));
        IMAGE_MAGIC_NUMBERS.add(FileUtil.intArrayToByteArray(new int[] {0xFF, 0xD8, 0xFF, 0xE0, 0x00, 0x10, 0x4A, 0x46,
                0x49, 0x46, 0x00, 0x01}));
        IMAGE_MAGIC_NUMBERS.add(FileUtil.intArrayToByteArray(new int[] {0xFF, 0xD8, 0xFF, 0xEE}));
        IMAGE_MAGIC_NUMBERS.add(FileUtil.intArrayToByteArray(new int[] {0xFF, 0xD8, 0xFF, 0xE1, -1, -1, 0x45, 0x78,
                0x69, 0x66, 0x00, 0x00}));
    }

    private final Path filePath;

    public Picture(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Checks if the given @{code str} is a valid file path to an image file
     */
    public static boolean isValidFilePath(String str) {
        Path path;

        try {
            path = ParserUtil.parseFilePath(str);
        } catch (ParseException pe) {
            return false;
        }

        return isValidFilePath(path);
    }

    public static boolean isValidFilePath(Path path) {
        return FileUtil.isFileExists(path) && FileUtil.hasExtension(path, ALLOWED_FILE_EXTENSIONS);
    }

    public static boolean isValidImage(Path path) {
        try {
            return FileUtil.hasMagicNumber(path, IMAGE_MAGIC_NUMBERS);
        } catch (IOException ioException) {
            return false;
        }
    }

    public Path getFilePath() {
        return filePath;
    }

    public String getAbsoluteFilePath() {
        return filePath.toAbsolutePath().toString();
    }

    public void deleteFile() throws IOException {
        Files.delete(filePath);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("filePath: ")
                .append(filePath);
        return sb.toString();
    }
}
