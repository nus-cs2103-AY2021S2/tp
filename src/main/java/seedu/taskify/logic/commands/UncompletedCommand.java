package seedu.taskify.logic.commands;

import seedu.taskify.logic.commands.exceptions.CommandException;
import seedu.taskify.model.Model;

public class UncompletedCommand extends Command {
    public static final String COMMAND_WORD = "uncompleted";
    public static final String MESSAGE_USAGE = "Switched to uncompleted tab";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return CommandResult.switchToUncompleted(MESSAGE_USAGE);
    }

}
