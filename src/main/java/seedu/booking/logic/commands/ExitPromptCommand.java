package seedu.booking.logic.commands;

import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;

/**
 * Exits prompting process
 */
public class ExitPromptCommand extends Command {

    public static final String COMMAND_WORD = "exit_prompt";


    /**
     * Exits prompting process right away
     */
    public CommandResult execute(Model model) {

        ModelManager.setStateInactive();
        return new CommandResult("Prompting exited;");
    }
}
