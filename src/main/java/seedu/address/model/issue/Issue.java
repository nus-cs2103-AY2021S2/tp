package seedu.address.model.issue;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Issue in the address book. Guarantees: details are present and
 * not null, field values are validated, immutable.
 */
public class Issue implements Comparable<Issue> {

    // Data fields
    private final RoomNumber roomNumber;
    private final Description description;
    private final Timestamp timestamp;
    private final Status status;
    private final Category category;

    /**
     * Every field must be present and not null.
     */
    public Issue(RoomNumber roomNumber, Description description, Timestamp timestamp, Status status,
            Category category) {
        requireAllNonNull(roomNumber, description, timestamp, status, category);
        this.roomNumber = roomNumber;
        this.description = description;
        this.timestamp = timestamp;
        this.status = status;
        this.category = category;
    }

    /**
     * Takes in an issue and returns an issue with the same values except for Status being closed.
     *
     * @param issue to be closed
     * @return the same issue but the status set to closed
     */
    public static Issue closeIssue(Issue issue) {
        requireNonNull(issue);
        return new Issue(
                issue.getRoomNumber(),
                issue.getDescription(),
                issue.getTimestamp(),
                new Status(IssueStatus.Closed),
                issue.getCategory());
    }

    public RoomNumber getRoomNumber() {
        return this.roomNumber;
    }

    public Description getDescription() {
        return this.description;
    }

    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    public Status getStatus() {
        return this.status;
    }

    /**
     * Returns an immutable category set, which throws
     * {@code UnsupportedOperationException} if modification is attempted.
     */
    public Category getCategory() {
        return this.category;
    }

    /**
     * Returns true if both issues have the same identity and data fields. This
     * defines a stronger notion of equality between two issues.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Issue)) {
            return false;
        }

        Issue otherIssue = (Issue) other;
        return otherIssue.getRoomNumber().equals(getRoomNumber())
                && otherIssue.getDescription().equals(getDescription())
                && otherIssue.getTimestamp().equals(getTimestamp()) && otherIssue.getStatus().equals(getStatus())
                && otherIssue.getCategory().equals(getCategory());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(roomNumber, description, timestamp, status, category);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getRoomNumber())
                .append("; Description: ").append(getDescription())
                .append("; Timestamp: ").append(getTimestamp())
                .append("; Status: ").append(getStatus())
                .append("; Category: ").append(getCategory());

        return builder.toString();
    }

    @Override
    public int compareTo(Issue other) {
        return timestamp.compareTo(other.timestamp);
    }

}
