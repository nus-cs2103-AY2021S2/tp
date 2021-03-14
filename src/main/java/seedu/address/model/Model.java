package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Event> PREDICATE_SHOW_ALL_EVENTS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    //=========== SOChedule ==================================================================================

    /**
     * Replaces sochedule data with the data in {@code sochedule}.
     */
    void setSochedule(ReadOnlySochedule sochedule);

    /** Returns the Sochedule */
    ReadOnlySochedule getSochedule();

    /**
     * Returns the user prefs' Sochedule file path.
     */
    Path getSocheduleFilePath();

    /**
     * Sets the user prefs' Sochedule file path.
     */
    void setSocheduleFilePath(Path socheduleFilePath);



    //=========== task ==================================================================================


    /**
     * Returns true if a task with the same identity as {@code task} exists in the Sochedule.
     */
    boolean hasTask(Task task);

    /**
     * Deletes the given task.
     * The task must exist in the Sochedule.
     */
    void deleteTask(Task target);

    /**
     * Adds the given task.
     * {@code task} must not already exist in the Sochedule.
     */
    void addTask(Task task);

    /**
     * Complete the given task.
     * {@code task} must not already exist in the Sochedule.
     */
    void doneTask(Task task);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in the Sochedule.
     * The task identity of {@code editedTask} must not be the same as another existing task in the Sochedule.
     */
    void setTask(Task target, Task editedTask);

    /** Returns an unmodifiable view of the filtered task list */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);

    //=========== event ==================================================================================

    /**
     * Returns true if an event with the same identity as {@code event} exists in the Sochedule.
     */
    boolean hasEvent(Event event);

    /**
     * Deletes the given event.
     * The event must exist in the Sochedule.
     */
    void deleteEvent(Event target);

    /**
     * Adds the given event.
     * {@code event} must not already exist in the Sochedule.
     */
    void addEvent(Event event);

    /**
     * Replaces the given event {@code target} with {@code editedEvent}.
     * {@code target} must exist in the Sochedule.
     * The event identity of {@code editedEvent} must not be the same as another existing event in the Sochedule.
     */
    void setEvent(Event target, Event editedEvent);

    /** Returns an unmodifiable view of the filtered event list */
    ObservableList<Event> getFilteredEventList();

    /**
     * Updates the filter of the filtered event list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEventList(Predicate<Event> predicate);

}
