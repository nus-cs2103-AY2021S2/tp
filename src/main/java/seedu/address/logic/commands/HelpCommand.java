package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String HELP_MESSAGE_DISPLAY = "Opened help window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(HELP_MESSAGE_DISPLAY, true, false);
    }
}
