package seedu.booking.logic.commands;

import static seedu.booking.logic.commands.CommandShowType.COMMAND_SHOW_PREVIOUS;

import seedu.booking.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = "Use the command " + COMMAND_WORD
            + "to display program usage instructions.\n";

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, COMMAND_SHOW_PREVIOUS, false);
    }
}
