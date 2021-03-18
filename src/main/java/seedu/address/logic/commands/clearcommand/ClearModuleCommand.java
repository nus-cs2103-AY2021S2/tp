package seedu.address.logic.commands.clearcommand;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.RemindMe;

/**
 * Clears the address book.
 */
public class ClearModuleCommand extends ClearCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Clears ALL modules in RemindMe. "
            + "Parameters: " + PREFIX_MODULE
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_MODULE;

    public static final String MESSAGE_SUCCESS = "Modules have been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.resetModules();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
