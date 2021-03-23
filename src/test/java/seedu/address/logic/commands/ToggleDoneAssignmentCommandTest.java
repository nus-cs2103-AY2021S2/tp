package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ASSIGNMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ASSIGNMENT;
import static seedu.address.testutil.TypicalRemindMe.getTypicalRemindMe;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.testutil.typicalmodules.ModuleBuilder;


public class ToggleDoneAssignmentCommandTest {

    private Model model = new ModelManager(getTypicalRemindMe(), new UserPrefs());

    @Test
    public void equals() {
        Module module = new ModuleBuilder().build();
        ToggleDoneAssignmentCommand toggleFirstCommand = new ToggleDoneAssignmentCommand(module.getTitle(),
                INDEX_FIRST_ASSIGNMENT);
        ToggleDoneAssignmentCommand toggleSecondCommand = new ToggleDoneAssignmentCommand(module.getTitle(),
                INDEX_SECOND_ASSIGNMENT);

        // same object -> returns true
        assertTrue(toggleFirstCommand.equals(toggleFirstCommand));

        // same values -> returns true
        ToggleDoneAssignmentCommand toggleFirstCommandCopy = new ToggleDoneAssignmentCommand(module.getTitle(),
                INDEX_FIRST_ASSIGNMENT);
        assertTrue(toggleFirstCommand.equals(toggleFirstCommandCopy));

        // different types -> returns false
        assertFalse(toggleFirstCommand.equals(1));

        // null -> returns false
        assertFalse(toggleFirstCommand.equals(null));

        // different index -> returns false
        assertFalse(toggleFirstCommand.equals(toggleSecondCommand));
    }

}


