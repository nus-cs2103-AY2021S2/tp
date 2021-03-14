package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.diet.DietPlan;
import seedu.address.model.diet.DietPlanList;

/**
 * View plan details command
 */
public class ViewPlanCommand extends Command {

    public static final String COMMAND_WORD = "plan";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View details of a specific diet plan.\n"
            + "Parameters: plan p/ID";

    public static final String MESSAGE_OUT_OF_BOUNDS = "There is no diet plan for that index!";

    private int index;

    /**
     * Creates a view plan command to get the details of a specific diet plan.
     */
    public ViewPlanCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        DietPlanList dietPlanList = model.getDietPlanList();
        try {
            DietPlan dietPlan = dietPlanList.getDietPlan(this.index);
            return new CommandResult(dietPlan.viewPlan());
        } catch (IndexOutOfBoundsException outOfBounds) {
            throw new CommandException(MESSAGE_OUT_OF_BOUNDS);
        }

    }
}
