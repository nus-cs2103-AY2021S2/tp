package fooddiary.logic.commands;

import static java.util.Objects.requireNonNull;

import fooddiary.model.FoodDiary;
import fooddiary.model.Model;

/**
 * Clears the food diary.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Food Diary has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setFoodDiary(new FoodDiary());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
