package seedu.address.testutil;

import java.time.LocalDateTime;

import seedu.address.model.module.Assignment;
import seedu.address.model.module.Description;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help in building an assignment.
 */
public class AssignmentBuilder {

    public static final String DEFAULT_DESCRIPTION = "description";
    public static final String DEFAULT_TAG = "CS2103";
    public static final LocalDateTime DEFAULT_DEADLINE = LocalDateTime.of(2021, 04, 24, 9, 00);
    public static final boolean DEFAULT_ISDONE = false;

    private Description description;
    private LocalDateTime deadline;
    private Tag tag;
    private boolean isDone;

    /**
     * Creates a {@code AssignmentBuilder} with default details;
     */
    public AssignmentBuilder() {
        this.description = new Description(DEFAULT_DESCRIPTION);
        this.deadline = DEFAULT_DEADLINE;
        this.tag = new Tag(DEFAULT_TAG);
        this.isDone = DEFAULT_ISDONE;
    }

    /**
     * Sets the {@code Description} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the deadline of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
        return this;
    }

    /**
     * Sets the {@code Tag} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withTag(String tag) {
        this.tag = new Tag(tag);
        return this;
    }
    /**
     * Sets the done status of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withDoneStatus(boolean isDone) {
        this.isDone = isDone;
        return this;
    }

    public Assignment build() {
        return new Assignment(description, deadline, tag, isDone);
    }
}
