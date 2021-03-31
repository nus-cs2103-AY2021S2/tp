package seedu.address.logic.commands.alias;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Alias;

public class ListAliasCommandTest {
    @Test
    public void equals() {
        ListAliasCommand commandOne = new ListAliasCommand();
        ListAliasCommand commandTwo = new ListAliasCommand();

        // same object -> true
        assertTrue(commandOne.equals(commandOne));

        // same instance type -> returns true
        assertTrue(commandOne.equals(commandTwo));

        // different instance types -> returns false
        assertFalse(commandOne.equals(new Alias()));

        // null -> returns false
        assertFalse(commandOne.equals(null));
    }
}
