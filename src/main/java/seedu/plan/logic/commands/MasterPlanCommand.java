package seedu.plan.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.plan.logic.parser.CliSyntax.PREFIX_PLAN_NUMBER;

import java.util.List;

import seedu.plan.commons.core.Messages;
import seedu.plan.commons.core.index.Index;
import seedu.plan.logic.commands.exceptions.CommandException;
import seedu.plan.model.Model;
import seedu.plan.model.plan.Plan;


/**
 * Marks a Semester as the current semester.
 */
public class MasterPlanCommand extends Command {

    public static final String COMMAND_WORD = "master";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the supplied plan as the master one. "
            + "Parameters: "
            + PREFIX_PLAN_NUMBER + "PLAN_NUMBER";

    public static final String MESSAGE_SUCCESS = "Successfully marked plan as master: %1$d";

    private final Index masterPlanIndex;

    /**
     * Creates a MasterPlanCommand to mark the given semester number as being the current one.
     *
     * @param masterPlanIndex The plan Index to be marked as the master one.
     */
    public MasterPlanCommand(Index masterPlanIndex) {
        this.masterPlanIndex = masterPlanIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Plan> lastShownList = model.getFilteredPlanList();

        // Check that the masterPlanIndex corresponds to a plan that is present
        if (masterPlanIndex.getOneBased() > lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PLAN_DISPLAYED_INDEX);
        }

        // only one plan should be valid at a time
        for (Plan p : lastShownList) {
            Plan originalPlan = p;
            p.setMasterPlan(false);
            p.setIsValid(false);
            model.setPlan(originalPlan, p);
        }

        Plan masterPlan;
        try {
            masterPlan = lastShownList.get(masterPlanIndex.getZeroBased());
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("Set a new Master Plan as the old one is no longer valid.");
        }
        Plan originalPlan = masterPlan;
        // masterPlan.setIsValid(true); // master plan can be invalid if pre-reqs are not met, run validateCommand.
        model.setMasterPlan(masterPlan);
        model.setPlan(originalPlan, masterPlan);

        return new CommandResult(String.format(MESSAGE_SUCCESS, masterPlanIndex.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof MasterPlanCommand) {
            MasterPlanCommand o = (MasterPlanCommand) other;
            return this.masterPlanIndex.equals(o.masterPlanIndex);
        } else {
            return false;
        }
    }
}
