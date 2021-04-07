package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.BOB;
import static seedu.address.testutil.TypicalTasks.CS2103;

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
        assertTrue(CS2103.isSameTask(CS2103));

        // null -> returns false
        assertFalse(CS2103.isSameTask(null));

        // same name, all other attributes different -> returns true
        Task editedAlice = new TaskBuilder(CS2103)
                .withTags(VALID_TAG_HUSBAND).build();
        assertFalse(CS2103.isSameTask(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new TaskBuilder(CS2103).withName(VALID_NAME_BOB).build();
        assertFalse(CS2103.isSameTask(editedAlice));

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
        Task aliceCopy = new TaskBuilder(CS2103).build();
        assertTrue(CS2103.equals(aliceCopy));

        // same object -> returns true
        assertTrue(CS2103.equals(CS2103));

        // null -> returns false
        assertFalse(CS2103.equals(null));

        // different type -> returns false
        assertFalse(CS2103.equals(5));

        // different task -> returns false
        assertFalse(CS2103.equals(BOB));

        // different name -> returns false
        Task editedAlice = new TaskBuilder(CS2103).withName(VALID_NAME_BOB).build();
        assertFalse(CS2103.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new TaskBuilder(CS2103).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(CS2103.equals(editedAlice));
    }
}
