package seedu.module.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.module.commons.core.GuiSettings;
import seedu.module.model.task.Task;

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
     * Returns the user prefs' module book file path.
     */
    Path getModuleBookFilePath();

    /**
     * Sets the user prefs' module book file path.
     */
    void setModuleBookFilePath(Path moduleBookFilePath);

    /**
     * Replaces module book data with the data in {@code moduleBook}.
     */
    void setModuleBook(ReadOnlyModuleBook moduleBook);

    /** Returns the ModuleBook */
    ReadOnlyModuleBook getModuleBook();

    /**
     * Returns true if a task with the same identity as {@code task} exists in the module book.
     */
    boolean hasTask(Task task);

    /**
     * Returns true if a task with the exact same attributes as {@code task} exists in the module book.
     */
    boolean hasRecurringTask(Task task);

    /**
     * Deletes the given task.
     * The task must exist in the module book.
     */
    void deleteTask(Task target);

    /**
     * Adds the given task.
     * {@code task} must not already exist in the module book.
     */
    void addTask(Task task);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in the module book.
     * The task identity of {@code editedTask} must not be the same as another existing task in the module book.
     */
    void setTask(Task target, Task editedTask);

    /**
     * Sorts the tasks by deadline.
     */
    void sortTasks();

    /** Returns an unmodifiable view of the filtered task list */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);
}
