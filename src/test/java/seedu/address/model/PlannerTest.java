package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.ALICE;
import static seedu.address.testutil.TypicalTasks.getTypicalPlanner;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.task.Task;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.testutil.TaskBuilder;

public class PlannerTest {

    private final Planner planner = new Planner();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), planner.getTaskList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> planner.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyPlanner_replacesData() {
        Planner newData = getTypicalPlanner();
        planner.resetData(newData);
        assertEquals(newData, planner);
    }

    @Test
    public void resetData_withDuplicateTasks_throwsDuplicateTaskException() {
        // Two tasks with the same identity fields
        Task editedAlice = new TaskBuilder(ALICE).withDescription(VALID_DESCRIPTION_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Task> newTasks = Arrays.asList(ALICE, editedAlice);
        PlannerStub newData = new PlannerStub(newTasks);

        assertThrows(DuplicateTaskException.class, () -> planner.resetData(newData));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> planner.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInPlanner_returnsFalse() {
        assertFalse(planner.hasTask(ALICE));
    }

    @Test
    public void hasTask_taskInPlanner_returnsTrue() {
        planner.addTask(ALICE);
        assertTrue(planner.hasTask(ALICE));
    }

    @Test
    public void hasTask_taskWithSameIdentityFieldsInPlanner_returnsTrue() {
        planner.addTask(ALICE);
        Task editedAlice = new TaskBuilder(ALICE).withDescription(VALID_DESCRIPTION_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(planner.hasTask(editedAlice));
    }

    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> planner.getTaskList().remove(0));
        assertThrows(UnsupportedOperationException.class, () -> planner.getTaskList().remove(0));
    }

    /**
     * A stub ReadOnlyPlanner whose tasks list can violate interface constraints.
     */
    private static class PlannerStub implements ReadOnlyPlanner {
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        PlannerStub(Collection<Task> tasks) {
            this.tasks.setAll(tasks);
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }
    }

}
