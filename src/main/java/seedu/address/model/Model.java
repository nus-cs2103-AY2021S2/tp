package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
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
     * Returns true if a task with the same identity as {@code task} exists in the daily task list.
     */
    boolean hasDailyTask(Task taskToAdd);

    /**
     * Deletes the given task.
     * The task must exist in the task tracker.
     */
    void deleteTask(Task target);

    void deleteDailyTask(Task target);

    /**
     * Finishes the given task.
     * The task must exist in the task tracker.
     */
    void finishTask(Task target);

    void finishDailyTask(Task target);

    /**
     * Adds the given task.
     * {@code task} must not already exist in the task tracker.
     */
    void addTask(Task task);

    /**
     * Adds the given task to the daily to-do list.
     * {@code task} must not already exist in the daily to-do list.
     */
    void addToDailyToDoList(Task taskToAdd);

    /**
     * Removes the given task from the daily to-do list.
     * {@code task} must not already exist in the daily to-do list.
     */
    void removeFromDailyToDoList(Task dailyTask);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in the task tracker.
     * The task identity of {@code editedTask} must not be the same as another existing task in the task tracker.
     */
    void setTask(Task target, Task editedTask);

    /**
     * Replaces the given task {@code target} with {@code editedTask} in the daily task tracker.
     * {@code target} must exist in the task tracker.
     * The task identity of {@code editedTask} must not be the same as another existing task in the task tracker.
     */
    void setDailyTask(Task target, Task editedTask);

    /**
     * Sorts the TaskTracker according to the given {@code comparator}.
     */
    void sortTasks(Comparator<Task> comparator);

    /**
     * Returns an unmodifiable view of the filtered task list
     */
    ObservableList<Task> getFilteredTaskList();


    /**
     * Returns an unmodifiable view of the daily task list
     */
    ObservableList<Task> getDailyTaskList();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);

    /**
     * Updates the filter of the daily task list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateDailyTaskList(Predicate<Task> predicate);

    void refreshDailyTasks(Task target, Task editedTask);

    /**
     * Commits the taskTracker
     */
    void commitTaskTracker(ReadOnlyTaskTracker currentState);

    /**
     * Undoes the last change to the TaskTracker
     *
     * @return Previous state of TaskTracker
     */
    TaskTracker undoTaskTracker();

    /**
     * Redoes the last change to the TaskTracker after an "undo"
     *
     * @return Previous state of TaskTracker after an "undo"
     */
    TaskTracker redoTaskTracker();

    /**
     * Checks if there is a valid TaskTracker state for undo
     *
     * @return True if there is a valid TaskTracker state for undo
     */
    boolean canUndoTaskTracker();

    /**
     * Checks if there is a valid TaskTracker state for redo
     *
     * @return True if there is a valid TaskTracker state for redo
     */
    boolean canRedoTaskTracker();



}
