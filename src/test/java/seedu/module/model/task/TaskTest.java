package seedu.module.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.module.logic.commands.CommandTestUtil.VALID_DEADLINE_BOB;
import static seedu.module.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.module.logic.commands.CommandTestUtil.VALID_MODULE_AMY;
import static seedu.module.logic.commands.CommandTestUtil.VALID_MODULE_BOB;
import static seedu.module.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.module.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.module.logic.commands.CommandTestUtil.VALID_TAG_HIGH;
import static seedu.module.testutil.Assert.assertThrows;
import static seedu.module.testutil.TypicalTasks.BOB;
import static seedu.module.testutil.TypicalTasks.PRACTICAL;
import static seedu.module.testutil.TypicalTasks.QUIZ;

import org.junit.jupiter.api.Test;

import seedu.module.testutil.TaskBuilder;


public class TaskTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Task task = new TaskBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> task.getTags().remove(0));
    }

    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(QUIZ.isSameTask(QUIZ));

        // null -> returns false
        assertFalse(QUIZ.isSameTask(null));

        // same name and different module all other attributes different -> returns false
        Task editedAlice = new TaskBuilder(QUIZ).withDeadline(VALID_DEADLINE_BOB).withModule(VALID_MODULE_BOB)
                .withDescription(VALID_DESCRIPTION_BOB).withTags(VALID_TAG_HIGH).build();
        assertFalse(QUIZ.isSameTask(editedAlice));

        // same name and same module, all other attributes different -> returns true
        editedAlice = new TaskBuilder(PRACTICAL).withModule(VALID_MODULE_BOB).withDescription(VALID_DESCRIPTION_BOB)
                .withDeadline(VALID_DEADLINE_BOB).build();
        assertTrue(PRACTICAL.isSameTask(editedAlice));

        //different name and same module, all other attributes different -> return false
        editedAlice = new TaskBuilder(QUIZ).withModule(VALID_MODULE_BOB).withDescription(VALID_DESCRIPTION_BOB)
                .withDeadline(VALID_DEADLINE_BOB).build();
        assertFalse(PRACTICAL.isSameTask(editedAlice));

        // different name, different module -> returns false
        editedAlice = new TaskBuilder(QUIZ).withName(VALID_NAME_AMY).withModule(VALID_MODULE_AMY).build();
        assertFalse(PRACTICAL.isSameTask(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Task editedBob = new TaskBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameTask(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new TaskBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameTask(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task aliceCopy = new TaskBuilder(QUIZ).build();
        assertTrue(QUIZ.equals(aliceCopy));

        // same object -> returns true
        assertTrue(QUIZ.equals(QUIZ));

        // null -> returns false
        assertFalse(QUIZ.equals(null));

        // different type -> returns false
        assertFalse(QUIZ.equals(5));

        // different task -> returns false
        assertFalse(QUIZ.equals(BOB));

        // different name -> returns false
        Task editedAlice = new TaskBuilder(QUIZ).withName(VALID_NAME_BOB).build();
        assertFalse(QUIZ.equals(editedAlice));

        // different deadline -> returns false
        editedAlice = new TaskBuilder(QUIZ).withDeadline(VALID_DEADLINE_BOB).build();
        assertFalse(QUIZ.equals(editedAlice));

        // different module -> returns false
        editedAlice = new TaskBuilder(QUIZ).withModule(VALID_MODULE_BOB).build();
        assertFalse(QUIZ.equals(editedAlice));

        // different description -> returns false
        editedAlice = new TaskBuilder(QUIZ).withDescription(VALID_DESCRIPTION_BOB).build();
        assertFalse(QUIZ.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new TaskBuilder(QUIZ).withTags(VALID_TAG_HIGH).build();
        assertFalse(QUIZ.equals(editedAlice));
    }
}
