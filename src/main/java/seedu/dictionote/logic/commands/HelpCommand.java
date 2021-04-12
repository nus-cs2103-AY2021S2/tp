package seedu.dictionote.logic.commands;

import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.model.Model;

/**
 * Shows a help window to user guide.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, UiAction.HELP);
    }
}
