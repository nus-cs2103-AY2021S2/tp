package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.plan.Plan;
import seedu.address.model.plan.Semester;


/**
 * Command to check validity of plans.
 * The validity of plans are checked again automatically each time
 * the MasterCommand is ran, so plans are always validated at least once.
 */
public class ValidateCommand extends Command {

    public static final String COMMAND_WORD = "validate";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + "Show validity of all plans.";

    public static final String MESSAGE_SUCCESS = "Showing validity of plans";

    private Plan masterPlan;
    private Semester currentSemester;

    /**
     * Creates a ValidateCommand to output validity of plans
     */
    public ValidateCommand() {
        this.masterPlan = null;
        this.currentSemester = null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        masterPlan = model.getMasterPlan(); // Exception if not set
        currentSemester = model.getCurrentSemester(); // Exception if not set

        model.validate(masterPlan, currentSemester);
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof ValidateCommand) {
            ValidateCommand o = (ValidateCommand) other;
            return this.masterPlan == o.masterPlan;
        } else {
            return false;
        }
    }
}
