package seedu.us.among.logic.commands;

import static seedu.us.among.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.us.among.model.Model;
import seedu.us.among.model.ModelManager;

public class ExitCommandTest {
    public static final boolean SHOW_HELP = false;
    public static final boolean IS_EXIT = true;
    public static final boolean IS_LIST = false;

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT,
                SHOW_HELP, IS_EXIT, IS_LIST);
        assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    }
}
