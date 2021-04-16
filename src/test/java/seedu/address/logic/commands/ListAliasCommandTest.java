package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ListAliasCommand.SHOWING_ALIASES_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListAliasCommand.
 */
public class ListAliasCommandTest {

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_listAlias_success() {
        CommandResult expectedCommandResult = new CommandResult(
                String.format(SHOWING_ALIASES_MESSAGE, model.getNumOfCommandAlias()), false, true, false);
        assertCommandSuccess(new ListAliasCommand(), model, expectedCommandResult, expectedModel);
    }
}
