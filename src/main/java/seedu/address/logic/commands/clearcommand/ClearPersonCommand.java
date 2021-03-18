package seedu.address.logic.commands.clearcommand;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

public class ClearPersonCommand extends ClearCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Clears ALL contacts in RemindMe. "
            + "Parameters: " + PREFIX_NAME
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_NAME;

    public static final String MESSAGE_SUCCESS = "Contacts has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.resetPersons();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
