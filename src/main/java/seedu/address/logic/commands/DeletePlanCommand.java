package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAN_NUMBER;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.plan.Plan;

/**
 * Deletes a plan identified using it's displayed index from the address book.
 */
public class DeletePlanCommand extends Command {

    public static final String COMMAND_WORD = "deletep";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the plan identified by the index number used in the displayed plan list.\n"
            + "Parameters: "
            + PREFIX_PLAN_NUMBER + "PLAN NUMBER (must be a valid plan number)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PLAN_NUMBER + "1";

    public static final String MESSAGE_DELETE_PLAN_SUCCESS = "Plan deleted: Plan %1$s";

    private final Index targetIndex;

    /**
     * Creates a DeletePlanCommand to delete the specified {@code Plan}
     */
    public DeletePlanCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Plan> lastShownList = model.getFilteredPlanList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PLAN_DISPLAYED_INDEX);
        }

        Plan planToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePlan(planToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PLAN_SUCCESS,
                targetIndex.getOneBased() + planToDelete.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletePlanCommand // instanceof handles nulls
                && targetIndex.equals(((DeletePlanCommand) other).targetIndex)); // state check
    }
}
