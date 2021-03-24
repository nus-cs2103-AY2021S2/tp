package seedu.taskify.logic.commands;

import seedu.taskify.logic.commands.exceptions.CommandException;
import seedu.taskify.model.Model;

public class HomeCommand extends Command {
    public static final String COMMAND_WORD = "home";
    public static final String MESSAGE_USAGE = "Switched to home tab";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return CommandResult.switchToHome(MESSAGE_USAGE);
    }

}
