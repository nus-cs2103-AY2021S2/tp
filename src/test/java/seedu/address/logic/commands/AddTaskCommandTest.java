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
import seedu.address.testutil.TaskBuilder;



public class AddTaskCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null));
    }


    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        AddTaskCommandTest.ModelStubAcceptingTaskAdded modelStub = new AddTaskCommandTest.ModelStubAcceptingTaskAdded();
        Task validTask = new TaskBuilder().build();

        CommandResult commandResult = new AddTaskCommand(validTask).execute(modelStub);

        assertEquals(String.format(AddTaskCommand.MESSAGE_SUCCESS, validTask), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTask), modelStub.tasksAdded);
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
        @Override
        public void sortTasks(String comparingVar) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void sortEvents(String comparingVar) {
            throw new AssertionError("This method should not be called.");
        }
    }

    private class ModelStubWithTask extends ModelStub {
        private final Task task;
        ModelStubWithTask(Task task) {
            requireNonNull(task);
            this.task = task;
        }
        @Override
        public boolean hasTask(Task task) {
            requireNonNull(task);
            return this.task.isSameTask(task);
        }
    }

    private class ModelStubAcceptingTaskAdded extends ModelStub {
        final ArrayList<Task> tasksAdded = new ArrayList<>();
        @Override
        public boolean hasTask(Task task) {
            requireNonNull(task);
            return tasksAdded.stream().anyMatch(task::isSameTask);
        }
        @Override
        public void addTask(Task task) {
            requireNonNull(task);
            tasksAdded.add(task);
        }
        @Override
        public ReadOnlySochedule getSochedule() {
            return new Sochedule();
        }
    }

}
