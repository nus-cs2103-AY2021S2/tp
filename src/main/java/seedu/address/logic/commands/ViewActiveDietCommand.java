package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.diet.DietPlan;

/**
 * View active diet command
 */
public class ViewActiveDietCommand extends Command {
    public static final String COMMAND_WORD = "plan_current";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View the active diet plan.";

    public static final String MESSAGE_NO_PLAN = "You are currently not on any active diet plan!";

    public static final String MESSAGE_SUCCESS = "Your active diet plan is:\n";

    /**
     * Creates a view active plan command to get the details of the active diet plan.
     */
    public ViewActiveDietCommand() {

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        DietPlan dietPlan = model.getActiveDiet();
        if (dietPlan != null) {
            String planDetails = dietPlan.viewPlan();
            return new CommandResult(MESSAGE_SUCCESS + planDetails);
        } else {
            return new CommandResult(MESSAGE_NO_PLAN);
        }
    }
}
