package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECURRINGSCHEDULE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.ALICE;
import static seedu.address.testutil.TypicalTasks.BOB;

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
        assertTrue(ALICE.isSameTask(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameTask(null));

        // same title, all other attributes different -> returns true
        Task editedAlice = new TaskBuilder(ALICE).withDeadline(VALID_DEADLINE_BOB)
                .withRecurringSchedule(VALID_RECURRINGSCHEDULE_BOB).withDescription(VALID_DESCRIPTION_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameTask(editedAlice));

        // different title, all other attributes same -> returns false
        editedAlice = new TaskBuilder(ALICE).withTitle(VALID_TITLE_BOB).build();
        assertFalse(ALICE.isSameTask(editedAlice));

        // title differs in case, all other attributes same -> returns false
        Task editedBob = new TaskBuilder(BOB).withTitle(VALID_TITLE_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameTask(editedBob));

        // title has trailing spaces, all other attributes same -> returns false
        String titleWithTrailingSpaces = VALID_TITLE_BOB + " ";
        editedBob = new TaskBuilder(BOB).withTitle(titleWithTrailingSpaces).build();
        assertFalse(BOB.isSameTask(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task aliceCopy = new TaskBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different task -> returns false
        assertFalse(ALICE.equals(BOB));

        // different title -> returns false
        Task editedAlice = new TaskBuilder(ALICE).withTitle(VALID_TITLE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different deadline -> returns false
        editedAlice = new TaskBuilder(ALICE).withDeadline(VALID_DEADLINE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        /*
        // different recurring schedule -> returns false
        editedAlice = new TaskBuilder(ALICE).withRecurringSchedule(VALID_RECURRINGSCHEDULE_BOB).build();
        System.out.println(editedAlice.getRecurringSchedule().value);
        System.out.println(VALID_RECURRINGSCHEDULE_BOB);

        assertTrue(ALICE.equals(editedAlice));
        */

        // different description -> returns false
        editedAlice = new TaskBuilder(ALICE).withDescription(VALID_DESCRIPTION_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new TaskBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
