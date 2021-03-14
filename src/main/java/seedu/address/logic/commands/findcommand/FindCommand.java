package seedu.address.logic.commands.findcommand;

import seedu.address.logic.commands.Command;

public abstract class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = FindPersonCommand.MESSAGE_USAGE
            + "\n" + FindModuleCommand.MESSAGE_USAGE;
}
