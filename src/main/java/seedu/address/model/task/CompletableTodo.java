package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public abstract class CompletableTodo {

    public static final String MESSAGE_CONSTRAINTS_DESCRIPTION = "Description can take any values, and it should "
            + "not be blank";

    /*
     * The first character of the description must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String DESCRIPTION_VALIDATION_REGEX = "[^\\s].*";

    protected String description;
    protected Boolean isDone;

    /**
     * Constructor for CompletableTodo.
     * @param description Description of the CompletableTodo.
     */
    public CompletableTodo(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS_DESCRIPTION);
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for CompletableTodo.
     * @param description Description of the CompletableTodo.
     * @param isDone Marks whether the CompletableTodo is Done.
     */
    public CompletableTodo(String description, Boolean isDone) {
        requireAllNonNull(description, isDone);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS_DESCRIPTION);
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a status icon dependent on the status of the CompletableTodo.
     * @return A string representing the CompletableTodo's status.
     */
    public String getStatusIcon() {
        assert isDone != null;
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns the CompletableTodo description.
     * @return A String representing the CompletableTodo description.
     */
    public String getDescription() {
        assert this.description != null;
        return this.description;
    }

    /**
     * Returns the CompletableTodo description.
     */
    public void setDescription(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS_DESCRIPTION);
        this.description = description;
    }

    /**
     * Returns the status of the CompletableTodo.
     * @return A Boolean representing the CompletableTodo's status.
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
     * Returns true if a given string is a valid Description.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(DESCRIPTION_VALIDATION_REGEX);
    }

    /**
     * Checks if an instance of a CompletableTodo is equal to another Object.
     * @param other Object to be compared with.
     * @return True if both objects are equal. Else return false.
     */
    @Override
    public abstract boolean equals(Object other);

    @Override
    public abstract int hashCode();

    /**
     * Returns a String representation of the CompletableTodo.
     * @return A String representation of the CompletableTodo.
     */
    @Override
    public abstract String toString();
}
