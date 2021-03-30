package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.HeyMatez;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyHeyMatez;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.assignee.Assignee;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

public class AddTaskCommandTest {
    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null));
    }

    @Test
    public void execute_taskAcceptedByModel_addSuccessful() throws Exception {
        AddTaskCommandTest.ModelStubAcceptingTaskAdded modelStub = new AddTaskCommandTest
                .ModelStubAcceptingTaskAdded();
        Task validTask = new TaskBuilder().build();

        CommandResult commandResult = new AddTaskCommand(validTask).execute(modelStub);

        assertEquals(String.format(AddTaskCommand.MESSAGE_SUCCESS, validTask), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTask), modelStub.tasksAdded);
    }

    @Test
    public void equals() {
        Task planMeeting = new TaskBuilder().withTitle("Plan meeting").build();
        Task bookVenue = new TaskBuilder().withTitle("Book venue").build();
        AddTaskCommand addPlanMeetingCommand = new AddTaskCommand(planMeeting);
        AddTaskCommand addBookVenueCommand = new AddTaskCommand(bookVenue);


        // same object -> returns true
        assertTrue(addPlanMeetingCommand.equals(addPlanMeetingCommand));

        // same values -> returns true
        AddTaskCommand addPlanMeetingCommandCopy = new AddTaskCommand(planMeeting);
        assertTrue(addPlanMeetingCommand.equals(addPlanMeetingCommandCopy));

        // different types -> returns false
        assertFalse(addPlanMeetingCommand.equals(1));

        // null -> returns false
        assertFalse(addPlanMeetingCommand.equals(null));

        // different person -> returns false
        assertFalse(addPlanMeetingCommand.equals(addBookVenueCommand));
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
        public Path getHeyMatezFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setHeyMatezFilePath(Path heyMatezFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public void setHeyMatez(ReadOnlyHeyMatez newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyHeyMatez getHeyMatez() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTask(Task target) {
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
        public void setTask(Task target, Task editedTask) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean checkAssignees(Task task) {
            throw new AssertionError("This method should not be called.");
        };
    }

    /**
     * A Model stub that contains a single task.
     */
    private class ModelStubWithTask extends AddTaskCommandTest.ModelStub {
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

    /**
     * A Model stub that always accept the task being added.
     */
    private class ModelStubAcceptingTaskAdded extends AddTaskCommandTest.ModelStub {
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
        public ReadOnlyHeyMatez getHeyMatez() {
            return new HeyMatez();
        }

        @Override
        public boolean checkAssignees(Task task) {
            Set<Assignee> assignees = task.getAssignees();

            for (Assignee assignee : assignees) {
                Name tempName = new Name(assignee.assigneeName);
                Person tempPerson = new Person(tempName);
                if (!hasPerson(tempPerson)) {
                    return false;
                }
            }

            return true;
        }
    }
}
