package seedu.address.logic.commands.clearcommand;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENERAL_EVENT;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Clears general events in RemindMe.
 */
public class ClearEventsCommand extends ClearCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Clears ALL general events in RemindMe. "
            + "Parameters: " + PREFIX_GENERAL_EVENT
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_GENERAL_EVENT;

    public static final String MESSAGE_SUCCESS = "General events have been cleared";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.resetEvents();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
