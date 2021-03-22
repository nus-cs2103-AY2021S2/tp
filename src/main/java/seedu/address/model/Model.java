package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.group.Group;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonEvent;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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

    /**
     * Adds the given group.
     * {@code group} must not already exist in the address book.
     */
    void addGroup(Group group);

    /**
     * Returns true if a person with the same identity as {@code group} exists in the address book.
     */
    boolean hasGroup(Group group);

    /**
     * Deletes the given group.
     * The group must exist in the address book.
     */
    void deleteGroup(Group target);

    /**
     * Replaces the group for {@code groupName} in the map with {@code editedGroup}.
     * {@code groupName} must exist in the group map.
     */
    void setGroup(Name groupName, Group editedGroup);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns an unmodifiable view of the filtered group list
     */
    ObservableMap<Name, Group> getGroupMap();

    /**
     * Returns an unmodifiable view of upcoming dates
     */
    ObservableList<PersonEvent> getUpcomingDates();

    /**
     * Updates the list of upcoming dates
     */
    void updateUpcomingDates();

    /**
     * Returns an unmodifiable view of a person to display the full details for
     */
    ObservableList<Person> getDetailedPerson();

    /**
     * Updates the person to display the full details for
     */
    void updateDetailedPerson(Person personToDisplay);
}
