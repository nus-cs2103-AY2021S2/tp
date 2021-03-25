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
import seedu.address.model.connection.PersonMeetingConnection;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingBook;
import seedu.address.model.meeting.ReadOnlyMeetingBook;
import seedu.address.model.meeting.UniqueMeetingList;
import seedu.address.model.person.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.ReadOnlyAddressBook;
import seedu.address.model.person.UniquePersonList;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    // TODO: Modify the signature of ModelManager so that we can add meetings inside it.
    private final MeetingBook meetingBook;
    private final FilteredList<Meeting> filteredMeetings;

    // TODO: Modify the signature of ModelManager so that we can add connection inside it.
    private final PersonMeetingConnection connection;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs. MeetingBook will be set to default.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.meetingBook = new MeetingBook();
        this.filteredMeetings = new FilteredList<Meeting>(this.meetingBook.getMeetingList());
        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        // TODO: Modify the signature of ModelManager so that we can add connection inside it.
        this.connection = new PersonMeetingConnection();
    }

    /**
     * Initializes a ModelManager with the given addressBook, meetingBOok and userPrefs
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyMeetingBook meetingBook,
                        ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.meetingBook = new MeetingBook(meetingBook);
        this.filteredMeetings = new FilteredList<Meeting>(this.meetingBook.getMeetingList());
        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        // TODO: Modify the signature of ModelManager so that we can add connection inside it.
        this.connection = new PersonMeetingConnection();
    }

    /**
     * Initializes a ModelManager with the given addressBook, meetingBook, userPrefs and PersonMeetingConnection
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyMeetingBook meetingBook,
                        ReadOnlyUserPrefs userPrefs, PersonMeetingConnection connection) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.meetingBook = new MeetingBook(meetingBook);
        this.filteredMeetings = new FilteredList<Meeting>(this.meetingBook.getMeetingList());
        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        // TODO: Modify the signature of ModelManager so that we can add connection inside it.
        this.connection = connection;
    }


    public ModelManager() {
        this(new AddressBook(), new MeetingBook(), new UserPrefs());
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
    //TODO: Set MeetingBook file path in userPrefs? low priority feature(nice to have)

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
                && filteredMeetings.equals(other.filteredMeetings);
    }

}
