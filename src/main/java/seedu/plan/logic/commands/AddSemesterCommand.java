package seedu.plan.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.plan.logic.parser.CliSyntax.PREFIX_PLAN_NUMBER;
import static seedu.plan.logic.parser.CliSyntax.PREFIX_SEM_NUMBER;

import java.util.List;

import seedu.plan.commons.core.Messages;
import seedu.plan.commons.core.index.Index;
import seedu.plan.logic.commands.exceptions.CommandException;
import seedu.plan.model.Model;
import seedu.plan.model.plan.Plan;
import seedu.plan.model.plan.Semester;

/**
 * Adds a semester to a plan in the address book.
 */
public class AddSemesterCommand extends Command {

    public static final String COMMAND_WORD = "adds";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a semester to a plan. "
            + "Parameters: "
            + PREFIX_PLAN_NUMBER + "PLAN NUMBER "
            + PREFIX_SEM_NUMBER + "SEM NUMBER \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PLAN_NUMBER + "1 "
            + PREFIX_SEM_NUMBER + "2 ";

    public static final String MESSAGE_SUCCESS = "New semester added to Plan %1$s: %2$s";
    public static final String MESSAGE_DUPLICATE_SEMESTER = "This semester already exists";

    private final Semester toAdd;
    private final Index toAddTo; // plan number

    /**
     * Creates an AddSemesterCommand to add the specified {@code Semester}
     */
    public AddSemesterCommand(Index planNumber, Semester semester) {
        requireNonNull(semester);
        toAdd = semester;
        toAddTo = planNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Plan> lastShownList = model.getFilteredPlanList();

        if (toAddTo.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PLAN_DISPLAYED_INDEX);
        }

        if (toAdd.getSemNumber() <= 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_SEM_NUMBER);
        }

        if (model.hasSemester(toAddTo.getZeroBased(), toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_SEMESTER);
        }

        model.addSemester(toAddTo.getZeroBased(), toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAddTo.getOneBased(), toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddSemesterCommand // instanceof handles nulls
                && toAdd.equals(((AddSemesterCommand) other).toAdd));
    }
}
