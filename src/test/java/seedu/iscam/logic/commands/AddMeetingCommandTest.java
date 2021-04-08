package seedu.iscam.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.iscam.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.iscam.commons.core.GuiSettings;
import seedu.iscam.commons.core.index.Index;
import seedu.iscam.logic.commands.exceptions.CommandException;
import seedu.iscam.model.Model;
import seedu.iscam.model.client.Client;
import seedu.iscam.model.meeting.Meeting;
import seedu.iscam.model.user.ReadOnlyUserPrefs;
import seedu.iscam.model.util.clientbook.ObservableClient;
import seedu.iscam.model.util.clientbook.ReadOnlyClientBook;
import seedu.iscam.model.util.meetingbook.MeetingBook;
import seedu.iscam.model.util.meetingbook.ObservableMeeting;
import seedu.iscam.model.util.meetingbook.ReadOnlyMeetingBook;
import seedu.iscam.testutil.MeetingBuilder;

public class AddMeetingCommandTest {

    @Test
    public void constructor_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddMeetingCommand(null));
    }

    @Test
    public void execute_meetingAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingMeetingAdded modelStub = new ModelStubAcceptingMeetingAdded();
        Meeting validMeeting = new MeetingBuilder().build();

        CommandResult commandResult = new AddMeetingCommand(validMeeting).execute(modelStub);

        assertEquals(String.format(AddMeetingCommand.MESSAGE_SUCCESS, validMeeting),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validMeeting), modelStub.meetingsAdded);
    }

    @Test
    public void execute_duplicateMeeting_throwsCommandException() {
        Meeting validMeeting = new MeetingBuilder().build();
        AddMeetingCommand addCommand = new AddMeetingCommand(validMeeting);
        ModelStub modelStub = new ModelStubWithMeeting(validMeeting);

        assertThrows(CommandException.class, AddMeetingCommand.MESSAGE_MEETING_CONFLICT, () ->
            addCommand.execute(modelStub)
        );
    }

    @Test
    public void equals() {
        Meeting alice = new MeetingBuilder().withName("Alice").build();
        Meeting bob = new MeetingBuilder().withName("Bob").build();
        AddMeetingCommand addAliceCommand = new AddMeetingCommand(alice);
        AddMeetingCommand addBobCommand = new AddMeetingCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddMeetingCommand addAliceCommandCopy = new AddMeetingCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different meeting -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
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
        public Index getIndexOfClient(Client client) {
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
        public Path getClientBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClientBookFilePath(Path clientBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addClient(Client client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addClientAtIndex(Index index, Client client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClientBook(ReadOnlyClientBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyClientBook getClientBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasClient(Client client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteClient(Client target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClient(Client target, Client editedClient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Client> getFilteredClientList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredClientList(Predicate<Client> predicate) {
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
        public boolean hasConflictingMeetingWith(Meeting meeting, Meeting... exclusions) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableMeeting getDetailedMeeting() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableClient getDetailedClient() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteMeeting(Meeting target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMeeting(Meeting target, Meeting editedMeeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMeetingBook(ReadOnlyMeetingBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getMeetingBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMeetingBookFilePath(Path meetingBookFilePath) {
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
        public void setDetailedClient(Client client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDetailedMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

    }

    /**
     * A Model stub that contains a single meeting.
     */
    private class ModelStubWithMeeting extends ModelStub {
        private final Meeting meeting;

        ModelStubWithMeeting(Meeting meeting) {
            requireNonNull(meeting);
            this.meeting = meeting;
        }

        @Override
        public boolean hasMeeting(Meeting meeting) {
            requireNonNull(meeting);
            return this.meeting.isInConflict(meeting);
        }
    }

    /**
     * A Model stub that always accept the meeting being added.
     */
    private class ModelStubAcceptingMeetingAdded extends ModelStub {
        final ArrayList<Meeting> meetingsAdded = new ArrayList<>();

        @Override
        public boolean hasMeeting(Meeting meeting) {
            requireNonNull(meeting);
            return meetingsAdded.stream().anyMatch(meeting::isInConflict);
        }

        @Override
        public void addMeeting(Meeting meeting) {
            requireNonNull(meeting);
            meetingsAdded.add(meeting);
        }

        @Override
        public ReadOnlyMeetingBook getMeetingBook() {
            return new MeetingBook();
        }
    }

}
