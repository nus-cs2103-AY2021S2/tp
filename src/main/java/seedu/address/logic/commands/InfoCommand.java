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
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < md.length; i++) {
            str.append(md[i].toString());
            str.append("\n\n");
        }
        System.out.println(str);
        return new CommandResult(str.toString());
//        return new CommandResult(MESSAGE_SUCCESS);
        //
    }
}

