package seedu.address.logic.commands.alias;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Alias;

public class UnaliasCommandTest {
    @Test
    public void equals() {
        UnaliasCommand commandOne = new UnaliasCommand("commandOne");
        UnaliasCommand commandTwo = new UnaliasCommand("commandTwo");

        // same object -> true
        assertTrue(commandOne.equals(commandOne));

        // same alias name -> returns true
        UnaliasCommand commandOneCopy = new UnaliasCommand("commandOne");
        assertTrue(commandOne.equals(commandOneCopy));

        // different instance types -> returns false
        assertFalse(commandOne.equals(new Alias()));

        // null -> returns false
        assertFalse(commandOne.equals(null));

        // different alias name -> returns false
        assertFalse(commandOne.equals(commandTwo));
    }
}
