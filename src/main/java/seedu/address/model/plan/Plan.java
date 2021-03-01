package seedu.address.model.plan;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Plan in the description book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Plan {
    // Data fields
    private Semester[] semesters;
    private final Description description;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     * Semesters MUST have at least 1 semester within it
     */
    public Plan(Description description, Set<Tag> tags) {
        requireAllNonNull(description, tags);
        this.description = description;
        this.tags.addAll(tags);
    }

    /**
     * Every field must be present and not null.
     * Semesters MUST have at least 1 semester within it
     */
    public Plan(Description description, Set<Tag> tags, Semester[] semesters) {
        requireAllNonNull(description, tags, semesters);
        this.semesters = semesters;
        this.description = description;
        this.tags.addAll(tags);
    }

    public Description getDescription() {
        return description;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Plan)) {
            return false;
        }

        Plan otherPlan = (Plan) other;
        return otherPlan.getDescription().equals(getDescription())
                && otherPlan.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("; Description: ")
                .append(getDescription());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
