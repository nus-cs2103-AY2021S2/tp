package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.diet.DietPlan;
import seedu.address.model.diet.DietPlanList;

/**
 * Set active diet plan command
 */
public class SetActiveDietCommand extends Command {
    public static final String COMMAND_WORD = "plan_set";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Set the active diet plan.\n"
            + "Parameters: plan_set p/ID";

    public static final String MESSAGE_OUT_OF_BOUNDS = "There is no diet plan for that index!";

    public static final String MESSAGE_SUCCESS = "Your active diet plan is now:\n";

    private int index;

    /**
     * Creates a set plan command to set the active diet plan for the user
     */
    public SetActiveDietCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        DietPlanList dietPlanList = model.getDietPlanList();
        try {
            DietPlan dietPlan = dietPlanList.getDietPlan(this.index);
            model.setActiveDiet(dietPlan);

            return new CommandResult(MESSAGE_SUCCESS + dietPlan.viewPlan());
        } catch (IndexOutOfBoundsException outOfBounds) {
            throw new CommandException(MESSAGE_OUT_OF_BOUNDS);
        }

    }
}
