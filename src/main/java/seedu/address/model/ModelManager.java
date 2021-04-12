package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.contact.Contact;
import seedu.address.model.entry.Entry;

/**
 * Represents the in-memory model of the Teaching Assistant data.
 */
public class ModelManager implements Model {

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TeachingAssistant teachingAssistant;
    private final UserPrefs userPrefs;
    private final FilteredList<Contact> filteredContacts;
    private final FilteredList<Entry> filteredEntries;

    /**
     * Initializes a ModelManager with the given teachingAssistant and userPrefs.
     */
    public ModelManager(ReadOnlyTeachingAssistant teachingAssistant, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(teachingAssistant, userPrefs);

        logger.fine("Initializing with teaching assistant: " + teachingAssistant + " and user prefs " + userPrefs);

        this.teachingAssistant = new TeachingAssistant(teachingAssistant);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredContacts = new FilteredList<>(this.teachingAssistant.getContactList());
        filteredEntries = new FilteredList<>(this.teachingAssistant.getEntryList());
    }

    public ModelManager() {
        this(new TeachingAssistant(), new UserPrefs());
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
    public Path getTeachingAssistantFilePath() {
        return userPrefs.getTeachingAssistantFilePath();
    }

    @Override
    public void setTeachingAssistantFilePath(Path teachingAssistantFilePath) {
        requireNonNull(teachingAssistantFilePath);
        userPrefs.setTeachingAssistantFilePath(teachingAssistantFilePath);
    }

    // ====== Teaching Assistant ======

    @Override
    public void setTeachingAssistant(ReadOnlyTeachingAssistant teachingAssistant) {
        this.teachingAssistant.resetData(teachingAssistant);
    }

    @Override
    public ReadOnlyTeachingAssistant getTeachingAssistant() {
        return teachingAssistant;
    }

    // ====== Contact ======

    @Override
    public boolean hasContact(Contact contact) {
        requireNonNull(contact);
        return teachingAssistant.hasContact(contact);
    }

    @Override
    public void deleteContact(Contact target) {
        teachingAssistant.removeContact(target);
    }

    @Override
    public void addContact(Contact contact) {
        teachingAssistant.addContact(contact);
        updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
    }

    @Override
    public void setContact(Contact target, Contact editedContact) {
        requireAllNonNull(target, editedContact);

        teachingAssistant.setContact(target, editedContact);
    }

    // ====== Entry ======

    @Override
    public boolean hasEntry(Entry entry) {
        return teachingAssistant.hasEntry(entry);
    }

    @Override
    public void addEntry(Entry entry) {
        teachingAssistant.addEntry(entry);
        updateFilteredEntryList(PREDICATE_SHOW_ALL_ENTRIES);
    }

    @Override
    public void deleteEntry(Entry entry) {
        teachingAssistant.removeEntry(entry);
    }

    @Override
    public void setEntry(Entry target, Entry editedEntry) {
        requireAllNonNull(target, editedEntry);
        teachingAssistant.setEntry(target, editedEntry);
    }

    @Override
    public boolean isOverlappingEntry(Entry toAdd) {
        requireNonNull(toAdd);
        return teachingAssistant.isOverlappingEntry(toAdd);
    }

    @Override
    public void clearOverdueEntries() {
        teachingAssistant.clearOverdueEntries();
    }

    //=========== Filtered Contact List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Contact} backed by the internal list of
     * {@code versionedTeachingAssistant}.
     */
    @Override
    public ObservableList<Contact> getFilteredContactList() {
        return filteredContacts;
    }

    @Override
    public void updateFilteredContactList(Predicate<Contact> predicate) {
        requireNonNull(predicate);
        filteredContacts.setPredicate(predicate);
    }

    //=========== Filtered Entry List Accessors ==============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Entry} backed by the internal list of
     * {@code versionedTeachingAssistant}.
     */
    @Override
    public ObservableList<Entry> getFilteredEntryList() {
        return filteredEntries;
    }

    @Override
    public void updateFilteredEntryList(Predicate<Entry> predicate) {
        requireNonNull(predicate);
        filteredEntries.setPredicate(predicate);
    }

    //=========== Misc ===============

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
        return teachingAssistant.equals(other.teachingAssistant)
                && userPrefs.equals(other.userPrefs)
                && filteredContacts.equals(other.filteredContacts)
                && filteredEntries.equals(other.filteredEntries);
    }
}
