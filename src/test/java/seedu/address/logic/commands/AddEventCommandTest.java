package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlySochedule;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.Sochedule;
import seedu.address.model.event.Event;
import seedu.address.model.task.Task;
import seedu.address.testutil.EventBuilder;



public class AddEventCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddEventCommand(null));
    }


    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        AddEventCommandTest.ModelStubAcceptingEventAdded modelStub =
                new AddEventCommandTest.ModelStubAcceptingEventAdded();
        Event validEvent = new EventBuilder().build();

        CommandResult commandResult = new AddEventCommand(validEvent).execute(modelStub);

        assertEquals(String.format(AddEventCommand.MESSAGE_SUCCESS, validEvent), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validEvent), modelStub.eventsAdded);
    }

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
        public Path getSocheduleFilePath() {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void setSocheduleFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void addTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void addEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void setSochedule(ReadOnlySochedule newData) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void sortTasks(String comparingVar) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getNumCompletedTask() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getNumOverdueTask() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getNumIncompleteTask() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortEvents(String comparingVar) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getNumIncomingEvents() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlySochedule getSochedule() {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public boolean hasTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public boolean hasEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void deleteTask(Task target) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void deleteEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void doneTask(Task target) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void setTask(Task target, Task editedTask) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void setEvent(Event target, Event editedEvent) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void clearExpiredTasks() {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void clearCompletedTasks() {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void clearExpiredEvents() {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public ObservableList<Event> getFilteredEventList() {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void updateFilteredEventList(Predicate<Event> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

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
        public ReadOnlySochedule getSochedule() {
            return new Sochedule();
        }
    }

}
