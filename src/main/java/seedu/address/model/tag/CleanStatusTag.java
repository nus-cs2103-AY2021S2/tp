package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class CleanStatusTag {

    public static final String CLEAN = "Clean";
    public static final String UNCLEAN = "Unclean";
    public static final String CLEAN_DESC = "y";
    public static final String UNCLEAN_DESC = "n";

    private static String MESSAGE_CONSTRAINTS = "Please use 'c/y', 'c/n', 'c/clean' or 'c/unclean'";

    private String cleanStatus;

    /**
     * Constructs a default {@code CleanStatusTag} with status value "Clean".
     */
    public CleanStatusTag() {
        this.cleanStatus = CLEAN;
    }

    /**
     * Constructs a {@code CleanStatusTag}.
     *
     * @param cleanStatus A valid clean status.
     */
    public CleanStatusTag(String cleanStatus) {
        requireNonNull(cleanStatus);
        System.out.println("TESTING FOR CLEAN STATUS: " + cleanStatus);
        checkArgument(isValidCleanStatusTag(cleanStatus), MESSAGE_CONSTRAINTS);
        if (cleanStatus.equalsIgnoreCase("y") || cleanStatus.equalsIgnoreCase("clean")) {
            this.cleanStatus = CLEAN;
        } else if (cleanStatus.equalsIgnoreCase("n") || cleanStatus.equalsIgnoreCase("unclean")) {
            this.cleanStatus = UNCLEAN;
        }
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidCleanStatusTag(String test) {
        return test.equalsIgnoreCase("y") || test.equalsIgnoreCase("n")
                || test.equalsIgnoreCase("clean") || test.equalsIgnoreCase("unclean");
    }

    /**
     * Returns value of this {@code CleanStatusTag}.
     */
    public String getValue() {
        return cleanStatus;
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
