package seedu.address.model.alias;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAliases.ADD_ALIAS;
import static seedu.address.testutil.TypicalAliases.ADD_COMMAND;
import static seedu.address.testutil.TypicalAliases.ADD_COMMAND_ALIAS;
import static seedu.address.testutil.TypicalAliases.DELETE_ALIAS;
import static seedu.address.testutil.TypicalAliases.DELETE_COMMAND;

import org.junit.jupiter.api.Test;

public class CommandAliasTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CommandAlias(null, null));
        assertThrows(NullPointerException.class, () -> new CommandAlias(ADD_ALIAS, null));
        assertThrows(NullPointerException.class, () -> new CommandAlias(null, ADD_COMMAND));
    }

    @Test
    public void isSameCommandAlias_sameCommandAlias_returnsTrue() {
        // same alias, same command -> true
        assertTrue(ADD_COMMAND_ALIAS.isSameCommandAlias(new CommandAlias(ADD_ALIAS, ADD_COMMAND)));

        // same alias, different command -> true
        assertTrue(ADD_COMMAND_ALIAS.isSameCommandAlias(new CommandAlias(ADD_ALIAS, DELETE_COMMAND)));
    }

    @Test
    public void isSameCommandAlias_differentCommandAlias_returnsFalse() {
        // different alias, different command -> true
        assertFalse(ADD_COMMAND_ALIAS.isSameCommandAlias(new CommandAlias(DELETE_ALIAS, DELETE_COMMAND)));

        // different alias, same command -> true
        assertFalse(ADD_COMMAND_ALIAS.isSameCommandAlias(new CommandAlias(DELETE_ALIAS, ADD_COMMAND)));
    }

    @Test
    public void isSameCommandAlias() {
        assertEquals(ADD_COMMAND_ALIAS, new CommandAlias(ADD_ALIAS, ADD_COMMAND));
    }
}
