package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.food.FoodIntake;

/**
 * Updates a particular FoodIntake item in the FoodIntakeList.
 */
public class UpdateFoodIntakeCommand extends Command {
    public static final String COMMAND_WORD = "food_intake_update";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": This updates the nutritional values for a particular date and food.\n"
            + "Command usage: food_intake_update d/DATE(in dd Mmm yyyy format) n/FOOD_NAME c/CARBOS f/FATS p/PROTEINS";

    public static final String MESSAGE_SUCCESS = "Food intake successfully updated for:";

    public static final String MESSAGE_FAILURE = "Unable to find food intake record. Are you trying "
            + "to add a new intake?";

    public static final String MESSAGE_NUTRIENTS_REQUIRED = "Please specify at least 1 nutrient value to be updated.";
    public static final int EDITABLE_NUTRIENT_COUNT = 3;

    private String name;
    private LocalDate date;
    private String fats;
    private String carbos;
    private String proteins;
    private int nutrientsEdited = 0;

    /**
     * Creates an UpdateFoodIntake command to update existing foodIntakes.
     */
    public UpdateFoodIntakeCommand(LocalDate date, String name, String fats, String carbos, String proteins) {
        this.name = name;
        this.date = date;
        this.fats = fats;
        this.carbos = carbos;
        this.proteins = proteins;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        FoodIntake currentFoodIntake;
        FoodIntake newFoodIntake;
        int index;
        requireNonNull(model);

        try {
            index = model.getFoodIntakeList().findFoodIntake(date, name);
            if (index == -1) {
                throw new CommandException(MESSAGE_FAILURE);
            }
            currentFoodIntake = model.getFoodIntakeList().getFoodIntakeList().get(index);
        } catch (IndexOutOfBoundsException error) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        // Retain original values as date and name are not editable.
        this.date = currentFoodIntake.getDate();
        this.name = currentFoodIntake.getFood().getName();

        // Replace nutrient values, if they were provided
        this.fats = replaceUpdatedValue(currentFoodIntake.getFood().getFats(), this.fats);
        this.carbos = replaceUpdatedValue(currentFoodIntake.getFood().getCarbos(), this.carbos);
        this.proteins = replaceUpdatedValue(currentFoodIntake.getFood().getProteins(), this.proteins);

        if (nutrientsEdited == 0) {
            throw new CommandException(MESSAGE_NUTRIENTS_REQUIRED);
        }

        newFoodIntake = new FoodIntake(this.date, this.name,
                Double.parseDouble(this.carbos), Double.parseDouble(this.fats),
                Double.parseDouble(this.proteins));
        model.updateFoodIntake(index, newFoodIntake);

        String updatedFoodIntakeList = model.getFoodIntakeList().getFoodIntakeListByDate(this.date);
        return new CommandResult(MESSAGE_SUCCESS + " " + this.name + "\n\n" + updatedFoodIntakeList);
    }

    /**
     * Returns current existing value if no updated value provided.
     */
    public String replaceUpdatedValue(Double current, String updated) {
        if (updated == null) {
            return String.valueOf(current);
        }
        nutrientsEdited++;
        return updated;
    }
}
