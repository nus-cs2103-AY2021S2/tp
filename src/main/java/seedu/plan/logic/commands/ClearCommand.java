package seedu.plan.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.plan.model.Model;
import seedu.plan.model.ModulePlanner;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Plans list has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setPlans(new ModulePlanner());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
