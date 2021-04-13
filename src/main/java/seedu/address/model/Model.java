package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.Set;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.attribute.Attribute;
import seedu.address.model.person.Person;
import seedu.address.model.shortcut.ShortcutLibrary;
import seedu.address.storage.Authentication;

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
     * Returns the user prefs' shortcut library file path.
     */
    Path getShortcutLibraryFilePath();

    /**
     * Sets the user prefs' shortcut library file path.
     */
    void setShortcutLibraryFilePath(Path shortcutLibraryFilePath);

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

    /**
     * Updates the Persons in person list to have only the particular given {@code attribute}.
     * @throws NullPointerException if {@code attribute} is null.
     */
    void updatePersonListByAttribute(Set<Attribute> attributeTypes);

    /**
     * Undoes the last modification done on the person list.
     * If list has not been modified, this method does nothing.
     */
    void undoListModification();

    /**
     * Returns an Authentication object needed to lock and unlock ClientBook.
     */
    Authentication getAuthentication();

    /**
     * Updates the comparator of the sorted person list to sort by the given {@code comparator}.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void updateSortedPersonList(Comparator<Person> comparator);

    /**
     * Returns an unmodifiable view of the entire person list
     */
    ObservableList<Person> getWholePersonList();

    /**
     * Replaces Shortcut Library data with the data in {@code shortcutLibrary}.
     */
    void setShortcutLibrary(ShortcutLibrary shortcutLibrary);

    /** Returns the ShortcutLibrary */
    ShortcutLibrary getShortcutLibrary();

    /**
     * Returns true if a shortcut with the same identity {@code shortcutName} exists in the shortcut library.
     */
    boolean hasShortcut(String shortcutName);

    /**
     * Deletes the shortcut with the given name.
     * {@code shortcutName} must exist in the shortcut library.
     */
    void deleteShortcut(String shortcutName);

    /**
     * Adds the shortcut with the given shortcut name and command.
     * {@code shortcutName} must not already exist in the shortcut library.
     */
    void addShortcut(String shortcutName, String shortcutCommand);

    /**
     * Replaces the given shortcut named {@code target} with a new {@code shortcutCommand}.
     * {@code target} must exist in the shortcut library.
     */
    void setShortcut(String target, String shortcutCommand);
}
