package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a CleanStatusTag in the residence tracker.
 * Guarantees: name is valid as declared in {@link #isValidCleanStatusTag(String)}
 */
public class CleanStatusTag {
    //@@author Soorya
    public static final String CLEAN = "Clean";
    public static final String UNCLEAN = "Unclean";
    public static final String MESSAGE_CONSTRAINTS = "Please use 'c/y', 'c/n', 'c/clean' or 'c/unclean'";

    private String cleanStatus;

    /**
     * Constructs a default {@code CleanStatusTag} with status value "Clean".
     */
    public CleanStatusTag() {
        this.cleanStatus = CLEAN;
    }

    //@@author Wang Tao
    /**
     * Constructs a {@code CleanStatusTag}.
     *
     * @param cleanStatus A valid clean status.
     */
    public CleanStatusTag(String cleanStatus) {
        requireNonNull(cleanStatus);
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

    //@@author Soorya
    /**
     * Returns value of this {@code CleanStatusTag}.
     */
    public String getValue() {
        return cleanStatus;
    }

    //@@author Wang Tao
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CleanStatusTag // instanceof handles nulls
                && cleanStatus.equals(((CleanStatusTag) other).cleanStatus)); // state check
    }

    @Override
    public int hashCode() {
        return cleanStatus.hashCode();
    }

    /**
     * Formats this CleanStatusTag as text for viewing.
     */
    @Override
    public String toString() {
        return '[' + cleanStatus + ']';
    }
}
