package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

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

    private void appendDiets(StringBuilder recommendations, List<DietPlan> recommendedDiets) {
        for (int count = 0; count < recommendedDiets.size(); count++) {
            int index = count + 1;
            DietPlan dietPlan = recommendedDiets.get(count);
            recommendations.append(index + ". " + dietPlan.getPlanName() + "\n");
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        PlanType userGoal = model.getUserGoal();
        List<DietPlan> recommendedDiets = model.recommendDiets(userGoal);

        StringBuilder recommendations = new StringBuilder("Here are the recommended ");
        switch (userGoal) {
        case WEIGHTGAIN:
            recommendations.append("weight gain ");
            break;
        case WEIGHTLOSS:
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
