package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.model.diet.DietPlan;
import seedu.address.model.diet.DietPlanList;

import java.util.Iterator;

import static java.util.Objects.requireNonNull;

public class ListDietPlansCommand extends Command {
    public static final String COMMAND_WORD = "plan_list";

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
