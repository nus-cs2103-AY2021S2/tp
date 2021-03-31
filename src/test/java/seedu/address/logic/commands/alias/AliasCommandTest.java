package seedu.address.logic.commands.alias;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.alias.AliasUtil.ALIAS_1_TO_2;
import static seedu.address.testutil.alias.AliasUtil.ALIAS_2_TO_1;
import static seedu.address.testutil.alias.AliasUtil.ALIAS_HELP;
import static seedu.address.testutil.alias.AliasUtil.VALID_ALIAS_1;
import static seedu.address.testutil.alias.AliasUtil.VALID_ALIAS_2;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Alias;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class AliasCommandTest {
    @Test
    public void equals() {
        AliasCommand aliasOne = new AliasCommand(VALID_ALIAS_1);
        AliasCommand aliasTwo = new AliasCommand(VALID_ALIAS_2);

        // same object -> true
        assertTrue(aliasOne.equals(aliasOne));

        // same alias -> returns true
        AliasCommand aliasOneCopy = new AliasCommand(VALID_ALIAS_1);
        assertTrue(aliasOne.equals(aliasOneCopy));

        // different instance types -> returns false
        assertFalse(aliasOne.equals(new Alias()));

        // null -> returns false
        assertFalse(aliasOne.equals(null));

        // different alias -> returns false
        assertFalse(aliasOne.equals(aliasTwo));
    }

    @Test
    public void execute_aliasNameIsReserved_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        AliasCommand alias = new AliasCommand(ALIAS_HELP);

        assertThrows(CommandException.class, () -> alias.execute(model));
    }

    @Test
    public void execute_aliasCommandIsRecursive_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        AliasCommand alias = new AliasCommand(ALIAS_2_TO_1);

        model.addAlias(ALIAS_1_TO_2);
        assertThrows(CommandException.class, () -> alias.execute(model));
    }

    @Test
    public void execute_aliasCommandIsValid_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.addAlias(VALID_ALIAS_1);

        assertCommandSuccess(new AliasCommand(VALID_ALIAS_1), model, String.format(AliasCommand.MESSAGE_SUCCESS,
                VALID_ALIAS_1.getAliasName()), expectedModel);
    }
}
