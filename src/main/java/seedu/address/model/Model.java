package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.task.Task;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;

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
     * Returns the user prefs' planner file path.
     */
    Path getPlannerFilePath();

    /**
     * Sets the user prefs' planner file path.
     */
    void setPlannerFilePath(Path plannerFilePath);

    /**
     * Replaces planner data with the data in {@code planner}.
     */
    void setPlanner(ReadOnlyPlanner planner);

    /** Returns the Planner */
    ReadOnlyPlanner getPlanner();

    /**
     * Returns true if a task with the same identity as {@code task} exists in the planner.
     */
    boolean hasTask(Task task);

    /**
     * Returns true if a task's deadline is already over.
     */
    boolean dateOver(Task task);

    /**
     * Deletes the given task.
     * The task must exist in the planner.
     */
    void deleteTask(Task target);

    /**
     * Adds the given task.
     * {@code task} must not already exist in the planner.
     */
    void addTask(Task task);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in the planner.
     * The task identity of {@code editedTask} must not be the same as another existing task in the planner.
     */
    void setTask(Task target, Task editedTask);

    /**
     * Finds the time left until the deadline of {@code task}.
     * The task must already exist in the planner.
     * @return the time left in the form of a {code String}.
     */
    String countdownTask(Task task);

    /** Returns an unmodifiable view of the filtered task list */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);
}
