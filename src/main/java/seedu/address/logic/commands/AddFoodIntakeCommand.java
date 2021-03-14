package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.food.Food;

/**
 * Adds Food Intake to the nutrients tracker.
 */
public class AddFoodIntakeCommand extends Command {
    public static final String COMMAND_WORD = "foodintake";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": This adds the food intake for the day.\n"
            + "Command usage: foodintake d/ d MMM yyyy n/food name";

    public static final String MESSAGE_SUCCESS = "Success adding food item (";

    public static final String MESSAGE_FAILURE = "Suggested food item not found. Please add it into food list before "
            + "adding food intake.";

    private final String foodString;

    private final LocalDate date;

    /**
     * Creates a AddFoodIntake command to run the Macronutrients Tracker.
     */
    public AddFoodIntakeCommand(LocalDate date, String food) {
        this.foodString = food;
        this.date = date;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        int index = model.getUniqueFoodList().getFoodItemIndex(foodString);

        if (index == -1) {
            throw new CommandException(MESSAGE_FAILURE);
        }
        Food food = model.getUniqueFoodList().getFoodList().get(index);
        model.addFoodIntake(this.date, food);
        return new CommandResult(MESSAGE_SUCCESS + food + ") into food intake list.");
    }
}
