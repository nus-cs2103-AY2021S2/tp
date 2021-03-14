package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.plan.Plan;

import static java.util.Objects.requireNonNull;

/**
 * Concrete implementation of ShowCommand to show CAP.
 *
 * Shows the user their Cumulative Average Points (CAP) for all completed modules with
 * an input grade.
 *
 * The CAP is calculated by taking a weighted average of each grade the user got,
 * weighted by the number of MCs the module is worth.
 */
public class ShowCAPCommand extends ShowCommand {
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Plan currentPlan = model.getCurrentPlan();
        double currentCAP = currentPlan.getCurrentCAP();
        return new CommandResult(String.format(MESSAGE_SUCCESS_CAP, currentCAP));
    }

    @Override
    public boolean equals(Object other) {
        return other == this || other instanceof ShowCAPCommand;
    }
}
