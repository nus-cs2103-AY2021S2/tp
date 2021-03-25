package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;

import seedu.address.commons.util.DateUtil;

public abstract class CompletableDeadline {

    public static final String MESSAGE_CONSTRAINTS_DESCRIPTION = "Description can take any values, and it should "
            + "not be blank";

    /*
     * The first character of the description must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String DESCRIPTION_VALIDATION_REGEX = "[^\\s].*";

    protected String description;
    protected Boolean isDone;
    protected LocalDate by;

    /**
     * Constructor for CompletableDeadline.
     * @param description Description of the CompletableDeadline.
     * @param by Deadline of the CompletableDeadline.
     */
    public CompletableDeadline(String description, LocalDate by) {
        requireAllNonNull(description, by);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS_DESCRIPTION);
        this.description = description;
        this.by = by;
        this.isDone = false;
    }

    /**
     * Constructor for CompletableDeadline.
     * @param description Description of the CompletableDeadline.
     * @param by Deadline of the CompletableDeadline.
     * @param isDone Marks whether the CompletableDeadline is Done.
     */
    public CompletableDeadline(String description, LocalDate by, Boolean isDone) {
        requireAllNonNull(description, by, isDone);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS_DESCRIPTION);
        this.description = description;
        this.by = by;
        this.isDone = isDone;
    }

    /**
     * Returns the deadline of the Deadline.
     * @return A LocalDate representing the deadline of the Deadline.
     */
    public LocalDate getBy() {
        assert this.by != null : "by should not be null!";
        return this.by;
    }

    /**
     * Returns the CompletableDeadline description.
     * @return A String representing the CompletableDeadline description.
     */
    public String getDescription() {
        assert this.description != null;
        return this.description;
    }

    /**
     * Returns the CompletableDeadline description.
     */
    public void setDescription(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS_DESCRIPTION);
        this.description = description;
    }

    /**
     * Returns the status of the CompletableDeadline.
     * @return A Boolean representing the CompletableDeadline's status.
     */
    public Boolean getIsDone() {
        assert this.isDone != null;
        return this.isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a String representation of the by date.
     * @return String representation of by date.
     */
    public String getStringByDate() {
        assert this.by != null;
        return DateUtil.decodeDateForStorage(by);
    };

    /**
     * Returns true if a given string is a valid Description.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(DESCRIPTION_VALIDATION_REGEX);
    }

    /**
     * Checks if an instance of a CompletableDeadline is equal to another Object.
     * @param other Object to be compared with.
     * @return True if both objects are equal. Else return false.
     */
    @Override
    public abstract boolean equals(Object other);

    @Override
    public abstract int hashCode();

    /**
     * Returns a String representation of the CompletableDeadline.
     * @return A String representation of the CompletableDeadline.
     */
    @Override
    public abstract String toString();
}
