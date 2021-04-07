package seedu.address.logic.commands.clearcommand;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Clears general events in RemindMe.
 */
public class ClearEventsCommand extends ClearCommand {

    public static final String MESSAGE_USAGE = "After g/, it should be empty!";

    public static final String MESSAGE_SUCCESS = "General events have been cleared";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.resetEvents();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
