package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.EventBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyEventBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventStatus;
import seedu.address.model.person.Person;
import seedu.address.testutil.EventBuilder;


public class TodoCommandTest {

    @Test
    public void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TodoCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingEventAdded modelStub = new TodoCommandTest.ModelStubAcceptingEventAdded();
        Event validEvent = new EventBuilder().build();

        CommandResult commandResult = new TodoCommand(validEvent).execute(modelStub);

        assertEquals(String.format(TodoCommand.MESSAGE_SUCCESS, validEvent), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validEvent), modelStub.eventsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Event validEvent = new EventBuilder().build();
        TodoCommand todoCommand = new TodoCommand(validEvent);
        TodoCommandTest.ModelStub modelStub = new ModelStubWithEvent(validEvent);

        assertThrows(CommandException.class, TodoCommand.MESSAGE_DUPLICATE_EVENT, () -> todoCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Event cs2030 = new EventBuilder().withName("CS2030").build();
        Event cs2100 = new EventBuilder().withName("CS2100").build();
        TodoCommand todoCS2030Command = new TodoCommand(cs2030);
        TodoCommand todoCS2100Command = new TodoCommand(cs2100);

        // same object -> returns true
        assertTrue(todoCS2030Command.equals(todoCS2030Command));

        // same values -> returns true
        TodoCommand todoCs2030CommandCopy = new TodoCommand(cs2030);
        assertTrue(todoCS2030Command.equals(todoCs2030CommandCopy));

        // different types -> returns false
        assertFalse(todoCS2030Command.equals(1));

        // null -> returns false
        assertFalse(todoCS2030Command.equals(null));

        // different person -> returns false
        assertFalse(todoCS2030Command.equals(todoCS2100Command));
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
        public Path getEventBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEventBookFilePath(Path eventBookFilePath) {
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
        public Optional<Event> getEventByIdentifier(int identifier) {
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
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getFilteredEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public FilteredList<Event> getFilteredListByStatus(EventStatus status) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public FilteredList<Event> getFilteredTodoList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public FilteredList<Event> getFilteredBacklogList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public FilteredList<Event> getFilteredInProgressList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public FilteredList<Event> getFilteredDoneList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEventList(Predicate<Event> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
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
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingEventAdded extends ModelStub {
        final ArrayList<Event> eventsAdded = new ArrayList<>();

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
        public ReadOnlyEventBook getEventBook() {
            return new EventBook();
        }
    }

}
