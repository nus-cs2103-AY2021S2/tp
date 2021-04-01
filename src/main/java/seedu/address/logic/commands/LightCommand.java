package seedu.address.logic.commands;

import seedu.address.model.Model;


public class LightCommand extends Command {

    public static final String COMMAND_WORD = "light";

    public static final String MESSAGE_SUCCESS = "Changed to light mode";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SUCCESS, false, false, true, false);
    }
}

