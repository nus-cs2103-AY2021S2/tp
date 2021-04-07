package seedu.taskify.logic.commands;

import seedu.taskify.logic.commands.exceptions.CommandException;
import seedu.taskify.model.Model;

public class ExpiredCommand extends Command {
    public static final String COMMAND_WORD = "expired";
    public static final String MESSAGE_USAGE = "Switched to expired task tab";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return CommandResult.switchToExpired(MESSAGE_USAGE);
    }

}
