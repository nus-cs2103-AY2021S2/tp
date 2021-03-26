package seedu.address.logic.commands.meetings;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.function.Predicate;
import org.junit.jupiter.api.Test;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.persons.AddPersonCommand;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.connection.PersonMeetingConnection;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingBook;
import seedu.address.model.meeting.ReadOnlyMeetingBook;
import seedu.address.model.meeting.UniqueMeetingList;
import seedu.address.model.person.Person;
import seedu.address.model.person.ReadOnlyAddressBook;
import seedu.address.model.person.UniquePersonList;
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
        Meeting validMeeting = new MeetingBuilder().build().setConnectionToPerson(connections);
        Meeting validMeeting2 = new MeetingBuilder().withName("Important Conference").build()
            .setConnectionToPerson(connections);

        CommandResult commandResult2 = new AddPersonCommand(validPerson).execute(modelStub);
        CommandResult commandResult1 = new AddMeetingCommand(validMeeting).execute(modelStub);

        assertEquals(String.format(AddMeetingCommand.MESSAGE_SUCCESS, validMeeting), commandResult1.getFeedbackToUser());
        assertEquals(String.format(AddPersonCommand.MESSAGE_SUCCESS, validPerson), commandResult2.getFeedbackToUser());

        UniqueMeetingList expectedMeetings = new UniqueMeetingList();
        expectedMeetings.add(validMeeting);

        UniquePersonList expectedPersons = new UniquePersonList();
        expectedPersons.add(validPerson);

        // Check the meetings and persons
        assertEquals(expectedMeetings, modelStub.meetingsAdded);
        assertEquals(expectedPersons, modelStub.personsAdded);

        // Check their connections
        assertEquals(expectedMeetings.asUnmodifiableObservableList(), modelStub.getFilteredMeetingListByPersonConnection(validPerson));
        assertEquals(expectedPersons.asUnmodifiableObservableList(), modelStub.getFilteredPersonListByMeetingConnection(validMeeting));

        // Check more complex connections (2 meetings points to the same person)
        expectedMeetings.add(validMeeting2);
        CommandResult commandResult3 = new AddMeetingCommand(validMeeting2).execute(modelStub);
        assertEquals(expectedMeetings.asUnmodifiableObservableList(), modelStub.getFilteredMeetingListByPersonConnection(validPerson));
        assertEquals(expectedPersons.asUnmodifiableObservableList(), modelStub.getFilteredPersonListByMeetingConnection(validMeeting));

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
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
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
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
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
        public ObservableList<Meeting> getFilteredMeetingList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredMeetingList(Predicate<Meeting> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPersonMeetingConnection(PersonMeetingConnection connection) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public PersonMeetingConnection getPersonMeetingConnection() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPersonMeetingConnection(Person person, Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPersonMeetingConnection(Person person, Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteSinglePersonMeetingConnection(Person person, Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAllPersonMeetingConnectionByPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAllPersonMeetingConnectionByMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Meeting> getFilteredMeetingListByPersonConnection(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonListByMeetingConnection(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that always accept the person being added. Also, should always accept meeting being added, as well as their connection.
     */
    private class MeetingModelStubAcceptingAdded extends seedu.address.logic.commands.meetings.AddMeetingCommandWithConnectionTest.ModelStub {
        final UniqueMeetingList meetingsAdded = new UniqueMeetingList();
        final UniquePersonList personsAdded = new UniquePersonList();
        final PersonMeetingConnection connection = new PersonMeetingConnection();
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

        @Override
        public boolean hasMeeting(Meeting meeting) {
            requireNonNull(meeting);
            return meetingsAdded.contains(meeting);
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.contains(person);
        }

        @Override
        public void addMeeting(Meeting meeting) {
            requireNonNull(meeting);
            meetingsAdded.add(meeting);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        /**
         * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
         * {@code versionedAddressBook}
         */
        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return personsAdded.asUnmodifiableObservableList();
        }

        @Override
        public ReadOnlyMeetingBook getMeetingBook() {
            return new MeetingBook();
        }
    }
}


