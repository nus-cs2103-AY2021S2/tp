package seedu.taskify.logic.commands;

import seedu.taskify.logic.commands.exceptions.CommandException;
import seedu.taskify.model.Model;

public class CompletedCommand extends Command {
    public static final String COMMAND_WORD = "completed";
    public static final String MESSAGE_USAGE = "Switched to completed tab";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return CommandResult.switchToCompleted(MESSAGE_USAGE);
    }

}
