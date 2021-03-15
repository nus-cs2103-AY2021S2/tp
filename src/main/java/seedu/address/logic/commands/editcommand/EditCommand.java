package seedu.address.logic.commands.editcommand;

import seedu.address.logic.commands.Command;

public abstract class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = EditPersonCommand.MESSAGE_USAGE
            + "\n" + EditModuleCommand.MESSAGE_USAGE;
//            + "\n" + EditAssignmentCommand.MESSAGE_USAGE
//            + "\n" + EditExamCommand.MESSAGE_USAGE;


}
