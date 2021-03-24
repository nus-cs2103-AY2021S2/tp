package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_HOMEWORK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_TASKONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TASKONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_TASKONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_IMPORTANT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.ASSIGNMENT;
import static seedu.address.testutil.TypicalTasks.TASKONE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class TaskTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Task task = new TaskBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> task.getTags().remove(0));
    }

    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(ASSIGNMENT.isSameTask(ASSIGNMENT));

        // null -> returns false
        assertFalse(ASSIGNMENT.isSameTask(null));

        // same name, all other attributes different -> returns false
        Task editedAssignment = new TaskBuilder(ASSIGNMENT).withDeadline(VALID_DEADLINE_TASKONE)
                .withPriority(VALID_PRIORITY_TASKONE)
                .withCategories(VALID_CATEGORY_HOMEWORK)
                .withTags(VALID_TAG_IMPORTANT).build();
        assertFalse(ASSIGNMENT.isSameTask(editedAssignment));

        // different name, all other attributes same -> returns false
        editedAssignment = new TaskBuilder(ASSIGNMENT).withName(VALID_NAME_TASKONE).build();
        assertFalse(ASSIGNMENT.isSameTask(editedAssignment));

        // name differs in case, all other attributes same -> returns false
        Task editedTaskOne = new TaskBuilder(TASKONE)
                .withName(VALID_NAME_TASKONE.toLowerCase()).build();
        assertFalse(TASKONE.isSameTask(editedTaskOne));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_TASKONE + " ";
        editedTaskOne = new TaskBuilder(TASKONE).withName(nameWithTrailingSpaces).build();
        assertFalse(TASKONE.isSameTask(editedTaskOne));
    }
}
