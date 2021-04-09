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

    public static final String HELP_MESSAGE_DELETE_TASK = DeleteTaskCommand.SHORT_MESSAGE_USAGE;

    public static final String HELP_MESSAGE_DELETE_FIELD = DeleteFieldCommand.SHORT_MESSAGE_USAGE;

    public static final String SHOWING_HELP_MESSAGE = "Here are some frequently used commands:\n\n"
            + "1. Adding a task: \n" + HELP_MESSAGE_ADD + "\n\n"
            + "2. Viewing all task:  \n" + HELP_MESSAGE_LIST + "\n"
            + "3. Editing a task: \n" + HELP_MESSAGE_EDIT + "\n"
            + "4. Finding tasks: \n" + HELP_MESSAGE_FIND + "\n"
            + "5. Deleting a task: \n" + HELP_MESSAGE_DELETE_TASK + "\n"
            + "6. Deleting a task's attribute: \n" + HELP_MESSAGE_DELETE_FIELD + "\n"
            + "For more commands, features and a detailed description of the above features, read our user guide.\n"
            + "You may find the user guide here: https://ay2021s2-cs2103t-t10-2.github.io/tp/UserGuide.html";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE);
    }
}
