package seedu.address.model.module;

import java.time.LocalDateTime;
import java.util.Objects;

import seedu.address.commons.util.LocalDateTimeUtil;
import seedu.address.model.Event;
import seedu.address.model.tag.Tag;

public class Assignment extends Event {
    public static final String MESSAGE_CONSTRAINTS = "Assignment deadline must be formatted "
            + "to a valid DD/MM/YYYY HHmm";

    public final Description description;
    public final LocalDateTime deadline;
    private boolean isDone;

    /**
     * Constructs an {@code Assignment}.
     *
     * @param description A valid assignment description.
     * @param deadline A valid date and time.
     * @param tag A tag with the module name.
     */
    public Assignment(Description description, LocalDateTime deadline, Tag tag) {
        super(description, deadline, tag);
        this.isDone = false;
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Constructs an {@code Assignment}.
     *
     * @param description A valid assignment description.
     * @param deadline A valid date and time.
     */
    public Assignment(Description description, LocalDateTime deadline, Tag tag, boolean isDone) {
        super(description, deadline, tag);
        this.isDone = false;
        this.description = description;
        this.deadline = deadline;
        this.isDone = isDone;
    }

    /**
     * Changes the description of the assignment with the given {@code newDescription}
     */
    public Assignment setDescription(Description newDescription) {
        return new Assignment(newDescription, deadline, tag);
    }

    /**
     * Changes the deadline of the assignment with the given {@code newDeadline}
     */
    public Assignment setDeadline(LocalDateTime newDeadline) {
        return new Assignment(description, newDeadline, tag);
    }

    public boolean isSameAssignment(Assignment other) {
        return this.equals(other);
    }

    /**
     * Get a String representation of the doneStatus.
     * @return [X] for done assignment and [ ] for un-done assignment.
     */
    public String isDone() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /**
     * this method toggle the done status of our assignment, Either from done to undone or undone to done
     */
    public void toggleDoneStatus() {
        this.isDone = !isDone;
    }


    @Override
    public String toString() {
        return description + " due: " + deadline.format(LocalDateTimeUtil.DATETIME_FORMATTER)
            + "    " + isDone();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Assignment // instanceof handles nulls
                && isDone() == isDone())
                && description.equals(((Assignment) other).description)
                && deadline.equals(((Assignment) other).deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, deadline);
    }
}
