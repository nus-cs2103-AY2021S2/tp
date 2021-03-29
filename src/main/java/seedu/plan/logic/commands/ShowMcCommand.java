package seedu.plan.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.plan.logic.commands.exceptions.CommandException;
import seedu.plan.model.Model;
import seedu.plan.model.plan.Semester;


/**
 * Concrete implementation of ShowCommand to show MCs.
 *
 * Shows the user the total number of MCs that the user is doing in
 * the semester marked as the `current semester`.
 */
public class ShowMcCommand extends ShowCommand {

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Semester currentSem = model.getCurrentSemester();
        int totalMCs = currentSem.getTotalMCs();
        return new CommandResult(String.format(MESSAGE_SUCCESS_MCS, totalMCs));
    }

    @Override
    public boolean equals(Object other) {
        return other == this || other instanceof ShowMcCommand;
    }
}
