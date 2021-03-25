package seedu.address.model.task;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;

public abstract class Repeatable {

    public static final String MESSAGE_CONSTRAINTS_DESCRIPTION = "Description can take any values, and it should "
            + "not be blank";

    /*
     * The first character of the description must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String DESCRIPTION_VALIDATION_REGEX = "[^\\s].*";

    protected String description;
    protected Boolean isDone;
    protected Interval interval;
    protected LocalDate at;

    /**
     * Constructor for Repeatable.
     * @param description Description of the Repeatable.
     */
    public Repeatable(String description, Interval interval, LocalDate at) {
        requireAllNonNull(description, interval, at);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS_DESCRIPTION);
        this.description = description;
        this.interval = interval;
        this.at = at;
        this.isDone = false;
    }

    /**
     * Constructor for Repeatable.
     * @param description Description of the Repeatable.
     * @param isDone Marks whether the Repeatable is Done.
     */
    public Repeatable(String description, Interval interval, LocalDate at, Boolean isDone) {
        requireAllNonNull(description, interval, at, isDone);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS_DESCRIPTION);
        this.description = description;
        this.interval = interval;
        this.at = at;
        this.isDone = isDone;
    }

    /**
     * Returns the Repeatable description.
     * @return A String representing the Repeatable description.
     */
    public String getDescription() {
        assert this.description != null;
        return this.description;
    }

    /**
     * Returns the status of the Repeatable.
     * @return A Boolean representing the Repeatable's status.
     */
    public Boolean getIsDone() {
        assert this.isDone != null;
        return this.isDone;
    }

    /**
     * Returns the date of the Repeatable.
     * @return A LocalDate representing the Repeatable's date.
     */
    public LocalDate getAt() {
        assert this.at != null : "at should not be null!";
        return this.at;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the Repeatable's interval.
     * @return Interval interval.
     */
    public abstract Interval getRecurrence();

    /**
     * Sets the Repeatable's interval to specified level.
     * @param interval Level of Interval.
     */
    public abstract void setRecurrence(Interval interval);

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(DESCRIPTION_VALIDATION_REGEX);
    }

    /**
     * Checks if an instance of a Repeatable is equal to another Object.
     * @param other Object to be compared with.
     * @return True if both objects are equal. Else return false.
     */
    @Override
    public abstract boolean equals(Object other);

    @Override
    public abstract int hashCode();

    /**
     * Returns a String representation of the Repeatable.
     * @return A String representation of the Repeatable.
     */
    @Override
    public abstract String toString();
}
