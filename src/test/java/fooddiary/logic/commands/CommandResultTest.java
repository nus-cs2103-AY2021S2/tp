package fooddiary.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code CommandResult}.
 */
public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult(null, null, "feedback", false, false, false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different enableView value -> returns false
        assertFalse(commandResult.equals(new CommandResult(null, null, "feedback",
                false, true, false, false)));

        // different enableRevise value -> returns false
        assertFalse(commandResult.equals(new CommandResult(null, null, "feedback",
                false, false, true, false)));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult(null, null, "feedback",
                true, false, false, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult(null, null, "feedback",
                false, false, false, true)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different enableView value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult(null, null, "feedback",
                false, true, false, false).hashCode());

        // different enableRevise value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult(null, null, "feedback",
                false, false, true, false).hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult(null, null, "feedback",
                true, false, false, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult(null, null, "feedback",
                false, false, false, true).hashCode());
    }
}
