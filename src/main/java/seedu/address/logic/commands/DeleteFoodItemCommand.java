package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.food.Food;

/**
 * Deletes a food item identified using it's displayed index from the UniqueFoodList.
 */
public class DeleteFoodItemCommand extends Command {
    public static final String COMMAND_WORD = "food_delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": This deletes the food item from the food list.\n"
            + "Command usage: food_delete n/FOOD_NAME";

    public static final String MESSAGE_DELETE_FOOD_SUCCESS = "Successfully deleted food item: ";

    public static final String MESSAGE_DELETE_FOOD_FAILURE = "Food item could not be found. Please ensure its name is"
            + " correct.";

    private final String foodName;

    public DeleteFoodItemCommand(String name) {
        this.foodName = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Food> foodList = model.getAddressBook().getFoodList().getFoodList();
        for (int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).getName().equals(this.foodName)) {
                model.deleteFoodItem(i);
                return new CommandResult(MESSAGE_DELETE_FOOD_SUCCESS + " " + this.foodName);
            }
        }
        throw new CommandException(MESSAGE_DELETE_FOOD_FAILURE);
    }
}
