package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;

import seedu.address.commons.util.DateUtil;

public abstract class Completable {

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
     * Constructor for Completable.
     * @param description Description of the Completable.
     * @param by Deadline of the Completable or Null if there is no deadline.
     */
    public Completable(String description, LocalDate by) {
        requireAllNonNull(description, by);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS_DESCRIPTION);
        this.description = description;
        this.by = by;
        this.isDone = false;
    }

    /**
     * Constructor for Completable.
     * @param description Description of the Completable.
     * @param by Deadline of the Completable or Null if there is no deadline.
     * @param isDone Marks whether the Completable is Done.
     */
    public Completable(String description, LocalDate by, Boolean isDone) {
        requireAllNonNull(description, by, isDone);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS_DESCRIPTION);
        this.description = description;
        this.by = by;
        this.isDone = isDone;
    }

    /**
     * Returns a status icon dependent on the status of the Completable.
     * @return A string representing the Completable's status.
     */
    public String getStatusIcon() {
        assert isDone != null;
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns the Completable description.
     * @return A String representing the Completable description.
     */
    public String getDescription() {
        assert this.description != null;
        return this.description;
    }

    /**
     * Returns the status of the Completable.
     * @return A Boolean representing the Completable's status.
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
     * Returns a String representation of the by date, or null if the completable does not have a by date.
     * @return String representation of by date or null if the completable does not have a by date.
     */
    public String getStringByDate() {
        return by == null ? null : DateUtil.decodeDateForStorage(by);
    };

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(DESCRIPTION_VALIDATION_REGEX);
    }

    /**
     * Checks if an instance of a Completable is equal to another Object.
     * @param other Object to be compared with.
     * @return True if both objects are equal. Else return false.
     */
    @Override
    public abstract boolean equals(Object other);

    @Override
    public abstract int hashCode();

    /**
     * Returns a String representation of the Completable.
     * @return A String representation of the Completable.
     */
    @Override
    public abstract String toString();
}
