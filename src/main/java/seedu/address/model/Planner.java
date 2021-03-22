package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameTask comparison)
 */
public class Planner implements ReadOnlyPlanner {

    private final UniqueTaskList tasks;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        tasks = new UniqueTaskList();
    }

    public Planner() {}

    /**
     * Creates an Planner using the Tasks in the {@code toBeCopied}
     */
    public Planner(ReadOnlyPlanner toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the task list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks.setTasks(tasks);
    }

    /**
     * Resets the existing data of this {@code Planner} with {@code newData}.
     */
    public void resetData(ReadOnlyPlanner newData) {
        requireNonNull(newData);

        setTasks(newData.getTaskList());
    }

    //// task-level operations

    /**
     * Returns true if a task with the same identity as {@code task} exists in the planner.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
    }

    /**
     * Returns true if the deadline of a task is already over.
     */
    public boolean dateOver(Task task) {
        requireNonNull(task);
        return task.dateOver();
    }

    /**
     * Adds a task to the planner.
     * The task must not already exist in the planner.
     */
    public void addTask(Task p) {
        tasks.add(p);
    }

    /**
     * Replaces the given task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the planner.
     * The task identity of {@code editedTask} must not be the same as another existing task in the planner.
     */
    public void setTask(Task target, Task editedTask) {
        requireNonNull(editedTask);

        tasks.setTask(target, editedTask);
    }

    /**
     * Removes {@code key} from this {@code Planner}.
     * {@code key} must exist in the planner.
     */
    public void removeTask(Task key) {
        tasks.remove(key);
    }

    /**
     * Returns number of days left until the deadline of {@code Task}.
     * Task must already exist in the planner.
     * @return a {code String} representing the number of days left until the deadline of the task.
     */
    public String countdown(Task task) {
        requireNonNull(task);

        long numberOfDaysLeft = tasks.countdown(task);

        return Long.toString(numberOfDaysLeft);
    }

    //// util methods

    @Override
    public String toString() {
        return tasks.asUnmodifiableObservableList().size() + " tasks";
        // TODO: refine later
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return tasks.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Planner // instanceof handles nulls
                && tasks.equals(((Planner) other).tasks));
    }

    @Override
    public int hashCode() {
        return tasks.hashCode();
    }
}
