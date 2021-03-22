package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.food.Food;

/**
 * Macronutrients Tracker Food command
 */
public class AddFoodItemCommand extends Command {

    public static final String COMMAND_WORD = "food_add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": This adds a food item into the food list.\n"
            + "Command usage: food_add n/FOOD_NAME c/CARBOS f/FATS p/PROTEINS";

    public static final String MESSAGE_SUCCESS = "Success adding food item (";

    public static final String MESSAGE_DUPLICATE_FOOD = "This food item exists in the database. Please use (food_update"
            + " n/FOOD_NAME c/CARBOS f/FATS p/PROTEINS) command to update.\n"
            + "At least 1 nutrient value has to be specified and different from original.";

    private final Food temporaryFood;

    /**
     * Creates a Food command to run the Macronutrients Tracker.
     */
    public AddFoodItemCommand(Food food) {
        temporaryFood = food;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasFoodItem(temporaryFood)) {
            throw new CommandException(MESSAGE_DUPLICATE_FOOD);
        }

        model.addFoodItem(temporaryFood);
        return new CommandResult(MESSAGE_SUCCESS + temporaryFood + ") into food list.");
    }
}
