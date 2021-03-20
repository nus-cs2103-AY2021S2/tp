package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.ProgressCalculator;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.diet.DietPlan;
import seedu.address.model.food.FoodIntakeList;
import seedu.address.model.user.User;

/**
 * Generate progress report command
 */
public class RunProgressCalculatorCommand extends Command {
    public static final String COMMAND_WORD = "progress";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Generates the progress "
            + "report for the active diet plan.\n";

    /**
     * Generates progress report for the active diet plan
     */
    public RunProgressCalculatorCommand() {

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        DietPlan dietPlan = model.getActiveDiet();
        FoodIntakeList foodIntakeList = model.getFoodIntakeList();
        User user = model.getUser();

        String result = ProgressCalculator.calculateProgress(foodIntakeList, dietPlan, user);

        return new CommandResult(result);

    }
}
