package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class CleanStatusTag {

    private static String MESSAGE_CONSTRAINTS = "should use y or n to show clean status ";

    public final String cleanStatus;

    /**
     * Constructs a {@code Tag}.
     *
     * @param cleanStatus define the clean status by y or n .
     */
    public CleanStatusTag(String cleanStatus) {
        requireNonNull(cleanStatus);
        checkArgument(isValidCleanStatusTag(cleanStatus), MESSAGE_CONSTRAINTS);
        if (cleanStatus.equals("y") || cleanStatus.equals("Cleaned")) {
            this.cleanStatus = "Cleaned";
        } else {
            this.cleanStatus = "Uncleaned";
        }
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidCleanStatusTag(String test) {
        return test.equals("y") || test.equals("Cleaned")
                || test.equals("n") || test.equals("Uncleaned");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && cleanStatus.equals(((Tag) other).tagName)); // state check
    }

    @Override
    public int hashCode() {
        return cleanStatus.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + cleanStatus + ']';
    }

    public static String getMessageConstraints() {
        return MESSAGE_CONSTRAINTS;
    }
}
