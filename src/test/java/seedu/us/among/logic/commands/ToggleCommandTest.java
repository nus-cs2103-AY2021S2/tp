package seedu.us.among.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.us.among.ui.ThemeType;

public class ToggleCommandTest {

    @Test
    public void equals() {

        for (ThemeType theme : ThemeType.values()) {
            String userInput = ThemeType.getTheme(theme.toString()).name();
            ToggleCommand standardCommand = new ToggleCommand(userInput);

            // same values -> returns true
            ToggleCommand commandWithSameValues = new ToggleCommand(userInput);
            assertTrue(standardCommand.equals(commandWithSameValues));

            // same object -> returns true
            assertTrue(standardCommand.equals(standardCommand));

            // null -> returns false
            assertFalse(standardCommand.equals(null));

            // different types -> returns false
            assertFalse(standardCommand.equals(new ClearCommand()));
        }

        ToggleCommand standardCommand = new ToggleCommand(ThemeType.getTheme("light").name());

        // different theme toggle -> returns false
        assertFalse(standardCommand.equals(new ToggleCommand(ThemeType.getTheme("dark").name())));
    }
}
