package seedu.iscam.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.iscam.commons.util.AppUtil.checkArgument;

/**
 * Represents a Client's image link in the iscam book.
 */
public class Image {
    public static final String MESSAGE_CONSTRAINTS = "Images should refer to a jpg, jpeg or png file in the \"data\" "
            + "folder, must not contain whitespaces and must end in either .jpg, .jpeg or .png";
    public static final String VALIDATION_REGEX = "^\\w+\\.(gif|png|jpg|jpeg)$"; // Must not contain spaces
    public final String value;

    /**
     * Constructs an {@code Image}.
     *
     * @param imageRes name of the image file, including the file type extension.
     */
    public Image(String imageRes) {
        requireNonNull(imageRes);
        checkArgument(isValidImageRes(imageRes), MESSAGE_CONSTRAINTS);
        value = imageRes;
    }

    public static boolean isValidImageRes(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Image
                && value.equals(((Image) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
