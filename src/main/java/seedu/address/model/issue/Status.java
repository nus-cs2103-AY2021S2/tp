package seedu.address.model.issue;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.issue.exceptions.InvalidIssueStatusException;

/**
 * Represents an issue's status in SunRez. Guarantees: immutable; is valid as
 * declared in {@link #isValidStatus(String)}
 */
public class Status {

    public static final String MESSAGE_CONSTRAINTS = "Statuses should be 1 of 2 words: "
            + "'Pending' or 'Closed'. It is case-insensitive and shorthand is 'p' and 'c' respectively.";

    /*
     * The first character of the status must not be a whitespace, otherwise " " (a
     * blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "(?i)pending|p|closed|c";

    public final IssueStatus value;

    /**
     * Constructs a {@code Status}.
     *
     * @param status A valid status.
     */
    public Status(String status) {
        requireNonNull(status);
        checkArgument(isValidStatus(status), MESSAGE_CONSTRAINTS);
        this.value = parse(status);
    }

    /**
     * Constructs a {@code Status}.
     *
     * @param status A valid status.
     */
    public Status(IssueStatus status) {
        requireNonNull(status);
        this.value = status;
    }

    /**
     * Returns true if a given string is a valid status.
     */
    public static boolean isValidStatus(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Retuns the IssueStatus enum of the given status
     *
     * @param status
     * @return IssueStatus enum of status
     */
    private static IssueStatus parse(String status) {
        String lowercaseStatus = status.toLowerCase();
        switch (lowercaseStatus) {
        case "pending":
        case "p":
            return IssueStatus.Pending;
        case "closed":
        case "c":
            return IssueStatus.Closed;
        default:
            throw new InvalidIssueStatusException(String.format("%s is not a valid issue status", status));
        }
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Status // instanceof handles nulls
                        && value.equals(((Status) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
