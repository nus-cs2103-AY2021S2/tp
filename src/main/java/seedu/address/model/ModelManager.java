package seedu.address.model;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.connection.PersonMeetingConnection;
import seedu.address.model.group.Group;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingBook;
import seedu.address.model.meeting.ReadOnlyMeetingBook;
import seedu.address.model.meeting.UniqueMeetingList;
import seedu.address.model.note.Note;
import seedu.address.model.note.NoteBook;
import seedu.address.model.note.ReadOnlyNoteBook;
import seedu.address.model.person.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.ReadOnlyAddressBook;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.reminder.ReadOnlyReminderBook;
import seedu.address.model.reminder.ReminderBook;
import seedu.address.model.schedule.TimetablePrefs;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final SortedList<Person> sortedBeforeFilterPersons;
    private final FilteredList<Person> filteredPersons;

    // TODO: Modify the signature of ModelManager so that we c
    //  an add meetings inside it.
    private final MeetingBook meetingBook;
    private final SortedList<Meeting> sortedBeforeFilterMeetings;
    private final FilteredList<Meeting> filteredMeetings;

    // TODO: Modify the signature of ModelManager so that we can add connection inside it.
    private final PersonMeetingConnection connection;

    private final ReminderBook reminderBook;

    //===============  Note ===========================================================
    private final NoteBook noteBook;
    private final FilteredList<Note> filteredNotes;

    //===============  Timetable ===========================================================
    private final TimetablePrefs timetablePrefs;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     * Sets the MeetingBook to be empty
     * Sets the Person- Meeting Connection to be empty
     * Sets the timetable preferences to be default date today's date.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.meetingBook = new MeetingBook();
        this.sortedBeforeFilterMeetings = new SortedList<>(this.meetingBook.getMeetingList());
        this.filteredMeetings = new FilteredList<Meeting>(sortedBeforeFilterMeetings);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.sortedBeforeFilterPersons = new SortedList<>(this.addressBook.getPersonList());
        filteredPersons = new FilteredList<>(sortedBeforeFilterPersons);
        // TODO: Modify the signature of ModelManager so that we can add connection inside it.
        this.reminderBook = new ReminderBook(this.meetingBook);
        this.connection = new PersonMeetingConnection();

        //================== NoteBook ==================================================================
        this.noteBook = new NoteBook();
        this.filteredNotes = new FilteredList<>(this.noteBook.getNoteList());

        //================== Timetable ==================================================================
        //default initializes to current localdate
        timetablePrefs = new TimetablePrefs(LocalDate.now());
    }


    /**
     * Initializes a ModelManager with the given addressBook, meetingBook, userPrefs and PersonMeetingConnection
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyMeetingBook meetingBook,
                        ReadOnlyNoteBook noteBook, ReadOnlyUserPrefs userPrefs,
                        PersonMeetingConnection connection) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.meetingBook = new MeetingBook(meetingBook);
        this.sortedBeforeFilterMeetings = new SortedList<>(this.meetingBook.getMeetingList());
        this.filteredMeetings = new FilteredList<Meeting>(sortedBeforeFilterMeetings);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.sortedBeforeFilterPersons = new SortedList<>(this.addressBook.getPersonList());
        filteredPersons = new FilteredList<>(sortedBeforeFilterPersons);
        // TODO: Modify the signature of ModelManager so that we can add connection inside it.
        this.reminderBook = new ReminderBook(this.meetingBook);

        //============ Set Connection ===========================================================
        this.connection = connection;
        this.meetingBook.setPersonToMeetingConnections(connection);

        //================== Note ==================================================================
        this.noteBook = new NoteBook(noteBook);
        this.filteredNotes = new FilteredList<>(this.noteBook.getNoteList());

        //================== Timetable ==================================================================
        //default initializes to current localdate
        timetablePrefs = new TimetablePrefs(LocalDate.now());

    }


    public ModelManager() {
        this(new AddressBook(), new MeetingBook(), new NoteBook(), new UserPrefs(), new PersonMeetingConnection());
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
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public Set<Person> findPersonsInGroup(Group group) {
        return addressBook.findPersonsInGroup(group);
    }

    //=========== MeetingBook ================================================================================

    @Override
    public void setMeetingBook(ReadOnlyMeetingBook meetingBook) {
        this.meetingBook.resetData(meetingBook);
    }

    @Override
    public ReadOnlyMeetingBook getMeetingBook() {
        return meetingBook;
    }

    @Override
    public boolean hasMeeting(Meeting meeting) {
        requireNonNull(meeting);
        return meetingBook.hasMeeting(meeting);
    }

    @Override
    public void deleteMeeting(Meeting target) {
        meetingBook.removeMeeting(target);
    }

    @Override
    public void addMeeting(Meeting meeting) {
        meetingBook.addMeeting(meeting);
        updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS);
    }

    @Override
    public void setMeeting(Meeting target, Meeting editedMeeting) {
        requireAllNonNull(target, editedMeeting);
        meetingBook.setMeeting(target, editedMeeting);
    }
    @Override
    public void updateMeeting(Meeting target, Meeting editedMeeting) {
        requireAllNonNull(target, editedMeeting);
        meetingBook.updateMeeting(target, editedMeeting);
    }

    /**
     * Returns the unmodifiable list of all meetings
     */
    public ObservableList<Meeting> getUnmodifiableMeetingList() {
        return this.meetingBook.getMeetingList();
    }

    //TODO: Set MeetingBook file path in userPrefs? low priority feature(nice to have)

    //========= Clashing Meetings ================================================================

    /**
     * Checks if there is a clash in meeting times within the model.
     */
    public boolean clashes(Meeting toCheck) {
        return meetingBook.clashes(toCheck);
    }

    public boolean clashesExceptOne(Meeting meetingNotIncluded, Meeting toCheck) {
        return meetingBook.clashesExceptOne(meetingNotIncluded, toCheck);
    }

    /**
     * Gets a list of meetings from the model that overlap with this meeting.
     */
    public List<Meeting> getClashes(Meeting toCheck) {
        return meetingBook.getClashes(toCheck);
    }

    /**
     * Gets the meeting ( if any ) scheduled  at this point in time in the model.
     */
    public Optional<Meeting> getMeetingAtInstant(LocalDateTime localDateTime) {
        return meetingBook.getMeetingAtInstant(localDateTime);
    }

    // ============= PersonMeetingConnection =======================
    /**
     * Replaces person meeting connection data with the data in {@code PersonMeetingConnection}.
     */
    @Override
    public void setPersonMeetingConnection(PersonMeetingConnection connection) {
        requireNonNull(connection);
        this.connection.resetData(connection);
    }

    /** Returns the connection */
    @Override
    public PersonMeetingConnection getPersonMeetingConnection() {
        return connection;
    };

    /**
     * Returns true if a given person and a given meeting exist a connection.
     */
    @Override
    public boolean hasPersonMeetingConnection(Person person, Meeting meeting) {
        return connection.existPersonMeetingConnection(person, meeting);
    }

    /**
     * Adds a connection between a person and a meeting.
     */
    @Override
    public void addPersonMeetingConnection(Person person, Meeting meeting) {
        connection.addPersonMeetingConnection(person, meeting);
    }

    /**
     * This method delete a single connection between a meeting and a person.
     */
    @Override
    public void deleteSinglePersonMeetingConnection(Person person, Meeting meeting) {
        connection.deleteSinglePersonMeetingConnection(person, meeting);
    }

    /**
     * This method delete a all connections related to a given person.
     */
    @Override
    public void deleteAllPersonMeetingConnectionByPerson(Person person) {
        connection.deleteAllPersonMeetingConnectionByPerson(person);
    };

    /**
     * This method delete a all connections related to a given meeting.
     */
    @Override
    public void deleteAllPersonMeetingConnectionByMeeting(Meeting meeting) {
        connection.deleteAllPersonMeetingConnectionByMeeting(meeting);
    }

    //TODO: This two methods below may need further change because I don't know how it works with GUI.(Yuheng)
    /**
     * Returns a Observable meeting list object with the person as the key.
     * Empty list will be returned if there is no value found in the hashMap.
     */
    @Override
    public ObservableList<Meeting> getFilteredMeetingListByPersonConnection(Person person) {
        UniqueMeetingList meetings = connection.getMeetingsByPerson(person);
        assert meetings != null;
        return meetings.asUnmodifiableObservableList();
    }
    /**
     * Returns a Observable person list object with the meeting as the key.
     * Empty list will be returned if there is no value found in the hashMap.
     */
    public ObservableList<Person> getFilteredPersonListByMeetingConnection(Meeting meeting) {
        UniquePersonList persons = connection.getPersonsByMeeting(meeting);
        assert persons != null;
        return persons.asUnmodifiableObservableList();
    }
    /**
     * Returns a Unique meeting list object with the person as the key.
     * Empty list will be returned if there is no value found in the hashMap.
     */
    @Override
    public UniqueMeetingList getUniqueMeetingListByPersonConnection(Person person) {
        UniqueMeetingList meetings = connection.getMeetingsByPerson(person);
        return meetings;
    }
    /**
     * Returns a Unique person list object with the meeting as the key.
     * Empty list will be returned if there is no value found in the hashMap.
     */
    @Override
    public UniquePersonList getUniquePersonListByMeetingConnection(Meeting meeting) {
        UniquePersonList persons = connection.getPersonsByMeeting(meeting);
        return persons;
    }

    @Override
    public ReadOnlyReminderBook getReminderBook() {
        return reminderBook;
    }

    @Override
    public void refreshReminderBook() {
        reminderBook.refreshRemindersFromMeetings(this.meetingBook);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    public void sortFilteredPersonList(Comparator<Person> comparator) {
        sortedBeforeFilterPersons.setComparator(comparator);
    }


    //=========== Filtered Meeting List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Meeting} backed by the internal list of
     * {@code versionedMeetingBook}
     */
    @Override
    public ObservableList<Meeting> getFilteredMeetingList() {
        return filteredMeetings;
    }

    @Override
    public void updateFilteredMeetingList(Predicate<Meeting> predicate) {
        requireNonNull(predicate);
        filteredMeetings.setPredicate(predicate);
    }

    public void sortFilteredMeetingList(Comparator<Meeting> comparator) {
        sortedBeforeFilterMeetings.setComparator(comparator);
    }

    // ======================= Note part of the note Model interface ============================ //

    @Override
    public void setNoteBook(ReadOnlyNoteBook noteBook) {
        this.noteBook.resetData(noteBook);
    }

    @Override
    public ReadOnlyNoteBook getNoteBook() {
        return noteBook;
    }

    @Override
    public boolean hasNote(Note note) {
        requireNonNull(note);
        return noteBook.hasNote(note);
    }

    @Override
    public void deleteNote(Note target) {
        noteBook.removeNote(target);
    }

    @Override
    public void addNote(Note note) {
        noteBook.addNote(note);
        updateFilteredNoteList(PREDICATE_SHOW_ALL_NOTES);
    }

    @Override
    public void setNote(Note target, Note editedNote) {
        requireAllNonNull(target, editedNote);

        noteBook.setNote(target, editedNote);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Note} backed by the internal list of
     * {@code versionedNoteBook}
     */
    @Override
    public ObservableList<Note> getFilteredNoteList() {
        return filteredNotes;
    }

    @Override
    public void updateFilteredNoteList(Predicate<Note> predicate) {
        requireNonNull(predicate);
        filteredNotes.setPredicate(predicate);
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

    //================= Get timetable prefs methods ================================================

    @Override
    public void setTimetableStartDate(LocalDate localDate) {
        timetablePrefs.setTimetableStartDate(localDate);
    }

    @Override
    public ObservableValue<LocalDate> getReadOnlyTimetableStartDate() {
        return timetablePrefs.getReadOnlyStartDate();
    }


    //=========== Other methods =============================================================
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
                && filteredPersons.equals(other.filteredPersons)
                && meetingBook.equals(other.meetingBook)
                && filteredMeetings.equals(other.filteredMeetings)
                && noteBook.equals(other.noteBook)
                && timetablePrefs.equals(other.timetablePrefs);
    }

}
