package seedu.address.logic.commands.alias;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.alias.AliasUtil.VALID_ALIAS_1;
import static seedu.address.testutil.alias.AliasUtil.VALID_ALIAS_2;
import static seedu.address.testutil.alias.AliasUtil.VALID_ALIAS_3;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

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
        assertFalse(commandOne.equals(1));

        // null -> returns false
        assertFalse(commandOne.equals(null));
    }

    @Test
    public void execute_noAliasCreated_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        assertCommandSuccess(new ListAliasCommand(), model, String.format(ListAliasCommand.MESSAGE_EMPTY_ALIAS,
                VALID_ALIAS_1.getAliasName()), model);
    }

    @Test
    public void execute_someAliasesCreated_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.addAlias(VALID_ALIAS_1);
        model.addAlias(VALID_ALIAS_2);
        model.addAlias(VALID_ALIAS_3);

        StringBuilder expectedMessage = new StringBuilder();
        expectedMessage.append(String.format(ListAliasCommand.MESSAGE_SUCCESS_HEADER, model.getAliasMapping().size()));
        expectedMessage.append(model.getAliasMapping().toString());

        assertCommandSuccess(new ListAliasCommand(), model, expectedMessage.toString(), model);
    }
}
