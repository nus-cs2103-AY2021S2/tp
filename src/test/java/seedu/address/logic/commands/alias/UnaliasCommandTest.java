package seedu.address.logic.commands.alias;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.alias.AliasUtil.VALID_ALIAS_1;
import static seedu.address.testutil.alias.AliasUtil.VALID_ALIAS_2;
import static seedu.address.testutil.alias.AliasUtil.VALID_ALIAS_3;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

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
        assertFalse(commandOne.equals(1));

        // null -> returns false
        assertFalse(commandOne.equals(null));

        // different alias name -> returns false
        assertFalse(commandOne.equals(commandTwo));
    }

    @Test
    public void execute_aliasNotFound_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.addAlias(VALID_ALIAS_2);
        UnaliasCommand unalias = new UnaliasCommand(VALID_ALIAS_3.getAliasName());

        assertThrows(CommandException.class, () -> unalias.execute(model));
    }

    @Test
    public void execute_aliasFound_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.addAlias(VALID_ALIAS_1);
        model.addAlias(VALID_ALIAS_2);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.addAlias(VALID_ALIAS_2);

        assertCommandSuccess(new UnaliasCommand(VALID_ALIAS_1.getAliasName()), model,
                String.format(UnaliasCommand.MESSAGE_SUCCESS, VALID_ALIAS_1.getAliasName()), expectedModel);
    }
}
