package seedu.module.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.module.logic.commands.CommandTestUtil.VALID_DEADLINE_PRACTICAL;
import static seedu.module.logic.commands.CommandTestUtil.VALID_DESCRIPTION_PRACTICAL;
import static seedu.module.logic.commands.CommandTestUtil.VALID_MODULE_LAB;
import static seedu.module.logic.commands.CommandTestUtil.VALID_MODULE_PRACTICAL;
import static seedu.module.logic.commands.CommandTestUtil.VALID_TAG_PRIORITY_HIGH;
import static seedu.module.logic.commands.CommandTestUtil.VALID_TASK_NAME_LAB;
import static seedu.module.logic.commands.CommandTestUtil.VALID_TASK_NAME_PRACTICAL;
import static seedu.module.testutil.Assert.assertThrows;
import static seedu.module.testutil.TypicalTasks.QUIZ;
import static seedu.module.testutil.TypicalTasks.REVIEW;

import org.junit.jupiter.api.Test;

import seedu.module.testutil.TaskBuilder;
import seedu.module.testutil.TypicalTasks;


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
        Task editedAlice = new TaskBuilder(QUIZ)
                .withDeadline(VALID_DEADLINE_PRACTICAL)
                .withModule(VALID_MODULE_PRACTICAL)
                .withDescription(VALID_DESCRIPTION_PRACTICAL)
                .withTags(VALID_TAG_PRIORITY_HIGH).build();
        assertFalse(QUIZ.isSameTask(editedAlice));

        // same name and same module, all other attributes different -> returns true
        editedAlice = new TaskBuilder(REVIEW)
                .withModule(VALID_MODULE_PRACTICAL)
                .withDescription(VALID_DESCRIPTION_PRACTICAL)
                .withDeadline(VALID_DEADLINE_PRACTICAL).build();
        assertTrue(REVIEW.isSameTask(editedAlice));

        //different name and same module, all other attributes different -> return false
        editedAlice = new TaskBuilder(QUIZ)
                .withModule(VALID_MODULE_PRACTICAL)
                .withDescription(VALID_DESCRIPTION_PRACTICAL)
                .withDeadline(VALID_DEADLINE_PRACTICAL).build();
        assertFalse(REVIEW.isSameTask(editedAlice));

        // different name, different module -> returns false
        editedAlice = new TaskBuilder(QUIZ).withName(VALID_TASK_NAME_LAB).withModule(VALID_MODULE_LAB).build();
        assertFalse(REVIEW.isSameTask(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Task editedBob = new TaskBuilder(TypicalTasks.REVIEW).withName(VALID_TASK_NAME_PRACTICAL.toLowerCase()).build();
        assertFalse(TypicalTasks.REVIEW.isSameTask(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_TASK_NAME_PRACTICAL + " ";
        editedBob = new TaskBuilder(TypicalTasks.REVIEW).withName(nameWithTrailingSpaces).build();
        assertFalse(TypicalTasks.REVIEW.isSameTask(editedBob));
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
        assertFalse(QUIZ.equals(TypicalTasks.REVIEW));

        // different name -> returns false
        Task editedAlice = new TaskBuilder(QUIZ).withName(VALID_TASK_NAME_PRACTICAL).build();
        assertFalse(QUIZ.equals(editedAlice));

        // different deadline -> returns false
        editedAlice = new TaskBuilder(QUIZ).withDeadline(VALID_DEADLINE_PRACTICAL).build();
        assertFalse(QUIZ.equals(editedAlice));

        // different module -> returns false
        editedAlice = new TaskBuilder(QUIZ).withModule(VALID_MODULE_PRACTICAL).build();
        assertFalse(QUIZ.equals(editedAlice));

        // different description -> returns false
        editedAlice = new TaskBuilder(QUIZ).withDescription(VALID_DESCRIPTION_PRACTICAL).build();
        assertFalse(QUIZ.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new TaskBuilder(QUIZ).withTags(VALID_TAG_PRIORITY_HIGH).build();
        assertFalse(QUIZ.equals(editedAlice));
    }
}
