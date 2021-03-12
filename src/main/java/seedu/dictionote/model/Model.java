package seedu.dictionote.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.dictionote.commons.core.GuiSettings;
import seedu.dictionote.model.contact.Contact;
import seedu.dictionote.model.note.Note;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Contact> PREDICATE_SHOW_ALL_CONTACTS = unused -> true;

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

    /** Returns the NoteBook */
    ReadOnlyNoteBook getNoteBook();

    /**
     * Returns true if a note with the same content as {@code note} exists in the dictionote book.
     */
    boolean hasNote(Note note);


    /**
     * Adds the given note.
     * {@code note} must not already exist in the dictpersonionote book.
     */
    void addNote(Note note);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Note> getFilteredNoteList();

    /**
     * Returns the user prefs' dictionote book file path.
     */
    Path getAddressBookFilePath();
    Path getNoteBookFilePath();

    /**
     * Sets the user prefs' dictionote book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);
    void setNoteBookFilePath(Path noteBookFilePath);

    /**
     * Replaces dictionote book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the dictionote book.
     */
    boolean hasContact(Contact contact);

    /**
     * Deletes the given person.
     * The person must exist in the dictionote book.
     */
    void deleteContact(Contact target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the dictionote book.
     */
    void addContact(Contact contact);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the dictionote book.
     * The person identity of {@code editedPerson} must not be the same as
     * another existing person in the dictionote book.
     */
    void setContact(Contact target, Contact editedContact);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Contact> getFilteredContactList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredContactList(Predicate<Contact> predicate);
}
