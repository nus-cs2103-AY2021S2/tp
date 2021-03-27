package seedu.us.among.logic.commands;

import seedu.us.among.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final boolean SHOW_HELP = true;
    public static final boolean IS_EXIT = false;
    public static final boolean IS_LIST = false;

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, SHOW_HELP, IS_EXIT, IS_LIST);
    }
}
