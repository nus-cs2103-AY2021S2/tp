package seedu.address.model.person;

import java.nio.file.Path;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class Picture {
    public static final String[] ACCEPTED_FILE_EXTENSIONS = { ".png", ".jpeg", ".jpg" };
    public static final String MESSAGE_ACCEPTED_FILE_EXTENSIONS = "Accepted file extensions: "
            + String.join(", ", ACCEPTED_FILE_EXTENSIONS);

    public static final String MESSAGE_CONSTRAINTS = "An image file should exist at specified path. "
            + MESSAGE_ACCEPTED_FILE_EXTENSIONS;

    Path filePath;

    public Picture(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFilePath() {
        return filePath;
    }

    public String getAbsoluteFilePath() {
        return filePath.toAbsolutePath().toString();
    }

    public static boolean isValidFilePath(String str) {
        try {
            ParserUtil.parsePictureFilePath(str);
            return true;
        } catch (ParseException pe) {
            return false;
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("filePath: ")
                .append(filePath);
        return sb.toString();
    }
}
