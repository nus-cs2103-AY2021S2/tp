package seedu.address.logic.commands.editcommand;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.addcommand.AddAssignmentCommand;
import seedu.address.logic.commands.addcommand.AddExamCommand;
import seedu.address.logic.commands.addcommand.AddModuleCommand;
import seedu.address.logic.commands.addcommand.AddPersonCommand;

public abstract class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = EditPersonCommand.MESSAGE_USAGE
            + "\n" + EditModuleCommand.MESSAGE_USAGE;
//            + "\n" + EditAssignmentCommand.MESSAGE_USAGE
//            + "\n" + EditExamCommand.MESSAGE_USAGE;


}
