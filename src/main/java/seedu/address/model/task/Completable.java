package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public abstract class Completable {

    public static final String MESSAGE_CONSTRAINTS = "Description can take any values, and it should not be blank";

    /*
     * The first character of the description must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String DESCRIPTION_VALIDATION_REGEX = "[^\\s].*";

    protected String description;
    protected Boolean isDone;

    /**
     * Constructor for Completable.
     * @param description Description of the Completable.
     */
    public Completable(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for Completable.
     * @param description Description of the Completable.
     * @param isDone Marks whether the Completable is Done.
     */
    public Completable(String description, Boolean isDone) {
        requireAllNonNull(description, isDone);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        this.description = description;
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
