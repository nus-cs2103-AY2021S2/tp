package seedu.address.logic.commands.addcommand;

import seedu.address.logic.commands.Command;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

public abstract class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = AddModuleCommand.MESSAGE_USAGE + "\n"
            + AddPersonCommand.MESSAGE_USAGE;


}
