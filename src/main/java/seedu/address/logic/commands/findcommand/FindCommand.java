package seedu.address.logic.commands.findcommand;

import seedu.address.logic.commands.Command;

public abstract class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = "Command: " + COMMAND_WORD + "\n"
            + "Person: find n/NAME\n"
            + "Module: find m/TITLE\n"
            + "Event: find g/DESCRIPTION";
}
