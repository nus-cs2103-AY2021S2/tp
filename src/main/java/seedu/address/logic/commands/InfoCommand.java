package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;


import seedu.address.model.Model;
import seedu.address.storage.JsonModule;


public class InfoCommand extends  Command{
    public static final String COMMAND_WORD = "info";

    public static final String MESSAGE_SUCCESS = "Listed all modules";

    @Override
    public CommandResult execute(Model model) {
        //test
        requireNonNull(model);
        JsonModule[] md = model.getAddressBook().getModuleInfo();
        return new CommandResult(MESSAGE_SUCCESS);
        //
    }
}

