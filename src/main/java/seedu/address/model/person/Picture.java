package seedu.address.model.person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
