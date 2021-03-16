package seedu.us.among.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.us.among.testutil.TypicalEndpoints.getTypicalEndpointList;

import org.junit.jupiter.api.Test;

import seedu.us.among.model.Model;
import seedu.us.among.model.ModelManager;
import seedu.us.among.model.UserPrefs;
import seedu.us.among.ui.ThemeType;

public class ToggleCommandTest {
    private Model model = new ModelManager(getTypicalEndpointList(), new UserPrefs());

    @Test
    public void equals() {
        final ToggleCommand standardCommand = new ToggleCommand(ThemeType.getTheme("light").name());

        // same values -> returns true
        ToggleCommand commandWithSameValues = new ToggleCommand(ThemeType.getTheme("light").name());
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new ToggleCommand(ThemeType.getTheme("dark").name())));
    }
}
