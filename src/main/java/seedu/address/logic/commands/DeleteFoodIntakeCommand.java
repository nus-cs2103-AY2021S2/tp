package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.food.FoodIntakeList;
import seedu.address.model.food.exceptions.FoodIntakeNotFoundException;

/**
 * Deletes a food intake identified using the food name and date.
 */
public class DeleteFoodIntakeCommand extends Command {
    public static final String COMMAND_WORD = "food_intake_delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": This deletes the food intake item identified by its name on that date.\n"
            + "Command usage: food_intake_delete d/DATE(in d MMM yyyy format) n/FOOD_NAME";

    public static final String MESSAGE_DELETE_FOODINTAKE_SUCCESS = "Successfully deleted food intake: ";

    public static final String MESSAGE_DELETE_FOODINTAKE_FAILURE = "Food intake could not be found. "
            + "Please verify that the name and date provided is correct.";

    private final String foodName;

    private final LocalDate date;

    /**
     * Initializes a DeleteFoodIntakeCommand instance with the date and food name input.
     *
     * @param date date of food intake
     * @param foodName name of food
     */
    public DeleteFoodIntakeCommand(LocalDate date, String foodName) {
        this.date = date;
        this.foodName = foodName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        FoodIntakeList foodIntakeList = model.getFoodIntakeList();

        try {
            foodIntakeList.deleteFoodIntake(this.date, this.foodName);
            String updateFoodIntakeList = model.getFoodIntakeList().getFoodIntakeListByDate(this.date);
            return new CommandResult(MESSAGE_DELETE_FOODINTAKE_SUCCESS + " "
                    + this.foodName + "\n\n" + updateFoodIntakeList);
        } catch (FoodIntakeNotFoundException exception) {
            throw new CommandException(MESSAGE_DELETE_FOODINTAKE_FAILURE);
        }
    }
}
