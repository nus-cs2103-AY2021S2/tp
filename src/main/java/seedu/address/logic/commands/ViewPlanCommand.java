package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.PlanInfoCalculator;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.diet.DietPlan;
import seedu.address.model.diet.DietPlanList;
import seedu.address.model.diet.PlanType;
import seedu.address.model.user.User;

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

    private String getResult(DietPlan dietPlan, PlanInfoCalculator planInfoCalculator) {
        String result = "Here's more information about the ";
        PlanType planType = dietPlan.getPlanType();
        switch (planType) {
        case WEIGHTGAIN:
            result += "Weight Gain plan:\n";
            break;
        case WEIGHTLOSS:
            result += "Weight Loss plan:\n";
            break;
        default:
            result += "Weight Maintenance plan:\n";
            break;
        }

        String calorieString = String.format("%.2f", planInfoCalculator.getCalories());
        String carboString = String.format("%.2f", planInfoCalculator.getCarbohydrates());
        String proteinString = String.format("%.2f", planInfoCalculator.getProteins());
        String fatString = String.format("%.2f", planInfoCalculator.getFats());

        result += "Daily calories intake: " + calorieString + " kcal\n";
        result += "Daily Protein intake: " + proteinString + " g\n";
        result += "Daily Carbohydrates intake: " + carboString + " g\n";
        result += "Daily Fat intake: " + fatString + " g";

        return result;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        DietPlanList dietPlanList = model.getDietPlanList();
        User user = model.getUser();
        try {
            DietPlan dietPlan = dietPlanList.getDietPlan(this.index);
            PlanInfoCalculator planInfoCalculator = new PlanInfoCalculator(user, dietPlan);
            String result = getResult(dietPlan, planInfoCalculator);
            return new CommandResult(result);
        } catch (IndexOutOfBoundsException outOfBounds) {
            throw new CommandException(MESSAGE_OUT_OF_BOUNDS);
        }

    }
}
