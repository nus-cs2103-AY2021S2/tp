package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.CS2103;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskTracker;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Task;
import seedu.address.model.person.exceptions.DuplicateTaskException;
import seedu.address.testutil.TaskBuilder;

public class TaskTrackerTest {

    private final TaskTracker taskTracker = new TaskTracker();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), taskTracker.getTaskList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskTracker.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTaskTracker_replacesData() {
        TaskTracker newData = getTypicalTaskTracker();
        taskTracker.resetData(newData);
        assertEquals(newData, taskTracker);
    }

    @Test
    public void resetData_withDuplicateTasks_throwsDuplicateTaskException() {
        // Two tasks with the same identity fields
        Task editedAlice = new TaskBuilder(CS2103).withTags(VALID_TAG_HUSBAND)
            .build();
        Task duplicateAlice = new TaskBuilder(CS2103).withTags(VALID_TAG_HUSBAND)
            .build();
        List<Task> newTasks = Arrays.asList(duplicateAlice, editedAlice);
        TaskTrackerStub newData = new TaskTrackerStub(newTasks);

        assertThrows(DuplicateTaskException.class, () -> taskTracker.resetData(newData));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskTracker.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInTaskTracker_returnsFalse() {
        assertFalse(taskTracker.hasTask(CS2103));
    }

    @Test
    public void hasTask_taskInTaskTracker_returnsTrue() {
        taskTracker.addTask(CS2103);
        assertTrue(taskTracker.hasTask(CS2103));
    }

    @Test
    public void hasTask_taskWithSameIdentityFieldsInTaskTracker_returnsFalse() {
        taskTracker.addTask(CS2103);
        Task editedAlice = new TaskBuilder(CS2103).withTags(VALID_TAG_HUSBAND)
            .build();
        assertFalse(taskTracker.hasTask(editedAlice));
    }

    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> taskTracker.getTaskList().remove(0));
    }

    /**
     * A stub ReadOnlyTaskTracker whose tasks list can violate interface constraints.
     */
    private static class TaskTrackerStub implements ReadOnlyTaskTracker {
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();
        private final ObservableList<Task> dailyTasks = FXCollections.observableArrayList();

        TaskTrackerStub(Collection<Task> tasks) {
            this.tasks.setAll(tasks);
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }

        @Override
        public ObservableList<Task> getDailyTaskList() {
            return dailyTasks;
        }
    }

}
