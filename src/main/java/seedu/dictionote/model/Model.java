package seedu.dictionote.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.dictionote.commons.core.GuiSettings;
import seedu.dictionote.model.contact.Contact;
import seedu.dictionote.model.dictionary.Content;
import seedu.dictionote.model.dictionary.Definition;
import seedu.dictionote.model.dictionary.DisplayableContent;
import seedu.dictionote.model.note.Note;
import seedu.dictionote.ui.DictionaryContentConfig;
import seedu.dictionote.ui.NoteContentConfig;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Contact> PREDICATE_SHOW_ALL_CONTACTS = unused -> true;
    /** {@code Predicate} that always evaluate to true */
    Predicate<Content> PREDICATE_SHOW_ALL_CONTENTS = unused -> true;
    /**{@code Predicate} that always evaluate to true */
    Predicate<Definition> PREDICATE_SHOW_ALL_DEFINITION = unused -> true;
    /**{@code Predicate} that always evaluate to true */
    Predicate<Note> PREDICATE_SHOW_ALL_NOTES = unused -> true;



    //=========== UserPrefs ==================================================================================
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
     * Sets the user prefs' dictionote book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);
    void setNoteBookFilePath(Path noteBookFilePath);
    void setDictionaryFilePath(Path getDictionaryFilePath);
    void setDefinitionBookFilePath(Path getDefinitionBookFilePath);

    /**
     * Returns the user prefs' dictionote book file path.
     */
    Path getAddressBookFilePath();
    Path getNoteBookFilePath();
    Path getDictionaryFilePath();
    Path getDefinitionBookFilePath();

    //=========== NoteBook ===================================================================================
    /**
     * Returns true if a note with the same content as {@code note} exists in the dictionote book.
     */
    boolean hasNote(Note note);

    /**
     * Adds the given note.
     * {@code note} must not already exist in the dictpersonionote book.
     */
    void addNote(Note note);

    /**
     * Deletes the given note.
     * The note must exist in the dictionote book.
     */
    void deleteNote(Note target);

    /**
     * Show the given note.
     */
    void showNote(Note note);

    /**
     * Check if there is note shown on note content panel.
     */
    boolean hasNoteShown();

    /**
     * Reset the note shown to it original content.
     */
    void resetNoteShown();

    /**
     * Get the note shown.
     */
    Note getNoteShown();

    /**
     * Get the edited note shown content.
     */
    String getEditedNoteShownContent();

    /**
     * Check if the UI is on edit note mode.
     */
    boolean onEditModeNote();


    /** Returns the NoteBook */
    ReadOnlyNoteBook getNoteBook();
    /**
     * Replaces the given note {@code target} with {@code editedNote}.
     * {@code target} must exist in the dictionote book.
     */
    void setNote(Note target, Note editedNote);


    /**
     * Set Note UI Configuration Interface
     */
    void setNoteContentConfig(NoteContentConfig noteContentConfig);

    //=========== Dictionary ===================================================================================
    /**
     * Returns true if a text with the same content as {@code content} exists in the dictionote book.
     */
    boolean hasContent(Content content);

    /**
     * Adds the given content.
     * {@code content} must not already exist in the dictionote book.
     */
    void addContent(Content content);

    /** Returns the Dictionary */
    ReadOnlyDictionary getDictionary();

    /**
     * Returns true if a definition with the same content as {@code definition} exists in the dictionote book.
     */
    boolean hasDefinition(Definition definition);

    /**
     * Adds the given definition.
     * {@code definition} must not already exist in the dictionote book.
     */
    void addDefinition(Definition definition);


    /**
     * Show the given content.
     */
    void showDictionaryContent(DisplayableContent content);

    /**
     * Set Dictionary UI Configuration Interface
     */
    void setDictionaryContentConfig(DictionaryContentConfig dictionaryContentConfig);

    /** Returns the DefinitionBook */
    ReadOnlyDefinitionBook getDefinitionBook();



    //=========== AddressBook ================================================================================
    /**
     * Replaces dictionote book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code contact} exists in the dictionote book.
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
     * Invokes the user's OS email client to send a new email to the given contact.
     * {@code contact} must exist in the contacts list.
     */
    void emailContact(Contact contact);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the dictionote book.
     * The person identity of {@code editedPerson} must not be the same as
     * another existing person in the dictionote book.
     */
    void setContact(Contact target, Contact editedContact);


    //=========== Filtered List Accessors =============================================================
    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Note> getFilteredNoteList();

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Contact> getFilteredContactList();

    /** Returns an unmodifiable view of the filtered list of content */
    ObservableList<Content> getFilteredContentList();

    /** Returns an unmodifiable view of the filtered list of definitions */
    ObservableList<Definition> getFilteredDefinitionList();

    /** Returns an unmodifiable view of the filtered list of current viewable list */
    ObservableList<? extends DisplayableContent> getFilteredCurrentDictionaryList();
    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredContactList(Predicate<Contact> predicate);

    /**
     * Updates the filter of the filtered note list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredNoteList(Predicate<Note> predicate);

    /**
     * Updates the filter of the filtered content list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredContentList(Predicate<Content> predicate);

    /**
     * Updates the filter of the filtered definition list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredDefinitionList(Predicate<Definition> predicate);

}
