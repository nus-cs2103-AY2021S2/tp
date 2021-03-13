package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String HELP_MESSAGE_ADD = "add n/TITLE";

    public static final String HELP_MESSAGE_LIST = "list";

    public static final String HELP_MESSAGE_ADD_DEADLINE = "add INDEX [d/DATE]";

    public static final String HELP_MESSAGE_EDIT = "edit INDEX [n/TITLE] [p/PHONE] [e/EMAIL] [d/DESCRIPTION] [t/TAG]";

    public static final String HELP_MESSAGE_FIND = "find KEYWORD [MORE_KEYWORDS]";

    public static final String HELP_MESSAGE_DELETE = "delete INDEX";

    public static final String HELP_MESSAGE_CLEAR = "clear";

    public static final String SHOWING_HELP_MESSAGE = "Here is a list of possible instructions:\n"
            + HELP_MESSAGE_ADD + "\n"
            + HELP_MESSAGE_LIST + "\n"
            + HELP_MESSAGE_ADD_DEADLINE + "\n"
            + HELP_MESSAGE_EDIT + "\n"
            + HELP_MESSAGE_FIND + "\n"
            + HELP_MESSAGE_DELETE + "\n"
            + HELP_MESSAGE_CLEAR + "\n"
            + "\nFor more instructions and a detailed description of the above features, read our user guide.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
