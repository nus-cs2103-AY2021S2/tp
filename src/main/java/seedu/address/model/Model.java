package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.contact.Contact;
import seedu.address.model.entry.Entry;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Entry> PREDICATE_SHOW_ALL_ENTRIES = unused -> true;

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

    // ====== Contact ======

    /**
     * Returns true if a contact with the same identity as {@code contact} exists in Teaching Assistant.
     */
    boolean hasContact(Contact contact);

    /**
     * Deletes the given contact.
     * The contact must exist in Teaching Assistant.
     */
    void deleteContact(Contact target);

    /**
     * Adds the given contact.
     * {@code contact} must not already exist in Teaching Assistant.
     */
    void addContact(Contact contact);

    /**
     * Replaces the given contact {@code target} with {@code editedContact}.
     * {@code target} must exist in Teaching Assistant.
     * The contact identity of {@code editedContact} must not be the same as another existing
     * contact in Teaching Assistant.
     */
    void setContact(Contact target, Contact editedContact);

    /** Returns an unmodifiable view of the filtered contact list */
    ObservableList<Contact> getFilteredContactList();

    /**
     * Updates the filter of the filtered contact list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredContactList(Predicate<Contact> predicate);

    // ====== Entry ======

    /**
     * Returns true if the entry exists in the list.
     */
    boolean hasEntry(Entry entry);

    /**
     * Deletes the given entry.
     * The entry must exist in the list.
     */
    void deleteEntry(Entry entry);

    /**
     * Adds the given entry.
     * {@code entry} must not overlap with existing entries in the list.
     */
    void addEntry(Entry entry);

    /**
     * Replaces the given entry {@code target} with {@code editedEntry}.
     * {@code target} must exist in the list.
     * {@code editedEntry} must not overlap with existing entries in the list.
     */
    void setEntry(Entry target, Entry editedEntry);

    /**
     * Returns true if the given entry has dates overlapping with other entries in the list.
     */
    boolean isOverlappingEntry(Entry toAdd);

    /**
     * Removes all entries that are overdue.
     */
    void clearOverdueEntries();

    /** Returns an unmodifiable view of the filtered entry list. */
    ObservableList<Entry> getFilteredEntryList();

    /**
     * Updates the filter of the filtered entry list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEntryList(Predicate<Entry> predicate);

}
