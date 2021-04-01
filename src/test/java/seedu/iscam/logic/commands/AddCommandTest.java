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

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import seedu.iscam.commons.core.GuiSettings;
import seedu.iscam.logic.commands.exceptions.CommandException;
import seedu.iscam.model.Model;
import seedu.iscam.model.ReadOnlyUserPrefs;
import seedu.iscam.model.client.Client;
import seedu.iscam.model.meeting.Meeting;
import seedu.iscam.model.util.clientbook.ClientBook;
import seedu.iscam.model.util.clientbook.ObservableClient;
import seedu.iscam.model.util.clientbook.ReadOnlyClientBook;
import seedu.iscam.model.util.meetingbook.MeetingBook;
import seedu.iscam.model.util.meetingbook.ObservableMeeting;
import seedu.iscam.model.util.meetingbook.ReadOnlyMeetingBook;
import seedu.iscam.testutil.ClientBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_clientAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingClientAdded modelStub = new ModelStubAcceptingClientAdded();
        Client validClient = new ClientBuilder().build();

        CommandResult commandResult = new AddCommand(validClient).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validClient), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validClient), modelStub.clientsAdded);
    }

    @Test
    public void execute_duplicateClient_throwsCommandException() {
        Client validClient = new ClientBuilder().build();
        AddCommand addCommand = new AddCommand(validClient);
        ModelStub modelStub = new ModelStubWithClient(validClient);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_CLIENT, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Client alice = new ClientBuilder().withName("Alice").build();
        Client bob = new ClientBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different client -> returns false
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
        public MeetingBook getMeetingBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasMeeting(Meeting meeting) {
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

        @Override
        public void setClientMode() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMeetingMode() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableValue<Boolean> getIsClientMode() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single client.
     */
    private class ModelStubWithClient extends ModelStub {
        private final Client client;

        ModelStubWithClient(Client client) {
            requireNonNull(client);
            this.client = client;
        }

        @Override
        public boolean hasClient(Client client) {
            requireNonNull(client);
            return this.client.isSameClient(client);
        }
    }

    /**
     * A Model stub that always accept the client being added.
     */
    private class ModelStubAcceptingClientAdded extends ModelStub {
        final ArrayList<Client> clientsAdded = new ArrayList<>();

        @Override
        public boolean hasClient(Client client) {
            requireNonNull(client);
            return clientsAdded.stream().anyMatch(client::isSameClient);
        }

        @Override
        public void addClient(Client client) {
            requireNonNull(client);
            clientsAdded.add(client);
        }

        @Override
        public ReadOnlyClientBook getClientBook() {
            return new ClientBook();
        }
    }

}
