package seedu.address.logic.commands.clearcommand;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Clears contacts in RemindMe.
 */
public class ClearPersonsCommand extends ClearCommand {

    public static final String MESSAGE_USAGE = "After n/, it should be empty!";

    public static final String MESSAGE_SUCCESS = "All persons have been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.resetPersons();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
