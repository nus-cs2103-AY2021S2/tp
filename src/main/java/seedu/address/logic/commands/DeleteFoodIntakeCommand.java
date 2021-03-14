package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.food.FoodIntake;
import seedu.address.model.food.FoodIntakeList;

/**
 * Deletes a food intake identified using the food name and date.
 */
public class DeleteFoodIntakeCommand extends Command {
    public static final String COMMAND_WORD = "foodintakedelete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": This deletes the food intake identified by its name and date.\n"
            + "Command usage: foodintakedelete d/ d MMM yyyy n/food name";

    public static final String MESSAGE_DELETE_FOOD_SUCCESS = "Successfully deleted food intake: ";

    public static final String MESSAGE_DELETE_FOOD_FAILURE = "Food intake could not be found. Please ensure its name "
            + "and date provided are correct.";

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

        ObservableList<FoodIntake> temporaryList = foodIntakeList.getFoodIntakeList();

        for (int i = 0; i < temporaryList.size(); i++) {
            if (temporaryList.get(i).getFood().getName().equals(this.foodName)
                    && temporaryList.get(i).getDate().isEqual(this.date)) {
                foodIntakeList.deleteFoodIntake(i);
                return new CommandResult(MESSAGE_DELETE_FOOD_SUCCESS + " " + this.foodName);
            }
        }
        throw new CommandException(MESSAGE_DELETE_FOOD_FAILURE);
    }
}
