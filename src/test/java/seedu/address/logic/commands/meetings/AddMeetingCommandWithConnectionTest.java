package seedu.address.logic.commands.meetings;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.persons.AddPersonCommand;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
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
import seedu.address.testutil.MeetingBuilder;
import seedu.address.testutil.PersonBuilder;


public class AddMeetingCommandWithConnectionTest {

    @Test
    public void execute_meetingPersonConnectionAcceptedByModel_addSuccessful() throws Exception {
        // Add the person and the meeting separately
        HashSet<Index> connections = new HashSet<>();
        connections.add(Index.fromOneBased(1));

        MeetingModelStubAcceptingAdded modelStub = new MeetingModelStubAcceptingAdded();
        Person validPerson = new PersonBuilder().build();
        Meeting validMeeting = new MeetingBuilder().build();
        Meeting validMeeting2 = new MeetingBuilder().withName("Important Conference").withStart("2222-01-01 19:00")
            .withTerminate("2222-01-01 20:00").build();

        CommandResult commandResult2 = new AddPersonCommand(validPerson).execute(modelStub);
        CommandResult commandResult1 = new AddMeetingCommand(validMeeting)
                .setConnectionToPerson(connections).execute(modelStub);

        assertEquals(String.format(AddMeetingCommand.MESSAGE_SUCCESS, validMeeting),
            commandResult1.getFeedbackToUser());
        assertEquals(String.format(AddPersonCommand.MESSAGE_SUCCESS, validPerson),
            commandResult2.getFeedbackToUser());

        UniqueMeetingList expectedMeetings = new UniqueMeetingList();
        expectedMeetings.add(validMeeting);

        UniquePersonList expectedPersons = new UniquePersonList();
        expectedPersons.add(validPerson);

        // Check their connections
        assertEquals(expectedMeetings.asUnmodifiableObservableList(),
            modelStub.getFilteredMeetingListByPersonConnection(validPerson));
        assertEquals(expectedPersons.asUnmodifiableObservableList(),
            modelStub.getFilteredPersonListByMeetingConnection(validMeeting));

        // Check more complex connections (2 meetings points to the same person)
        expectedMeetings.add(validMeeting2);
        CommandResult commandResult3 = new AddMeetingCommand(validMeeting2)
                .setConnectionToPerson(connections).execute(modelStub);
        assertEquals(expectedMeetings.asUnmodifiableObservableList(),
            modelStub.getFilteredMeetingListByPersonConnection(validPerson));
        assertEquals(expectedPersons.asUnmodifiableObservableList(),
            modelStub.getFilteredPersonListByMeetingConnection(validMeeting));

    }

    @Test
    public void equals() {
        Meeting meeting1 = new MeetingBuilder().withName("Meeting1").build();
        Meeting meeting2 = new MeetingBuilder().withName("Meeting2").build();
        AddMeetingCommand addMeeting1Command = new AddMeetingCommand(meeting1);
        AddMeetingCommand addMeeting2Command = new AddMeetingCommand(meeting2);

        // same object -> returns true
        assertTrue(addMeeting1Command.equals(addMeeting1Command));

        // same values -> returns true
        AddMeetingCommand addMeeting1CommandCopy = new AddMeetingCommand(meeting1);
        assertTrue(addMeeting1Command.equals(addMeeting1CommandCopy));

        // different types -> returns false
        assertFalse(addMeeting1Command.equals(1));

        // null -> returns false
        assertFalse(addMeeting1Command.equals(null));

        // different person -> returns false
        assertFalse(addMeeting1Command.equals(addMeeting2Command));
    }


    /**
     * A Model stub that always accept the person being added.
     * Also, should always accept meeting being added, as well as their connection.
     */
    private class MeetingModelStubAcceptingAdded implements Model {

        private final AddressBook addressBook;
        private final UserPrefs userPrefs;
        private final FilteredList<Person> filteredPersons;

        // TODO: Modify the signature of ModelManager so that we can add meetings inside it.
        private final MeetingBook meetingBook;
        private final FilteredList<Meeting> filteredMeetings;

        // TODO: Modify the signature of ModelManager so that we can add connection inside it.
        private final PersonMeetingConnection connection;

        private final NoteBook noteBook;
        private final FilteredList<Note> filteredNotes;

        private final TimetablePrefs timetablePrefs;


        /**
         * Initializes a ModelManager with the given addressBook, meetingBOok and userPrefs
         */
        public MeetingModelStubAcceptingAdded(ReadOnlyAddressBook addressBook, ReadOnlyMeetingBook meetingBook,
                                              ReadOnlyNoteBook noteBook, ReadOnlyUserPrefs userPrefs) {
            super();
            requireAllNonNull(addressBook, userPrefs);

            this.meetingBook = new MeetingBook(meetingBook);
            this.filteredMeetings = new FilteredList<Meeting>(this.meetingBook.getMeetingList());
            this.addressBook = new AddressBook(addressBook);
            this.userPrefs = new UserPrefs(userPrefs);
            filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
            this.noteBook = new NoteBook(noteBook);
            this.filteredNotes = new FilteredList<Note>(this.noteBook.getNoteList());
            // TODO: Modify the signature of ModelManager so that we can add connection inside it.
            this.connection = new PersonMeetingConnection();
            this.timetablePrefs = new TimetablePrefs(LocalDate.of(2020, 10, 10));
        }


        public MeetingModelStubAcceptingAdded() {
            this(new AddressBook(), new MeetingBook(), new NoteBook(), new UserPrefs());
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
        }


        //TODO: Set MeetingBook file path in userPrefs? low priority feature(nice to have)

        //========= Clashing Meetings ================================================================

        /**
         * Checks if there is a clash in meeting times within the model.
         */
        public boolean clashes(Meeting toCheck) {
            return meetingBook.clashes(toCheck);
        }

        @Override
        public boolean clashesExceptOne(Meeting meetingNotIncluded, Meeting toCheck) {
            return false;
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
        @Override
        public ObservableList<Person> getFilteredPersonListByMeetingConnection(Meeting meeting) {
            UniquePersonList persons = connection.getPersonsByMeeting(meeting);
            assert persons != null;
            return persons.asUnmodifiableObservableList();
        }

        @Override
        public UniqueMeetingList getUniqueMeetingListByPersonConnection(Person person) {
            return null;
        }

        @Override
        public UniquePersonList getUniquePersonListByMeetingConnection(Meeting meeting) {
            return null;
        }

        @Override
        public ReadOnlyReminderBook getReminderBook() {
            return new ReminderBook();
        }

        @Override
        public void refreshReminderBook() {}

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

        @Override
        public void sortFilteredPersonList(Comparator<Person> comparator) {

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

        @Override
        public void sortFilteredMeetingList(Comparator<Meeting> comparator) {

        }

        //======================= Note methods ================================================

        @Override
        public void setNoteBook(ReadOnlyNoteBook noteBook) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public ReadOnlyNoteBook getNoteBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasNote(Note note) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public void deleteNote(Note target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addNote(Note note) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setNote(Note target, Note editedNote) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Note> getFilteredNoteList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredNoteList(Predicate<Note> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getNoteBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setNoteBookFilePath(Path noteBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        //======================= Timetable methods ========================================

        @Override
        public void setTimetableStartDate(LocalDate localDate) {
            timetablePrefs.setTimetableStartDate(localDate);
        }

        @Override
        public ObservableValue<LocalDate> getReadOnlyTimetableStartDate() {
            return timetablePrefs.getReadOnlyStartDate();
        }

        @Override
        public ObservableList<Meeting> getUnmodifiableMeetingList() {
            return meetingBook.getMeetingList();
        }


    }
}
