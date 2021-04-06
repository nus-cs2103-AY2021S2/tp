package seedu.address.model.person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.util.FileUtil;

public class Picture {

    public static final String[] ALLOWED_FILE_EXTENSIONS = {".png", ".jpeg", ".jpg"};

    public static final String ALLOWED_FILE_EXTENSIONS_STRING = String.join(", ", ALLOWED_FILE_EXTENSIONS);

    public static final String MESSAGE_CONSTRAINTS = "An image file should exist at specified path. "
            + "Accepted file extensions: " + ALLOWED_FILE_EXTENSIONS_STRING;

    // 10 MB
    public static final long MAX_FILE_SIZE = 10 * 1024 * 1024;

    public static final Set<Byte[]> IMAGE_MAGIC_NUMBERS = new HashSet<>();

    // Initialize magic numbers of images. null represents wildcard values for a particular byte.
    static {
        // png
        IMAGE_MAGIC_NUMBERS.add(FileUtil.intArrayToByteArray(new int[] {0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A,
                0x0A}));
        // jpg, jpeg
        IMAGE_MAGIC_NUMBERS.add(FileUtil.intArrayToByteArray(new int[] {0xFF, 0xD8, 0xFF}));
    }

    private final Path filePath;

    public Picture(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Checks if a given file is a valid image. A valid image is defined to be any PNG, JPG/JPEG image
     * with any file signature in {@code IMAGE_MAGIC_NUMBERS}.
     *
     * @param path Path of the image to be validated.
     * @return true if image is valid otherwise false.
     */
    public static boolean isValidFilePath(Path path) {
        return (FileUtil.isFileExists(path)
                && FileUtil.hasExtension(path, ALLOWED_FILE_EXTENSIONS)
                && isValidImage(path));
    }

    /**
     * Checks if the given @{code str} is a valid image file. Note that this check is lenient and
     * only checks the file signature, not content.
     */
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
