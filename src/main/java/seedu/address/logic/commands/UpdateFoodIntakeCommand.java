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
            + "Command usage: food_intake_update d/DATE(in d MMM yyyy format) n/FOOD_NAME c/CARBOS f/FATS p/PROTEINS";

    public static final String MESSAGE_SUCCESS = "Food intake successfully updated for ";

    public static final String MESSAGE_FAILURE = "Unable to find food intake record. Are you trying "
            + "to add a new intake?";

    private String name;
    private LocalDate date;
    private String fats;
    private String carbos;
    private String proteins;

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

        this.date = currentFoodIntake.getDate();
        this.name = currentFoodIntake.getFood().getName();

        if (this.fats == null) {
            this.fats = String.valueOf(currentFoodIntake.getFood().getFats());
        }

        if (this.carbos == null) {
            this.carbos = String.valueOf(currentFoodIntake.getFood().getCarbos());
        }

        if (this.proteins == null) {
            this.proteins = String.valueOf(currentFoodIntake.getFood().getProteins());
        }

        newFoodIntake = new FoodIntake(this.date, this.name,
                Double.parseDouble(this.carbos), Double.parseDouble(this.fats),
                Double.parseDouble(this.proteins));
        model.updateFoodIntake(index, newFoodIntake);
        return new CommandResult(MESSAGE_SUCCESS + "(" + this.name + ")");
    }
}
