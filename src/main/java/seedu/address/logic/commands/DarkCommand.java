package seedu.address.logic.commands;

import seedu.address.model.Model;


public class DarkCommand extends Command {

    public static final String COMMAND_WORD = "dark";

    public static final String MESSAGE_SUCCESS = "Changed to dark mode";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SUCCESS, false, false, false, true);
    }
}
