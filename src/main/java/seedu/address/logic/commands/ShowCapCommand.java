package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.plan.Plan;


/**
 * Concrete implementation of ShowCommand to show CAP.
 *
 * Shows the user their Cumulative Average Points (CAP) for all completed modules with
 * an input grade.
 *
 * The CAP is calculated by taking a weighted average of each grade the user got,
 * weighted by the number of MCs the module is worth.
 */
public class ShowCapCommand extends ShowCommand {
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Plan currentPlan = model.getMasterPlan();
        double currentCap = currentPlan.getCurrentCap();
        return new CommandResult(String.format(MESSAGE_SUCCESS_CAP, currentCap));
    }

    @Override
    public boolean equals(Object other) {
        return other == this || other instanceof ShowCapCommand;
    }
}
