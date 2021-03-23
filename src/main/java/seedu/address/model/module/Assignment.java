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
     */
    public Assignment(Description description, LocalDateTime deadline, Tag tag) {
        super(description, deadline, tag);
        this.isDone = false;
        this.description = description;
        this.deadline = deadline;
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

    public boolean isDone() {
        return this.isDone;
    }

    /**
     * this method toggle the done status of our assignment, Either from done to undone or undone to done
     */
    public void toggleDoneStatus() {
        this.isDone = !isDone;
    }

    public String getDoneStatus() {
        String doneStatus = "";
        if (this.isDone) {
            doneStatus = "[D]";
        } else {
            doneStatus = "[X]";
        }
        return doneStatus;
    }

    @Override
    public String toString() {
        return getDoneStatus() + " " + description + " due: " + deadline.format(LocalDateTimeUtil.DATETIME_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Assignment // instanceof handles nulls
                && getDoneStatus().equals(((Assignment) other).getDoneStatus())
                && description.equals(((Assignment) other).description)
                && deadline.equals(((Assignment) other).deadline));
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, deadline);
    }
}
