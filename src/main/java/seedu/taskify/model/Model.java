package seedu.taskify.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.taskify.commons.core.GuiSettings;
import seedu.taskify.model.task.Task;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
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
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path taskifyFilePath);

    /**
     * Replaces address book data with the data in {@code taskify}.
     */
    void setAddressBook(ReadOnlyTaskify taskify);

    /**
     * Returns the TaskifyParser
     */
    ReadOnlyTaskify getAddressBook();

    /**
     * Returns true if a task with the same identity as {@code task} exists in the address book.
     */
    boolean hasTask(Task task);

    /**
     * Deletes the given task.
     * The task must exist in the address book.
     */
    void deleteTask(Task target);

    /**
     * Adds the given task.
     * {@code task} must not already exist in Taskify.
     */
    void addTask(Task task);

    /**
     * Sort tasks in Taskify by deadline.
     */
    void sortTask();

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in the address book.
     * The task identity of {@code editedTask} must not be the same as another existing task in the address book.
     */
    void setTask(Task target, Task editedTask);

    /**
     * Returns an unmodifiable view of the filtered task list
     */
    ObservableList<Task> getFilteredTaskList();

    ObservableList<Task> getExpiredFilteredTaskList();

    ObservableList<Task> getCompletedFilteredTaskList();

    ObservableList<Task> getTodaysFilteredTaskList();

    ObservableList<Task> getUncompletedFilteredTaskList();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);

    void updateExpiredFilterTaskList(Predicate<Task> predicate);

    void updateCompletedFilterTaskList(Predicate<Task> predicate);

    void updateTodaysFilteredTaskList(Predicate<Task> predicate);

    void updateUncompletedFilterTaskList(Predicate<Task> predicate);
}
