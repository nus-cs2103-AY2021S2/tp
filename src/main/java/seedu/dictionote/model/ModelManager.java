package seedu.dictionote.model;

import static java.util.Objects.requireNonNull;
import static seedu.dictionote.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.dictionote.commons.core.GuiSettings;
import seedu.dictionote.commons.core.LogsCenter;
import seedu.dictionote.model.contact.Contact;
import seedu.dictionote.model.dictionary.Content;
import seedu.dictionote.model.dictionary.Definition;
import seedu.dictionote.model.dictionary.DisplayableContent;
import seedu.dictionote.model.note.Note;
import seedu.dictionote.ui.DictionaryContentConfig;
import seedu.dictionote.ui.NoteContentConfig;

/**
 * Represents the in-memory model of the dictionote book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Note> filteredNote;
    private final NoteBook noteBook;
    private final FilteredList<Contact> filteredContacts;
    private final Dictionary dictionary;
    private final FilteredList<Content> filteredContent;
    private final DefinitionBook definitionBook;
    private final FilteredList<Definition> filteredDefinition;
    private DictionaryContentConfig dictionaryContentConfig;
    private NoteContentConfig noteContentConfig;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs,
                        ReadOnlyNoteBook noteBook, ReadOnlyDictionary dictionary,
                        ReadOnlyDefinitionBook definitionBook) {
        super();
        requireAllNonNull(addressBook, userPrefs, noteBook, dictionary, definitionBook);

        logger.fine("Initializing with dictionote book: " + addressBook
                + " and user prefs " + userPrefs
                + " and note book " + noteBook
                + " and dictionary content " + dictionary
                + " and definition book " + definitionBook);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.noteBook = new NoteBook(noteBook);
        this.dictionary = new Dictionary(dictionary);
        this.definitionBook = new DefinitionBook(definitionBook);
        filteredNote = new FilteredList<>(this.noteBook.getNoteList());
        filteredContacts = new FilteredList<>(this.addressBook.getContactList());
        filteredContent = new FilteredList<>(this.dictionary.getContentList());
        filteredDefinition = new FilteredList<>(this.definitionBook.getDefinitionList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new NoteBook(), new Dictionary(), new DefinitionBook());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }
    @Override
    public Path getNoteBookFilePath() {
        return userPrefs.getNoteBookFilePath();
    }

    @Override
    public void setNoteBookFilePath(Path noteBookFilePath) {
        requireNonNull(noteBookFilePath);
        userPrefs.setAddressBookFilePath(noteBookFilePath);
    }

    @Override
    public Path getDictionaryFilePath() {
        return userPrefs.getDictionaryFilePath();
    }

    @Override
    public void setDictionaryFilePath(Path dictionaryFilePath) {
        requireNonNull(dictionaryFilePath);
        userPrefs.setDictionaryFilePath(dictionaryFilePath);
    }

    @Override
    public Path getDefinitionBookFilePath() {
        return userPrefs.getDefinitionBookFilePath();
    }

    @Override
    public void setDefinitionBookFilePath(Path definitionBookFilePath) {
        requireNonNull(definitionBookFilePath);
        userPrefs.setDefinitionBookFilePath(definitionBookFilePath);
    }

    //=========== NoteBook ===================================================================================
    @Override
    public boolean hasNote(Note note) {
        requireNonNull(note);
        return noteBook.hasNote(note);
    }

    @Override
    public void addNote(Note note) {
        noteBook.addNote(note);
        updateFilteredNoteList(PREDICATE_SHOW_ALL_NOTES);
    }

    @Override
    public void deleteNote(Note note) {
        noteBook.deleteNote(note);
        updateFilteredNoteList(PREDICATE_SHOW_ALL_NOTES);
    }

    @Override
    public void showNote(Note note) {
        requireAllNonNull(note);
        requireAllNonNull(noteContentConfig);
        noteContentConfig.setNote(note);
    }

    @Override
    public ReadOnlyNoteBook getNoteBook() {
        return noteBook;
    }

    @Override
    public void setNote(Note target, Note editedContact) {
        requireAllNonNull(target, editedContact);
        noteBook.setNote(target, editedContact);
    }

    @Override
    public void setNoteContentConfig(NoteContentConfig noteContentConfig) {
        requireAllNonNull(noteContentConfig);
        this.noteContentConfig = noteContentConfig;
    }

    public void sortNote() {
        noteBook.sortNote();
    }
    //=========== Dictionary ===================================================================================
    @Override
    public boolean hasContent(Content content) {
        requireNonNull(content);
        return dictionary.hasContent(content);
    }

    @Override
    public void addContent(Content content) {
        requireNonNull(content);
        updateFilteredContentList(PREDICATE_SHOW_ALL_CONTENTS);
    }

    @Override
    public ReadOnlyDictionary getDictionary() {
        return dictionary;
    }

    @Override
    public boolean hasDefinition(Definition definition) {
        requireNonNull(definition);
        return definitionBook.hasDefinition(definition);
    }

    @Override
    public void addDefinition(Definition definition) {
        requireNonNull(definition);
        updateFilteredDefinitionList(PREDICATE_SHOW_ALL_DEFINITION);
    }

    @Override
    public ReadOnlyDefinitionBook getDefinitionBook() {
        return definitionBook;
    }

    @Override
    public void showDictionaryContent(DisplayableContent content) {
        requireAllNonNull(content);
        requireAllNonNull(dictionaryContentConfig);
        dictionaryContentConfig.setDisplayContent(content);
    }

    @Override
    public void setDictionaryContentConfig(DictionaryContentConfig dictionaryContentConfig) {
        requireAllNonNull(dictionaryContentConfig);
        this.dictionaryContentConfig = dictionaryContentConfig;
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasContact(Contact contact) {
        requireNonNull(contact);
        return addressBook.hasContact(contact);
    }

    @Override
    public void deleteContact(Contact target) {
        addressBook.removeContact(target);
    }

    @Override
    public void addContact(Contact contact) {
        addressBook.addContact(contact);
        updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
    }

    @Override
    public void emailContact(Contact contact) {
        requireNonNull(contact);
        addressBook.emailContact(contact);
    }

    @Override
    public void setContact(Contact target, Contact editedContact) {
        requireAllNonNull(target, editedContact);

        addressBook.setContact(target, editedContact);
    }

    //=========== Filtered List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Contact> getFilteredContactList() {
        return filteredContacts;
    }

    @Override
    public ObservableList<Note> getFilteredNoteList() {
        return filteredNote;
    }

    @Override
    public ObservableList<Content> getFilteredContentList() {
        return filteredContent;
    }

    @Override
    public ObservableList<Definition> getFilteredDefinitionList() {
        return filteredDefinition;
    }

    @Override
    public ObservableList<? extends DisplayableContent> getFilteredCurrentDictionaryList() {

        return dictionaryContentConfig.isContentVisible() ? filteredContent : filteredDefinition;
    }

    @Override
    public void updateFilteredContactList(Predicate<Contact> predicate) {
        requireNonNull(predicate);
        filteredContacts.setPredicate(predicate);
    }

    @Override
    public void updateFilteredNoteList(Predicate<Note> predicate) {
        requireNonNull(predicate);
        filteredNote.setPredicate(predicate);
    }

    @Override
    public void updateFilteredContentList(Predicate<Content> predicate) {
        requireNonNull(predicate);
        filteredContent.setPredicate(predicate);
    }

    @Override
    public void updateFilteredDefinitionList(Predicate<Definition> predicate) {
        requireNonNull(predicate);
        filteredDefinition.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredContacts.equals(other.filteredContacts);
    }
}
