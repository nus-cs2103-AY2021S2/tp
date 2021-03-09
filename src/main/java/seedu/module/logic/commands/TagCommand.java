package seedu.module.logic.commands;

import seedu.module.logic.commands.exceptions.CommandException;
import seedu.module.model.Model;

public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult("Hello from tag");
    }
}
