package seedu.partyplanet.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.partyplanet.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.partyplanet.commons.core.GuiSettings;
import seedu.partyplanet.commons.util.State;
import seedu.partyplanet.commons.util.StateHistory;
import seedu.partyplanet.logic.commands.exceptions.CommandException;
import seedu.partyplanet.model.AddressBook;
import seedu.partyplanet.model.EventBook;
import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.ReadOnlyAddressBook;
import seedu.partyplanet.model.ReadOnlyEventBook;
import seedu.partyplanet.model.ReadOnlyUserPrefs;
import seedu.partyplanet.model.event.Event;
import seedu.partyplanet.model.person.Person;
import seedu.partyplanet.testutil.EventBuilder;

public class EAddCommandTest {

    @Test
    public void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EAddCommand(null));
    }

    @Test
    public void execute_eventAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingEventAdded modelStub = new ModelStubAcceptingEventAdded();
        Event validEvent = new EventBuilder().build();

        CommandResult commandResult = new EAddCommand(validEvent).execute(modelStub);

        assertEquals(String.format(EAddCommand.MESSAGE_SUCCESS, validEvent), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validEvent), modelStub.eventsAdded);
    }

    @Test
    public void execute_eventOnlyNameAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingEventAdded modelStub = new ModelStubAcceptingEventAdded();
        Event validEventWithNameOnly =
            new EventBuilder("April").build();

        CommandResult commandResult = new EAddCommand(validEventWithNameOnly).execute(modelStub);

        assertEquals(String.format(EAddCommand.MESSAGE_SUCCESS, validEventWithNameOnly),
            commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validEventWithNameOnly), modelStub.eventsAdded);
    }

    @Test
    public void execute_duplicateEvent_throwsCommandException() {
        Event validEvent = new EventBuilder().build();
        EAddCommand eAddCommand = new EAddCommand(validEvent);
        ModelStub modelStub = new ModelStubWithEvent(validEvent);

        assertThrows(CommandException.class, EAddCommand.MESSAGE_DUPLICATE_EVENT, () -> eAddCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Event aprilFools = new EventBuilder().withName("April Fools").build();
        Event birthday = new EventBuilder().withName("Birthday").build();
        EAddCommand eAddAprilFoolsCommand = new EAddCommand(aprilFools);
        EAddCommand eAddBirthdayCommand = new EAddCommand(birthday);

        // same object -> returns true
        assertTrue(eAddAprilFoolsCommand.equals(eAddAprilFoolsCommand));

        // same values -> returns true
        EAddCommand eAddAprilFoolsCommandCopy = new EAddCommand(aprilFools);
        assertTrue(eAddAprilFoolsCommand.equals(eAddAprilFoolsCommandCopy));

        // different types -> returns false
        assertFalse(eAddAprilFoolsCommand.equals(1));

        // null -> returns false
        assertFalse(eAddAprilFoolsCommand.equals(null));

        // different event -> returns false
        assertFalse(eAddAprilFoolsCommand.equals(eAddBirthdayCommand));
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
        public void addState(String command) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String undo() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String redo() {
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
        public void sortPersonList(Comparator<Person> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<Person> getPersonListCopy() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getEventBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEventBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEventBook(ReadOnlyEventBook eventBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyEventBook getEventBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEvent(Event target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEvent(Event target, Event editedEvent) {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public ObservableList<Event> getFilteredEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEventList(Predicate<Event> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortEventList(Comparator<Event> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<Event> getEventListCopy() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single event.
     */
    private class ModelStubWithEvent extends ModelStub {
        private final Event event;

        ModelStubWithEvent(Event event) {
            requireNonNull(event);
            this.event = event;
        }

        @Override
        public boolean hasEvent(Event event) {
            requireNonNull(event);
            return this.event.isSameEvent(event);
        }
    }

    /**
     * A Model stub that always accept the event being added.
     */
    private class ModelStubAcceptingEventAdded extends ModelStub {
        final ArrayList<Event> eventsAdded = new ArrayList<>();

        private State savedState = new State("Loading from saved data", getAddressBook(), getEventBook());
        final StateHistory stateHistory = new StateHistory(savedState);

        @Override
        public boolean hasEvent(Event event) {
            requireNonNull(event);
            return eventsAdded.stream().anyMatch(event::isSameEvent);
        }

        @Override
        public void addEvent(Event event) {
            requireNonNull(event);
            eventsAdded.add(event);
        }

        @Override
        public void addState(String command) {
            stateHistory.addState(new State(command, getAddressBook(), getEventBook()));
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

        @Override
        public ReadOnlyEventBook getEventBook() {
            return new EventBook();
        }
    }

}
