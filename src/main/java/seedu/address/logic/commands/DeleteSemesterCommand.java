package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAN_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEM_NUMBER;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.plan.Plan;
import seedu.address.model.plan.Semester;

/**
 * Deletes a semester from the plan identified using it's displayed index from the address book.
 */
public class DeleteSemesterCommand extends Command {

    public static final String COMMAND_WORD = "deletes";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a semester from the plan identified by the plan number.\n"
            + "Parameters: "
            + PREFIX_PLAN_NUMBER + "PLAN NUMBER"
            + PREFIX_SEM_NUMBER + "SEM NUMBER\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PLAN_NUMBER + "1 "
            + PREFIX_SEM_NUMBER + "1";

    public static final String MESSAGE_DELETE_SEMESTER_SUCCESS = "Semester deleted from Plan %1$s: %2$s";

    private final Index targetPlanIndex;
    private final int targetSemNumber;

    /**
     * Creates a DeleteSemesterCommand to delete the specified {@code Semester}
     */
    public DeleteSemesterCommand(Index targetPlanIndex, int targetSemNumber) {
        this.targetPlanIndex = targetPlanIndex;
        this.targetSemNumber = targetSemNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Plan> lastShownList = model.getFilteredPlanList();

        if (targetPlanIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PLAN_DISPLAYED_INDEX);
        }

        Plan planToDeleteFrom = lastShownList.get(targetPlanIndex.getZeroBased());
        if (!planToDeleteFrom.hasSemester(targetSemNumber)) {
            throw new CommandException(Messages.MESSAGE_INVALID_SEM_NUMBER);
        }

        Semester semesterToDelete = planToDeleteFrom.getSemester(targetSemNumber);
        model.deleteSemester(planToDeleteFrom, semesterToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_SEMESTER_SUCCESS,
                targetPlanIndex.getOneBased(), semesterToDelete.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteSemesterCommand // instanceof handles nulls
                && targetPlanIndex == (((DeleteSemesterCommand) other).targetPlanIndex)
                && targetSemNumber == (((DeleteSemesterCommand) other).targetSemNumber)); // state check
    }
}
