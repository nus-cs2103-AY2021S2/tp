package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Displays a list of commonly used commands.
 * For a full list of all commands, Users have to refer to the user guide.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;


    public static final String HELP_MESSAGE_ADD = AddCommand.SHORT_MESSAGE_USAGE;

    public static final String HELP_MESSAGE_LIST = ListCommand.SHORT_MESSAGE_USAGE;

    public static final String HELP_MESSAGE_EDIT = EditCommand.SHORT_MESSAGE_USAGE;

    public static final String HELP_MESSAGE_FIND = FindCommand.SHORT_MESSAGE_USAGE;

    public static final String HELP_MESSAGE_COUNTDOWN = CountdownCommand.SHORT_MESSAGE_USAGE;

    public static final String HELP_MESSAGE_CLEAR = ClearCommand.SHORT_MESSAGE_USAGE;

    public static final String HELP_MESSAGE_DELETE_TASK = DeleteTaskCommand.SHORT_MESSAGE_USAGE;

    public static final String HELP_MESSAGE_DELETE_FIELD = DeleteFieldCommand.SHORT_MESSAGE_USAGE;


    public static final String SHOWING_HELP_MESSAGE = "Here is a list of possible instructions:\n\n"
            + HELP_MESSAGE_ADD + "\n"
            + HELP_MESSAGE_LIST + "\n"
            + HELP_MESSAGE_EDIT + "\n"
            + HELP_MESSAGE_FIND + "\n"
            + HELP_MESSAGE_DELETE_TASK + "\n"
            + HELP_MESSAGE_DELETE_FIELD + "\n"
            + HELP_MESSAGE_CLEAR + "\n"
            + HELP_MESSAGE_COUNTDOWN + "\n"
            + "For more instructions and a detailed description of the above features, read our user guide.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
