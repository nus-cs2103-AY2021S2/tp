package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.Set;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

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

    /**
     * Returns the Planner
     */
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
    void setTask(Task target, Task editedTask) throws CommandException;

    /**
     * Returns true if a tag with the same identity as {@code tag} exists in the planner.
     */
    boolean hasTag(Tag tag);

    /**
     * Gets a Tag that has the same identity as an existing tag.
     */
    Tag getTag(Tag tag);

    /**
     * Deletes the given tag.
     * The tag must exist in the planner.
     */
    void deleteTag(Tag target);

    /**
     * Adds the given tag.
     * {@code tag} must not already exist in the planner.
     */
    void addTag(Tag tag);

    /**
     * Adds tags that are not in the planner from the set of tags and returns a set containing those originally in the
     * planner and new tags that are now added.
     */
    Set<Tag> addTagsIfAbsent(Set<Tag> tag);

    /**
     * Replaces the given set of tags {@code target} with {@code editedTags}.
     * {@code target} must exist in the planner.
     * The tag identities of {@code editedTags} must not be the same as other existing tags in the planner.
     */
    void setTags(Set<Tag> target, Set<Tag> editedTags) throws CommandException;

    /**
     * Finds the time left until the deadline of {@code task}.
     * The task must already exist in the planner.
     *
     * @return the time left in the form of a {code String}.
     */
    String countdownTask(Task task);

    /**
     * Returns an unmodifiable view of the filtered task list
     */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Returns an unmodifiable view of the sorted tag list
     */
    ObservableList<Tag> getSortedTagList();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);

    /**
     * Updates the comparator of the sorted task list to sort by the given {@code comparator}.
     *
     * @throws NullPointerException if {@code comparator} is null.
     */
    void updateSortedTaskList(Comparator<Task> comparator);

    /**
     * Updates the comparator of the sorted tag list to sort by the given {@code comparator}.
     *
     * @throws NullPointerException if {@code comparator} is null.
     */
    void updateSortedTagList(Comparator<Tag> comparator);
}
