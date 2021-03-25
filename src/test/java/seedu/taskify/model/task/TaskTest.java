package seedu.taskify.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taskify.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CS2103T_TP;
import static seedu.taskify.logic.commands.CommandTestUtil.VALID_NAME_CS2103T_TP;
import static seedu.taskify.logic.commands.CommandTestUtil.VALID_TAG_DEBUGGING;
import static seedu.taskify.testutil.Assert.assertThrows;
import static seedu.taskify.testutil.TypicalTasks.BOB;
import static seedu.taskify.testutil.TypicalTasks.TASK_1;

import org.junit.jupiter.api.Test;

import seedu.taskify.testutil.TaskBuilder;

public class TaskTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Task task = new TaskBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> task.getTags().remove(0));
    }

    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(TASK_1.isSameTask(TASK_1));

        // null -> returns false
        assertFalse(TASK_1.isSameTask(null));

        // same name, all other attributes different -> returns true
        Task editedAlice = new TaskBuilder(TASK_1).withDescription(VALID_DESCRIPTION_CS2103T_TP)
                .withStatus(StatusType.NOT_DONE).withTags(VALID_TAG_DEBUGGING).build();
        assertTrue(TASK_1.isSameTask(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new TaskBuilder(TASK_1).withName(VALID_NAME_CS2103T_TP).build();
        assertFalse(TASK_1.isSameTask(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Task editedBob = new TaskBuilder(BOB).withName(VALID_NAME_CS2103T_TP.toLowerCase()).build();
        assertFalse(BOB.isSameTask(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_CS2103T_TP + " ";
        editedBob = new TaskBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameTask(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task aliceCopy = new TaskBuilder(TASK_1).build();
        assertTrue(TASK_1.equals(aliceCopy));

        // same object -> returns true
        assertTrue(TASK_1.equals(TASK_1));

        // null -> returns false
        assertFalse(TASK_1.equals(null));

        // different type -> returns false
        assertFalse(TASK_1.equals(5));

        // different task -> returns false
        assertFalse(TASK_1.equals(BOB));

        // different name -> returns false
        Task editedAlice = new TaskBuilder(TASK_1).withName(VALID_NAME_CS2103T_TP).build();
        assertFalse(TASK_1.equals(editedAlice));

        // different description -> returns false
        editedAlice = new TaskBuilder(TASK_1).withDescription(VALID_DESCRIPTION_CS2103T_TP).build();
        assertFalse(TASK_1.equals(editedAlice));

        // different status -> returns false
        editedAlice = new TaskBuilder(TASK_1).withStatus(StatusType.COMPLETED).build();
        assertFalse(TASK_1.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new TaskBuilder(TASK_1).withTags(VALID_TAG_DEBUGGING).build();
        assertFalse(TASK_1.equals(editedAlice));
    }
}
