package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.Optional;

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

    public static final String MESSAGE_FAILURE_CREATE_FOOD_REQ = "Suggested food item not found. Please append at "
            + "least 1 nutrient value to add the food item. (Eg. c/CARBOS(g) or f/FATS(g) or p/PROTEINS(g) ";

    private final LocalDate date;

    private final TemporaryFoodDescriptor tempFoodDescriptor;

    /**
     * Creates a AddFoodIntake command to run the Macronutrients Tracker.
     */
    public AddFoodIntakeCommand(LocalDate date, TemporaryFoodDescriptor tempFoodDescriptor) {
        this.date = date;
        this.tempFoodDescriptor = tempFoodDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        int index = model.getUniqueFoodList().getFoodItemIndex(this.tempFoodDescriptor.getName().get());
        boolean skipEditCheck = false;

        //Food does not exist in Unique Food List, hence add the food item in.
        if (index == -1) {
            Food newFood = createNewFood(this.tempFoodDescriptor);
            model.addFoodItem(newFood);
            index = model.getUniqueFoodList().getFoodList().size() - 1;
            skipEditCheck = true;
        }

        Food food = model.getUniqueFoodList().getFoodList().get(index);
        //Check for any edits made to the certain food item.
        if (!skipEditCheck && (this.tempFoodDescriptor.getCarbos().isPresent()
                || this.tempFoodDescriptor.getFats().isPresent()
                || this.tempFoodDescriptor.getProteins().isPresent())) {
            Food edittedFood = editCurrentFood(food, tempFoodDescriptor);
            model.getUniqueFoodList().getFoodList().set(index, edittedFood);
            model.addFoodIntake(this.date, edittedFood);
            return new CommandResult(MESSAGE_SUCCESS + edittedFood + ") into food intake list.");
        }

        model.addFoodIntake(this.date, food);
        return new CommandResult(MESSAGE_SUCCESS + food + ") into food intake list.");
    }

    /**
     * Creates a new food item to add into unique food list and food intake.
     *
     * @param tempFoodDescriptor temporary Food item holder
     * @return new food item
     * @throws CommandException if no nutrient value is specified.
     */
    public Food createNewFood(TemporaryFoodDescriptor tempFoodDescriptor) throws CommandException {
        String foodName = tempFoodDescriptor.getName().get();
        //Default initialize all double values to 0.
        Double newCarbos = 0.0;
        Double newFats = 0.0;
        Double newProteins = 0.0;
        if (!tempFoodDescriptor.getCarbos().isPresent() && !tempFoodDescriptor.getFats().isPresent()
                && !tempFoodDescriptor.getProteins().isPresent()) {
            throw new CommandException(MESSAGE_FAILURE_CREATE_FOOD_REQ);
        }
        if (tempFoodDescriptor.getCarbos().isPresent()) {
            newCarbos = tempFoodDescriptor.getCarbos().get();
        }
        if (this.tempFoodDescriptor.getFats().isPresent()) {
            newFats = tempFoodDescriptor.getFats().get();
        }
        if (this.tempFoodDescriptor.getProteins().isPresent()) {
            newProteins = tempFoodDescriptor.getProteins().get();
        }
        return new Food(foodName, newCarbos, newFats, newProteins);
    }

    /**
     * Creates a new edited food item with its details edited.
     *
     * @param existingFood       existing food item
     * @param tempFoodDescriptor temporary food item holder
     * @return new edited food item
     */
    public Food editCurrentFood(Food existingFood, TemporaryFoodDescriptor tempFoodDescriptor) {
        String foodName = this.tempFoodDescriptor.getName().get();
        //Default initialize all double values to 0.
        Double newCarbos = 0.0;
        Double newFats = 0.0;
        Double newProteins = 0.0;
        if (tempFoodDescriptor.getCarbos().isPresent()
                && existingFood.getCarbos() != tempFoodDescriptor.getCarbos().get()) {
            newCarbos = tempFoodDescriptor.getCarbos().get();
        } else {
            newCarbos = existingFood.getCarbos();
        }
        if (tempFoodDescriptor.getFats().isPresent() && existingFood.getFats() != tempFoodDescriptor.getFats().get()) {
            newFats = tempFoodDescriptor.getFats().get();
        } else {
            newFats = existingFood.getFats();
        }
        if (tempFoodDescriptor.getProteins().isPresent()
                && existingFood.getProteins() != tempFoodDescriptor.getProteins().get()) {
            newProteins = tempFoodDescriptor.getProteins().get();
        } else {
            newProteins = existingFood.getProteins();
        }
        return new Food(foodName, newCarbos, newFats, newProteins);
    }

    public static class TemporaryFoodDescriptor {
        private String name;
        private Double carbos;
        private Double fats;
        private Double proteins;

        public TemporaryFoodDescriptor() {
        }

        public void setName(String name) {
            this.name = name.toLowerCase();
        }

        public Optional<String> getName() {
            return Optional.ofNullable(name);
        }

        public void setCarbos(Double carbos) {
            this.carbos = carbos;
        }

        public Optional<Double> getCarbos() {
            return Optional.ofNullable(carbos);
        }

        public void setFats(Double fats) {
            this.fats = fats;
        }

        public Optional<Double> getFats() {
            return Optional.ofNullable(fats);
        }

        public void setProteins(Double proteins) {
            this.proteins = proteins;
        }

        public Optional<Double> getProteins() {
            return Optional.ofNullable(proteins);
        }
    }
}
