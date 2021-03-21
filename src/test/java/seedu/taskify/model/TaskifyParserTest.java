package seedu.taskify.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taskify.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.taskify.testutil.Assert.assertThrows;
import static seedu.taskify.testutil.TypicalTasks.ALICE;
import static seedu.taskify.testutil.TypicalTasks.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.taskify.model.task.Task;
import seedu.taskify.model.task.exceptions.DuplicateTaskException;
import seedu.taskify.testutil.TaskBuilder;


public class TaskifyParserTest {

    private final Taskify taskify = new Taskify();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), taskify.getTaskList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskify.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        Taskify newData = getTypicalAddressBook();
        taskify.resetData(newData);
        assertEquals(newData, taskify);
    }

    @Test
    public void resetData_withDuplicateTasks_throwsDuplicateTaskException() {
        // Two tasks with the same identity fields
        Task editedAlice = new TaskBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                                   .build();
        List<Task> newTasks = Arrays.asList(ALICE, editedAlice);
        TaskifyStub newData = new TaskifyStub(newTasks);

        assertThrows(DuplicateTaskException.class, () -> taskify.resetData(newData));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskify.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInAddressBook_returnsFalse() {
        assertFalse(taskify.hasTask(ALICE));
    }

    @Test
    public void hasTask_taskInAddressBook_returnsTrue() {
        taskify.addTask(ALICE);
        assertTrue(taskify.hasTask(ALICE));
    }

    @Test
    public void hasTask_taskWithSameIdentityFieldsInAddressBook_returnsTrue() {
        taskify.addTask(ALICE);
        Task editedAlice = new TaskBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                                   .build();
        assertTrue(taskify.hasTask(editedAlice));
    }

    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> taskify.getTaskList().remove(0));
    }

    /**
     * A stub ReadOnlyTaskify whose tasks list can violate interface constraints.
     */
    private static class TaskifyStub implements ReadOnlyTaskify {
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        TaskifyStub(Collection<Task> tasks) {
            this.tasks.setAll(tasks);
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }
    }

}
