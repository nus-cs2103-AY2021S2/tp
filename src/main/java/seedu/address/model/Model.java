package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Task;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = task -> true;

    /**
     * {@code Predicate} that returns true if the task is unfinished
     */
    Predicate<Task> PREDICATE_SHOW_UNFINISHED_TASKS = task -> !task.hasFinished();

    /**
     * {@code Predicate} that returns true if the task is finished
     */
    Predicate<Task> PREDICATE_SHOW_FINISHED_TASKS = task -> task.hasFinished();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' task tracker file path.
     */
    Path getTaskTrackerFilePath();

    /**
     * Sets the user prefs' task tracker file path.
     */
    void setTaskTrackerFilePath(Path taskTrackerFilePath);

    /**
     * Replaces task tracker data with the data in {@code taskTracker}.
     */
    void setTaskTracker(ReadOnlyTaskTracker taskTracker);

    /**
     * Returns the TaskTracker
     */
    ReadOnlyTaskTracker getTaskTracker();

    /**
     * Returns true if a task with the same identity as {@code task} exists in the task tracker.
     */
    boolean hasTask(Task task);

    /**
     * Deletes the given task.
     * The task must exist in the task tracker.
     */
    void deleteTask(Task target);

    /**
     * Finishes the given task.
     * The task must exist in the task tracker.
     */
    void finishTask(Task target);

    /**
     * Adds the given task.
     * {@code task} must not already exist in the task tracker.
     */
    void addTask(Task task);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in the task tracker.
     * The task identity of {@code editedTask} must not be the same as another existing task in the task tracker.
     */
    void setTask(Task target, Task editedTask);

    /**
     * Returns an unmodifiable view of the filtered task list
     */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Returns an unmodifiable view of the finisehed task list
     */
    ObservableList<Task> getFinishedTaskList();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);
}
