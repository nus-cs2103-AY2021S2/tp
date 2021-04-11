package seedu.booking.logic.commands;

import seedu.booking.logic.StatefulLogicManager;
import seedu.booking.model.Model;

/**
 * Exits prompting process
 */
public class PromptExitCommand extends Command {

    public static final String COMMAND_WORD = "exit_prompt";


    /**
     * Exits prompting process right away
     */
    public CommandResult execute(Model model) {

        StatefulLogicManager.setStateInactive();
        return new CommandResult("Prompting exited.");
    }
}
