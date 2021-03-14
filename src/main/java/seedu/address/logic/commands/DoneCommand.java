package seedu.address.logic.commands;

import seedu.address.model.Model;

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("This is printed on screen.");
    }
}
