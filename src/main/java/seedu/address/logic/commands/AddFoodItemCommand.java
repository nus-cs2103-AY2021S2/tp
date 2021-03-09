package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.food.Food;

/**
 * Macronutrients Tracker Food command
 */
public class AddFoodItemCommand extends Command {

    public static final String COMMAND_WORD = "food";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Opens up the macronutrients tracker."
            + "\nThis tracker has 2 function:"
            + "\n1. Input food item with its carbo, fats and protein value for the day.";

    public static final String MESSAGE_SUCCESS = "Success adding food item (";

    public static final String MESSAGE_DUPLICATE_FOOD = "This food item exists in the database. Do you want to"
            + " modify its details?";

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
        return new CommandResult(MESSAGE_SUCCESS + temporaryFood.getName() + ") into food list.");
    }
}
