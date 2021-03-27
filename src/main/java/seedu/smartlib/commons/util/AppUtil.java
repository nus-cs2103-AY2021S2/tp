package seedu.smartlib.commons.util;

import static java.util.Objects.requireNonNull;

import javafx.scene.image.Image;
import seedu.smartlib.MainApp;

/**
 * A container for App specific utility functions.
 */
public class AppUtil {

    /**
     * Gets an {@code Image} from the specified path.
     *
     * @param imagePath path leading to the image
     * @return an Image from the specified path
     */
    public static Image getImage(String imagePath) {
        requireNonNull(imagePath);
        return new Image(MainApp.class.getResourceAsStream(imagePath));
    }

    /**
     * Checks that {@code condition} is true. Used for validating arguments to methods.
     *
     * @param condition condition to be checked
     * @throws IllegalArgumentException if {@code condition} is false.
     */
    public static void checkArgument(Boolean condition) {
        if (!condition) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Checks that {@code condition} is true. Used for validating arguments to methods.
     *
     * @param condition condition to be checked
     * @param errorMessage error message to be thrown if condition is false
     * @throws IllegalArgumentException with {@code errorMessage} if {@code condition} is false.
     */
    public static void checkArgument(Boolean condition, String errorMessage) {
        if (!condition) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

}
