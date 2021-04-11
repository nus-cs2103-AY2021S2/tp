package seedu.cakecollate.logic.commands;

import static seedu.cakecollate.commons.core.Messages.MESSAGE_ORDERS_LISTED_OVERVIEW;
import static seedu.cakecollate.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.cakecollate.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.cakecollate.model.Model;
import seedu.cakecollate.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_mainToHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpToHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        HelpCommand helpCommand = new HelpCommand();
        helpCommand.execute(model);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_mainToHelpViaList_success() {
        CommandResult expectedCommandResult =
                new CommandResult(String.format(MESSAGE_ORDERS_LISTED_OVERVIEW, model.getFilteredOrderList().size()));
        HelpCommand helpCommand = new HelpCommand();
        helpCommand.execute(model);
        assertCommandSuccess(new ListCommand(), model, expectedCommandResult, expectedModel);
    }
}
