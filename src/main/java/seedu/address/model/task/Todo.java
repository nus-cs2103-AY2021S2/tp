package seedu.address.model.task;

import java.util.Objects;

/**
 * Represents a Task as a Todo.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo.
     * @param description Description of the Todo.
     */
    public Todo(String description) {
        super(description);
        this.taskType = "T";
    }

    /**
     * Constructor for Todo.
     * @param description Description of the Todo.
     * @param isDone Marks whether the Todo is Done.
     */
    public Todo(String description, Boolean isDone) {
        super(description, isDone);
        this.taskType = "T";
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
                && otherTodo.getIsDone().equals(getIsDone())
                && otherTodo.getTaskType().equals(getTaskType());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(taskType, description, isDone);
    }

    /**
     * Returns a String representation of the Task.
     * @return A String representation of the Task.
     */
    @Override
    public String toString() {
        return "[T]" + this.getStatusIcon() + " " + this.description;
    }
}
