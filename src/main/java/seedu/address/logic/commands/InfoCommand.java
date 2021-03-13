package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;


import seedu.address.model.Model;


public class InfoCommand extends  Command{
    public static final String COMMAND_WORD = "info";

    public static final String MESSAGE_SUCCESS = "Listed all modules";

    @Override
    public CommandResult execute(Model model) {
        //test
        requireNonNull(model);


        return new CommandResult(MESSAGE_SUCCESS);
        //
    }
}

