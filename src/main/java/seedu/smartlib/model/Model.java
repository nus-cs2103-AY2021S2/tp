package seedu.smartlib.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.smartlib.commons.core.GuiSettings;
import seedu.smartlib.model.reader.Reader;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Reader> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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
    void setSmartLib(ReadOnlySmartLib smartLib);

    /** Returns the AddressBook */
    ReadOnlySmartLib getSmartLib();

    /**
     * Returns true if a reader with the same identity as {@code reader} exists in the registered reader base.
     */
    boolean hasReader(Reader reader);

    /**
     * Deletes the given reader.
     * The reader must exist in the registered reader base.
     */
    void deleteReader(Reader target);

    /**
     * Adds the given reader.
     * {@code reader} must not already exist in the registered reader base.
     */
    void addReader(Reader reader);

    /**
     * Replaces the given reader {@code target} with {@code editedReader}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Reader target, Reader editedReader);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Reader> getFilteredReaderList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Reader> predicate);
}
