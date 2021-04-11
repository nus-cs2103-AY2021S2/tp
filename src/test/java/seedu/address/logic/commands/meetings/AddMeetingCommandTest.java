package seedu.address.logic.commands.meetings;


import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.connection.PersonMeetingConnection;
import seedu.address.model.group.Group;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingBook;
import seedu.address.model.meeting.ReadOnlyMeetingBook;
import seedu.address.model.meeting.UniqueMeetingList;
import seedu.address.model.note.Note;
import seedu.address.model.note.ReadOnlyNoteBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.ReadOnlyAddressBook;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.reminder.ReadOnlyReminderBook;
import seedu.address.testutil.MeetingBuilder;

class AddMeetingCommandTest {
    @Test
    public void constructor_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddMeetingCommand(null));
    }

    @Test
    public void execute_meetingAcceptedByModel_addSuccessful() throws Exception {
        AddMeetingCommandTest.MeetingModelStubAcceptingAdded modelStub =
                new AddMeetingCommandTest.MeetingModelStubAcceptingAdded();
        Meeting validMeeting = new MeetingBuilder().build();

        CommandResult commandResult = new AddMeetingCommand(validMeeting).execute(modelStub);
        assertEquals(String.format(AddMeetingCommand.MESSAGE_SUCCESS, validMeeting), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validMeeting), modelStub.meetingsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Meeting validMeeting = new MeetingBuilder().build();
        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(validMeeting);
        AddMeetingCommandTest.ModelStub modelStub = new AddMeetingCommandTest.MeetingModelStubWith(validMeeting);

        assertThrows(CommandException.class,
                AddMeetingCommand.MESSAGE_DUPLICATE_MEETING, () -> addMeetingCommand.execute(modelStub));
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
        public Set<Person> findPersonsInGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
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
        public void sortFilteredMeetingList(Comparator<Meeting> comparator) {

        }

        @Override
        public ObservableList<Meeting> getUnmodifiableMeetingList() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void setPersonMeetingConnection(PersonMeetingConnection connection) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public PersonMeetingConnection getPersonMeetingConnection() {
            return new PersonMeetingConnection();
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
        public ObservableList<Person> getFilteredPersonListByMeetingConnection(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
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

    /**
     * A Model stub that contains a single meeting.
     */
    private class MeetingModelStubWith extends AddMeetingCommandTest.ModelStub {
        private final Meeting meeting;

        MeetingModelStubWith(Meeting meeting) {
            requireNonNull(meeting);
            this.meeting = meeting;
        }

        @Override
        public boolean hasMeeting(Meeting meeting) {
            requireNonNull(meeting);
            return this.meeting.isSameMeeting(meeting);
        }
    }

    /**
     * A Model stub that always accept the meeting being added.
     */
    private class MeetingModelStubAcceptingAdded extends AddMeetingCommandTest.ModelStub {
        final ArrayList<Meeting> meetingsAdded = new ArrayList<>();

        @Override
        public boolean hasMeeting(Meeting meeting) {
            requireNonNull(meeting);
            return meetingsAdded.stream().anyMatch(meeting::isSameMeeting);
        }

        @Override
        public void addMeeting(Meeting meeting) {
            requireNonNull(meeting);
            meetingsAdded.add(meeting);
        }
        @Override
        public boolean clashes(Meeting meeting) {
            requireNonNull(meeting);
            return meetingsAdded.stream().anyMatch(meeting :: isConflict);
        }

        @Override
        public ReadOnlyMeetingBook getMeetingBook() {
            return new MeetingBook();
        }
    }
}
