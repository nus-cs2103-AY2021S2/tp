package seedu.address.model.task;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MARATHON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_MARATHON;
import static seedu.address.testutil.TypicalTasks.HOMEWORK;
import static seedu.address.testutil.TypicalTasks.MARATHON;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class TaskTest {

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(HOMEWORK.isSameTask(HOMEWORK));

        // null -> returns false
        assertFalse(HOMEWORK.isSameTask(null));

        // same name, all other attributes different -> returns true
        Task editedHomework = new TaskBuilder(HOMEWORK).withDescription(VALID_DESCRIPTION_MARATHON).build();
        assertTrue(HOMEWORK.isSameTask(editedHomework));

        // different name, all other attributes same -> returns false
        editedHomework = new TaskBuilder(HOMEWORK).withTitle(VALID_TITLE_MARATHON).build();
        assertFalse(HOMEWORK.isSameTask(editedHomework));

        // name differs in case, all other attributes same -> returns false
        Task editedMarathon = new TaskBuilder(MARATHON).withTitle(VALID_TITLE_MARATHON.toLowerCase()).build();
        assertFalse(MARATHON.isSameTask(editedMarathon));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_TITLE_MARATHON + " ";
        editedMarathon = new TaskBuilder(MARATHON).withTitle(nameWithTrailingSpaces).build();
        assertFalse(MARATHON.isSameTask(editedMarathon));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task homeworkcopy = new TaskBuilder(HOMEWORK).build();
        assertTrue(HOMEWORK.equals(homeworkcopy));

        // same object -> returns true
        assertTrue(HOMEWORK.equals(HOMEWORK));

        // null -> returns false
        assertFalse(HOMEWORK.equals(null));

        // different type -> returns false
        assertFalse(HOMEWORK.equals(5));

        // different task -> returns false
        assertFalse(HOMEWORK.equals(MARATHON));

        // different title -> returns false
        Task editedHomework = new TaskBuilder(HOMEWORK).withTitle(VALID_TITLE_MARATHON).build();
        assertFalse(HOMEWORK.equals(editedHomework));

        // different description -> returns false
        editedHomework = new TaskBuilder(HOMEWORK).withDescription(VALID_DESCRIPTION_MARATHON).build();
        assertFalse(HOMEWORK.equals(editedHomework));


    }
}

