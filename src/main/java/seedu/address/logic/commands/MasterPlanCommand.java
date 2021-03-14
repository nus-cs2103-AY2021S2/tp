package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.plan.Plan;

import java.util.List;

/**
 * Marks a Semester as the current semester.
 */
public class MasterPlanCommand extends Command {

    public static final String COMMAND_WORD = "master";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "p/PLAN_NUMBER\n: " +
            "Marks the supplied plan as the master one.";

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
        List<Plan> lastShownList = model.getFilteredPersonList();

        if (masterPlanIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }


        Plan masterPlan = lastShownList.get(masterPlanIndex.getZeroBased());
        model.setMasterPlan(masterPlan);
        return new CommandResult(String.format(MESSAGE_SUCCESS, masterPlanIndex.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof MasterPlanCommand) {
            MasterPlanCommand o = (MasterPlanCommand) other;
            return this.masterPlanIndex == o.masterPlanIndex;
        } else {
            return false;
        }
    }
}
