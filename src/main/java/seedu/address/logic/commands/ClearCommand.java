package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.ResidenceTracker;
import seedu.address.model.Model;

/**
 * Clears the residence tracker.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Residence Tracker has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setResidenceTracker(new ResidenceTracker());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
