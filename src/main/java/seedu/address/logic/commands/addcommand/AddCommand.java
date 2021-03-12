package seedu.address.logic.commands.addcommand;

import seedu.address.logic.commands.Command;

public abstract class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = AddPersonCommand.MESSAGE_USAGE
            + "\n" + AddModuleCommand.MESSAGE_USAGE
            + "\n" + AddAssignmentCommand.MESSAGE_USAGE
            + "\n" + AddExamCommand.MESSAGE_USAGE;


}
