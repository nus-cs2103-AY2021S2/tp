package seedu.address.logic.commands.connections;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMeetings.MEETING1;
import static seedu.address.testutil.TypicalMeetings.MEETING2;
import static seedu.address.testutil.TypicalMeetings.MEETING3;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyUserPrefs;
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

class AddPersonToMeetingConnectionCommandTest {
    private static String MESSAGE_SUCCESS = "Successfully add persons related to the meeting! "
            + "The possible duplication of persons related is automatically removed.";
    private PersonMeetingConnection connection = new PersonMeetingConnection();
    private Set<Person> personSet = new HashSet<>();
    private Set<Meeting> meetingSet = new HashSet<>();
    public AddPersonToMeetingConnectionCommandTest() {
        connection.addPersonMeetingConnection(AMY, MEETING1);
        connection.addPersonMeetingConnection(AMY, MEETING2);
        connection.addPersonMeetingConnection(AMY, MEETING3);
        connection.addPersonMeetingConnection(BOB, MEETING1);
        connection.addPersonMeetingConnection(BOB, MEETING2);
        personSet.add(AMY);
        personSet.add(BOB);
        meetingSet.add(MEETING1);
        meetingSet.add(MEETING2);
        meetingSet.add(MEETING3);
    }

    @Test
    public void constructor_nullConnection_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPersonToMeetingConnectionCommand(null, null));
    }

    @Test
    public void execute_connectionAcceptedByModel_addSuccessful() throws Exception {
        Model modelStub = new ModelStub(personSet, meetingSet, connection);

        // Extract the index for BOB, which is NO.2.
        Index personIndex = ParserUtil.parseIndex("2");
        Set<Index> personIndexSet = new HashSet<>();
        personIndexSet.add(personIndex);
        // Extract the index for Meeting3, which is NO.3.
        Index meetingIndex = ParserUtil.parseIndex("3");

        CommandResult commandResult = new AddPersonToMeetingConnectionCommand(meetingIndex,
                personIndexSet).execute(modelStub);

        assertEquals(MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_connectionAcceptedByModel_addFail() throws Exception {
        Model modelStub = new ModelStub(personSet, meetingSet, connection);

        // The meeting index is wrong.
        Index invalidMeetingIndex1 = ParserUtil.parseIndex("4");
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("0"));
        Index personIndex = ParserUtil.parseIndex("2");
        Set<Index> personIndexSet = new HashSet<>();
        personIndexSet.add(personIndex);

        assertThrows(CommandException.class, () ->
                new AddPersonToMeetingConnectionCommand(invalidMeetingIndex1, personIndexSet).execute(modelStub));

        // The person index is wrong.
        Index invalidPersonIndex1 = ParserUtil.parseIndex("3");
        Index meetingIndex = ParserUtil.parseIndex("3");
        Set<Index> invalidPersonIndexSet1 = new HashSet<>();
        Set<Index> invalidPersonIndexSet2 = new HashSet<>();
        invalidPersonIndexSet1.add(invalidPersonIndex1);

        assertThrows(CommandException.class, () ->
                new AddPersonToMeetingConnectionCommand(meetingIndex, invalidPersonIndexSet1).execute(modelStub));
        assertThrows(CommandException.class, () ->
                new AddPersonToMeetingConnectionCommand(meetingIndex, invalidPersonIndexSet2).execute(modelStub));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        private final List<Person> filteredPersons;
        private final List<Meeting> filteredMeetings;
        private final PersonMeetingConnection connection;

        public ModelStub(Set<Person> personSet, Set<Meeting> meetingSet, PersonMeetingConnection connection) {
            filteredMeetings = new ArrayList<>();
            filteredPersons = new ArrayList<>();
            filteredPersons.addAll(personSet);
            filteredMeetings.addAll(meetingSet);
            this.connection = connection;
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Set<Person> findPersonsInGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            UniquePersonList upl = new UniquePersonList();
            for (Person person : filteredPersons) {
                upl.add(person);
            }
            return upl.asUnmodifiableObservableList();
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortFilteredPersonList(Comparator<Person> comparator) {

        }

        @Override
        public void setMeetingBook(ReadOnlyMeetingBook meetingBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyMeetingBook getMeetingBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteMeeting(Meeting target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMeeting(Meeting target, Meeting editedMeeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateMeeting(Meeting target, Meeting editedMeeting) {
            filteredMeetings.remove(target);
            filteredMeetings.add(editedMeeting);
        }

        @Override
        public ObservableList<Meeting> getFilteredMeetingList() {
            UniqueMeetingList uml = new UniqueMeetingList();
            for (Meeting meeting : filteredMeetings) {
                uml.add(meeting);
            }
            return uml.asUnmodifiableObservableList();
        }

        @Override
        public void updateFilteredMeetingList(Predicate<Meeting> predicate) {
            requireNonNull(predicate);
        }

        @Override
        public void sortFilteredMeetingList(Comparator<Meeting> comparator) {

        }

        @Override
        public ObservableList<Meeting> getUnmodifiableMeetingList() {
            throw new AssertionError("This method should not be called");
        }

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

        //=======================Timetable methods ================================================

        @Override
        public void setTimetableStartDate(LocalDate localDate) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public ObservableValue<LocalDate> getReadOnlyTimetableStartDate() {
            throw new AssertionError("This method should not be called");
        }

        //-============================================================================================



        @Override
        public ReadOnlyReminderBook getReminderBook() {
            return null;
        }

        @Override
        public void refreshReminderBook() {

        }

        @Override
        public boolean clashes(Meeting toCheck) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean clashesExceptOne(Meeting meetingNotIncluded, Meeting toCheck) {
            return false;
        }

        /**
         * Gets a list of meetings from the model that overlap with this meeting.
         */
        public List<Meeting> getClashes(Meeting toCheck) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Gets the meeting ( if any ) scheduled  at this point in time in the model.
         */
        public Optional<Meeting> getMeetingAtInstant(LocalDateTime localDateTime) {
            throw new AssertionError("This method should not be called.");
        }


    }
}
