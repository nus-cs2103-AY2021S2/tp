package seedu.heymatez.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.heymatez.commons.core.GuiSettings;
import seedu.heymatez.model.person.Person;
import seedu.heymatez.model.task.Task;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Task> PREDICATE_SHOW_ALL_UNCOMPLETED_TASKS = Task::isUncompleted;
    Predicate<Task> PREDICATE_SHOW_ALL_UNASSIGNED_TASKS = Task::isUnassigned;

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
     * Returns the user prefs' Hey Matez file path.
     */
    Path getHeyMatezFilePath();

    /**
     * Sets the user prefs' Hey Matez file path.
     */
    void setHeyMatezFilePath(Path heyMatezFilePath);

    /**
     * Replaces hay matez data with the data in {@code HeyMatez}.
     */
    void setHeyMatez(ReadOnlyHeyMatez heyMatez);

    /** Returns hey matez */
    ReadOnlyHeyMatez getHeyMatez();

    /**
     * Returns true if a person with the same identity as {@code person} exists in Hey Matez .
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in Hey Matez.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in Hey Matez .
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in Hey Matez.
     * The person identity of {@code editedPerson} must not be the same as another existing person in Hey Matez.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Adds the given task.
     */
    void addTask(Task newTask);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in Hey Matez.
     */
    void setTask(Task target, Task editedTask);

    /**
     * Returns true if a person with the same identity as {@code person} exists in Hey Matez.
     */
    boolean hasTask(Task task);

    /**
     * Deletes the given task.
     * The task must exist in Hey Matez.
     */
    void deleteTask(Task target);

    /** Returns an unmodifiable view of the filtered task list */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Deletes the given Person if he is the Assignee of any task.
     * The person must exist in Hey Matez.
     */
    void removeAssignee(Person target);

    /**
     * Edits the given person {@code target} with {@code editedPerson} in the Assignees of any task.
     * {@code target} must exist in Hey Matez.
     * The person identity of {@code editedPerson} must not be the same as another existing person in Hey Matez.
     */
    void editAssignee(Person target, Person editedPerson);

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);

    boolean checkAssignees(Task task);

    /**
     * Returns true if the filtered person list is empty.
     */
    boolean isPersonListEmpty();
}
