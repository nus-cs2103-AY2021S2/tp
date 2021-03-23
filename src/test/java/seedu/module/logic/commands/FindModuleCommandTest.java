package seedu.module.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.module.model.task.Module;

/**
 * Contains integration tests (interaction with the Model) for {@code FindModuleCommand}.
 */
public class FindModuleCommandTest {

    @Test
    public void equals() {
        Module firstMod = new Module("CS2105");
        Module secondMod = new Module("CS2106");

        FindModuleCommand findFirstCommand = new FindModuleCommand(firstMod.toString());
        FindModuleCommand findSecondCommand = new FindModuleCommand(secondMod.toString());

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindModuleCommand findFirstCommandCopy = new FindModuleCommand(firstMod.toString());
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }
}
