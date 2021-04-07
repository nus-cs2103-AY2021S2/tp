package seedu.address.model;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.connection.PersonMeetingConnection;
import seedu.address.model.group.Group;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.ReadOnlyMeetingBook;
import seedu.address.model.meeting.UniqueMeetingList;
import seedu.address.model.note.Note;
import seedu.address.model.note.ReadOnlyNoteBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.ReadOnlyAddressBook;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.reminder.ReadOnlyReminderBook;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Meeting> PREDICATE_SHOW_ALL_MEETINGS = unused -> true;
    Predicate<Note> PREDICATE_SHOW_ALL_NOTES = unused -> true;

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


    // ======================= Person part of the person model interface ============================ //
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

    Set<Person> findPersonsInGroup(Group group);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    void sortFilteredPersonList(Comparator<Person> comparator);

    // ======================= Meeting part of the meeting Model interface ============================ //
    /**
     * Replaces meeting book data with the data in {@code meetingBook}.
     */
    void setMeetingBook(ReadOnlyMeetingBook meetingBook);

    /** Returns the Book */
    ReadOnlyMeetingBook getMeetingBook();

    /**
     * Returns true if a meeting with the same identity as {@code meeting} exists in the address book.
     */
    boolean hasMeeting(Meeting meeting);


    /**
     * Deletes the given meeting.
     * The meeting must exist in the meeting book.
     */
    void deleteMeeting(Meeting target);

    /**
     * Adds the given meeting.
     * {@code meeting} must not already exist in the meeting book.
     */
    void addMeeting(Meeting meeting);

    /**
     * Replaces the given meeting {@code target} with {@code editedMeeting}.
     * {@code target} must exist in the meeting book.
     * The meeting identity of {@code editedMeeting} must not be the same as another existing
     * meeting in the meeting book.
     */
    void setMeeting(Meeting target, Meeting editedMeeting);

    void updateMeeting(Meeting target, Meeting editedMeeting);

    /** Returns an unmodifiable view of the filtered meeting list */
    ObservableList<Meeting> getFilteredMeetingList();

    /**
     * Updates the filter of the filtered meeting list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredMeetingList(Predicate<Meeting> predicate);

    void sortFilteredMeetingList(Comparator<Meeting> comparator);

    /**
     * Returns the internal meeting list of meeting book as an unmodifiable meeting list.
     */
    ObservableList<Meeting> getUnmodifiableMeetingList();

        // ============= Clashing Meetings  ========================================================

    /**
     * Checks if there is a clash in meeting times within the model.
     */
    public boolean clashes(Meeting toCheck);

    public boolean clashesExceptOne(Meeting meetingNotIncluded, Meeting toCheck);

    /**
     * Gets a list of meetings from the model that overlap with this meeting.
     */
    public List<Meeting> getClashes(Meeting toCheck);

    /**
     * Gets the meeting ( if any ) scheduled  at this point in time in the model.
     */
    public Optional<Meeting> getMeetingAtInstant(LocalDateTime localDateTime);


    // ============= PersonMeetingConnection part of the meeting Model interface ================== //
    /**
     * Replaces person meeting connection data with the data in {@code PersonMeetingConnection}.
     */
    void setPersonMeetingConnection(PersonMeetingConnection connection);

    /** Returns the connection */
    PersonMeetingConnection getPersonMeetingConnection();

    /**
     * Returns true if a given person and a given meeting exist a connection.
     */
    boolean hasPersonMeetingConnection(Person person, Meeting meeting);

    /**
     * Adds a connection between a person and a meeting.
     */
    void addPersonMeetingConnection(Person person, Meeting meeting);

    /**
     * This method delete a single connection between a meeting and a person.
     */
    void deleteSinglePersonMeetingConnection(Person person, Meeting meeting);

    /**
     * This method delete a all connections related to a given person.
     */
    void deleteAllPersonMeetingConnectionByPerson(Person person);

    /**
     * This method delete a all connections related to a given meeting.
     */
    void deleteAllPersonMeetingConnectionByMeeting(Meeting meeting);

    /**
     * Returns a Observable meeting list object with the person as the key.
     * Empty list will be returned if there is no value found in the hashMap.
     */
    ObservableList<Meeting> getFilteredMeetingListByPersonConnection(Person person);
    /**
     * Returns a Observable person list object with the meeting as the key.
     * Empty list will be returned if there is no value found in the hashMap.
     */
    ObservableList<Person> getFilteredPersonListByMeetingConnection(Meeting meeting);
    /**
     * Returns a Unique meeting list object with the person as the key.
     * Empty list will be returned if there is no value found in the hashMap.
     */
    UniqueMeetingList getUniqueMeetingListByPersonConnection(Person person);
    /**
     * Returns a Unique person list object with the meeting as the key.
     * Empty list will be returned if there is no value found in the hashMap.
     */
    UniquePersonList getUniquePersonListByMeetingConnection(Meeting meeting);

    // ======================= Note part of the note Model interface ============================ //

    /**
     * Replaces note book data with the data in {@code noteBook}.
     */
    void setNoteBook(ReadOnlyNoteBook noteBook);

    /** Returns the NoteBook */
    ReadOnlyNoteBook getNoteBook();

    /**
     * Returns true if a note with the same content as {@code note} exists in the note book.
     */
    boolean hasNote(Note note);

    /**
     * Deletes the given note.
     * The note must exist in the note book.
     */
    void deleteNote(Note target);

    /**
     * Adds the given note.
     * {@code note} must not already exist in the note book.
     */
    void addNote(Note note);

    /**
     * Replaces the given note {@code target} with {@code editedNote}.
     * {@code target} must exist in the note book.
     * The content of {@code editedNote} must not be the same as another existing note in the note book.
     */
    void setNote(Note target, Note editedNote);

    /** Returns an unmodifiable view of the filtered note list */
    ObservableList<Note> getFilteredNoteList();

    /**
     * Updates the filter of the filtered note list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredNoteList(Predicate<Note> predicate);

    Path getNoteBookFilePath();

    void setNoteBookFilePath(Path noteBookFilePath);

    //============================= Timetable settings =====================================

    /**
     * sets the timetable start date.
     * @param localDate
     */
    public void setTimetableStartDate(LocalDate localDate);

    /**
     * Get a read-only observable for the timetable start date.
     * @return the observable value of the start date.
     */
    public ObservableValue<LocalDate> getReadOnlyTimetableStartDate();

    // ------ Reminders ------

    ReadOnlyReminderBook getReminderBook();

    void refreshReminderBook();


}
