package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.event.Event;
import seedu.address.model.event.UniqueEventList;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;

public class Sochedule implements ReadOnlySochedule {

    private final UniqueEventList events;
    private final UniqueTaskList tasks;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        events = new UniqueEventList();
        tasks = new UniqueTaskList();
    }

    public Sochedule() {}

    /**
     * Creates an Sochedule using the Tasks and Events in the {@code toBeCopied}
     */
    public Sochedule(ReadOnlySochedule toBeCopied) {
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
     * Replaces the contents of the events list with {@code events}.
     * {@code events} must not contain duplicate events.
     */
    public void setEvents(List<Event> events) {
        this.events.setEvents(events);
    }

    /**
     * Resets the existing data of this {@code Sochedule} with {@code newData}.
     */
    public void resetData(ReadOnlySochedule newData) {
        requireNonNull(newData);
        setTasks(newData.getTaskList());
        setEvents(newData.getEventList());
    }

    /**
     * Resets the existing task data of this {@code Sochedule} with {@code newData}.
     */
    public void resetTaskData(ReadOnlySochedule newData) {
        requireNonNull(newData);
        setTasks(newData.getTaskList());
    }

    /**
     * Resets the existing event data of this {@code Sochedule} with {@code newData}.
     */
    public void resetEventData(ReadOnlySochedule newData) {
        requireNonNull(newData);
        setEvents(newData.getEventList());
    }

    //// task-level operations

    /**
     * Returns true if a task with the same identity as {@code task} exists in Sochedule.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
    }

    /**
     * Adds a task to Sochedule.
     * The task must not already exist in Sochedule.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Replaces the given task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in Sochedule.
     * The task identity of {@code editedTask} must not be the same as another existing Task in Sochedule.
     */
    public void setTask(Task target, Task editedTask) {
        requireNonNull(editedTask);

        tasks.setTask(target, editedTask);
    }

    /**
     * Removes {@code key} from {@code Sochedule}.
     * {@code key} must exist in Sochedule.
     */
    public void removeTask(Task key) {
        tasks.remove(key);
    }

    /**
     * Completes {@code key} from {@code Sochedule}.
     * {@code task} must exist in Sochedule.
     */
    public void doneTask(Task task) {
        task.markTaskAsDone();
    }

    /**
     * Sorts the contents of this list given {@code comparingVar}.
     * {@code comparingVar} must be a valid parameter.
     *
     * @param comparingVar The value to be used for sorting.
     */
    public void sortTasks(String comparingVar) {
        tasks.sort(comparingVar);
    }

    //// event-level operations

    /**
     * Returns true if a event with the same identity as {@code event} exists in Sochedule.
     */
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return events.contains(event);
    }

    /**
     * Adds a event to Sochedule.
     * The event must not already exist in Sochedule.
     */
    public void addEvent(Event e) {
        events.add(e);
    }

    /**
     * Replaces the given event {@code target} in the list with {@code editedEvent}.
     * {@code target} must exist in Sochedule.
     * The event identity of {@code editedEvent} must not be the same as another existing Event in Sochedule.
     */
    public void setEvent(Event target, Event editedEvent) {
        requireNonNull(editedEvent);

        events.setEvent(target, editedEvent);
    }

    /**
     * Removes {@code key} from {@code Sochedule}.
     * {@code key} must exist in Sochedule.
     */
    public void removeEvent(Event key) {
        events.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return tasks.asUnmodifiableObservableList().size() + " Tasks;"
                + events.asUnmodifiableObservableList().size() + " Events";
        // TODO: refine later
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return tasks.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Event> getEventList() {
        return events.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Sochedule // instanceof handles nulls
                && tasks.equals(((Sochedule) other).tasks))
                && events.equals(((Sochedule) other).events);
    }

    @Override
    public int hashCode() {
        return tasks.hashCode() ^ events.hashCode();
    }
}
