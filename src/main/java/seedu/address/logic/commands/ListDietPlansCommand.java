package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;

import seedu.address.model.Model;
import seedu.address.model.diet.DietPlan;
import seedu.address.model.diet.DietPlanList;

/**
 * List all diet plans command
 */
public class ListDietPlansCommand extends Command {
    public static final String COMMAND_WORD = "plan_list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": This lists all available diet plans.";

    /**
     * Appends details of diet plans to the result
     *
     * @param result Result to be displayed to user
     * @param dietPlanList List of diet plans
     */
    private void printList(StringBuilder result, DietPlanList dietPlanList) {
        Iterator<DietPlan> dietPlanIterator = dietPlanList.iterator();

        // Iterate through all diet plans to get information
        int counter = 1;
        while (dietPlanIterator.hasNext()) {
            DietPlan dietPlan = dietPlanIterator.next();
            result.append(counter + ". " + dietPlan.getPlanName() + "\n");
            counter++;
        }

    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        DietPlanList dietPlanList = model.getDietPlanList();
        StringBuilder result = new StringBuilder("Here are the available diet plans:\n\n");
        printList(result, dietPlanList);

        String resultString = result.toString();
        return new CommandResult(resultString);
    }
}
