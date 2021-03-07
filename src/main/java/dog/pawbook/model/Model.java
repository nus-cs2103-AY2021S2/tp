package dog.pawbook.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import dog.pawbook.commons.core.GuiSettings;
import dog.pawbook.model.owner.Owner;
import javafx.collections.ObservableList;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Owner> PREDICATE_SHOW_ALL_OWNERS = unused -> true;

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
     * Returns true if a owner with the same identity as {@code owner} exists in the address book.
     */
    boolean hasOwner(Owner owner);

    /**
     * Deletes the given owner.
     * The owner must exist in the address book.
     */
    void deleteOwner(Owner target);

    /**
     * Adds the given owner.
     * {@code owner} must not already exist in the address book.
     */
    void addOwner(Owner owner);

    /**
     * Replaces the given owner {@code target} with {@code editedOwner}.
     * {@code target} must exist in the address book.
     * The owner identity of {@code editedOwner} must not be the same as another existing owner in the address book.
     */
    void setOwner(Owner target, Owner editedOwner);

    /** Returns an unmodifiable view of the filtered owner list */
    ObservableList<Owner> getFilteredOwnerList();

    /**
     * Updates the filter of the filtered owner list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredOwnerList(Predicate<Owner> predicate);
}
