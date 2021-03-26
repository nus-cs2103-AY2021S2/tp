package seedu.address.model.issue;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import seedu.address.model.tag.Tag;

/**
 * Represents a Issue in the address book. Guarantees: details are present and
 * not null, field values are validated, immutable.
 */
public class Issue implements Comparable<Issue> {

    // Identity fields
    private final String id;

    // Data fields
    private final RoomNumber roomNumber;
    private final Description description;
    private final Timestamp timestamp;
    private final Status status;
    private final Category category;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Issue(RoomNumber roomNumber, Description description, Timestamp timestamp, Status status,
            Category category, Set<Tag> tags) {
        requireAllNonNull(roomNumber, description, timestamp, status, category);
        this.id = UUID.randomUUID().toString();
        this.roomNumber = roomNumber;
        this.description = description;
        this.timestamp = timestamp;
        this.status = status;
        this.category = category;
        this.tags.addAll(tags);
    }

    public String getId() {
        return this.id;
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

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both issues have the same name. This defines a weaker notion
     * of equality between two issues.
     */
    public boolean isSameIssue(Issue otherIssue) {
        if (otherIssue == this) {
            return true;
        }

        return otherIssue != null && otherIssue.getId().equals(getId());
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
        return otherIssue.getId().equals(getId()) && otherIssue.getRoomNumber().equals(getRoomNumber())
                && otherIssue.getDescription().equals(getDescription())
                && otherIssue.getTimestamp().equals(getTimestamp()) && otherIssue.getStatus().equals(getStatus())
                && otherIssue.getCategory().equals(getCategory())
                && otherIssue.getTags().equals(getTags());
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

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }

    @Override
    public int compareTo(Issue other) {
        return timestamp.compareTo(other.timestamp);
    }

}
