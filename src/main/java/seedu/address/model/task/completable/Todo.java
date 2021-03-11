package seedu.address.model.task.completable;

import java.util.Objects;

import seedu.address.model.task.Completable;

/**
 * Represents a Completable as a Todo.
 */
public class Todo extends Completable {

    /**
     * Constructor for Todo.
     * @param description Description of the Todo.
     */
    public Todo(String description) {
        super(description, null);
    }

    /**
     * Constructor for Todo.
     * @param description Description of the Todo.
     * @param isDone Marks whether the Todo is Done.
     */
    public Todo(String description, Boolean isDone) {
        super(description, null, isDone);
    }

    /**
     * Checks if an instance of a Todo is equal to another Object.
     * @param other Object to be compared with.
     * @return True if both objects are equal. Else return false.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Todo)) {
            return false;
        }

        Todo otherTodo = (Todo) other;
        return otherTodo.getDescription().equals(getDescription())
                && otherTodo.getIsDone().equals(getIsDone());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, isDone);
    }

    /**
     * Returns a String representation of the Completable.
     * @return A String representation of the Completable.
     */
    @Override
    public String toString() {
        return "[T]" + this.getStatusIcon() + " " + this.description;
    }
}
