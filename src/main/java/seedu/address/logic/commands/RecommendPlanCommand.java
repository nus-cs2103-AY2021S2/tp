package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.diet.DietPlan;
import seedu.address.model.diet.PlanType;

/**
 * Recommend plan command
 */
public class RecommendPlanCommand extends Command {
    public static final String COMMAND_WORD = "plan_recommend";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Recommend diet plans based on ideal weight.\n";

    /**
     * Creates a recommend plan command to recommend diet plans to the user
     */
    public RecommendPlanCommand() {

    }

    private void appendDiets(StringBuilder recommendations, HashMap<Integer, DietPlan> recommendedDiets) {
        for (Integer index : recommendedDiets.keySet()) {
            DietPlan dietPlan = recommendedDiets.get(index);
            recommendations.append(index + ". " + dietPlan.getPlanName() + "\n");
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        PlanType userGoal = model.getUserGoal();
        HashMap<Integer, DietPlan> recommendedDiets = model.recommendDiets(userGoal);

        StringBuilder recommendations = new StringBuilder("Here are the recommended ");
        switch (userGoal) {
        case WEIGHT_GAIN:
            recommendations.append("weight gain ");
            break;
        case WEIGHT_LOSS:
            recommendations.append("weight loss ");
            break;
        default:
            recommendations.append("weight maintenance ");
            break;
        }

        double bmi = model.getUserBmi();
        String bmiString = String.format("%.2f", bmi);
        recommendations.append("diet plans based on your BMI of " + bmiString + ":\n\n");
        appendDiets(recommendations, recommendedDiets);

        String recommendationString = recommendations.toString();
        return new CommandResult(recommendationString);

    }
}
