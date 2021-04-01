package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.food.Food;

public class UpdateFoodItemCommand extends Command {

    public static final String COMMAND_WORD = "food_update";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": This updates the detail(s) of existing food item "
            + "specified.\n"
            + "Existing values will be overwritten by the input values and at least 1 value has to be different from"
            + " original.\n"
            + "Command usage: food_update n/FOOD_NAME c/CARBOS f/FATS p/PROTEINS";

    public static final String MESSAGE_EDIT_FOOD_SUCCESS = "Successfully updated food item";
    public static final String MESSAGE_NOT_EDITED = "At least one field such as its carbos, fats or proteins "
            + "value must be edited and different from original. (Current Food being edited: ";
    public static final String MESSAGE_NOT_FOUND = "The food item specified is not found. Please try again with a valid"
            + " item.";

    public static final String MESSAGE_NAME_MISSING = "Food name is missing! Please enter the food name. Here is "
            + "the command guide: \n"
            + MESSAGE_USAGE;

    private final EditFoodDescriptor editedFood;

    /**
     * Creates an Update Food Item command to run the Macronutrients Tracker.
     *
     * @param editedFood updated food data
     */
    public UpdateFoodItemCommand(EditFoodDescriptor editedFood) {
        requireNonNull(editedFood);

        this.editedFood = new EditFoodDescriptor(editedFood);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Food> foodList = model.getAddressBook().getFoodList().getFoodList();

        for (Food food : foodList) {
            if (food.getName().equals(editedFood.getName().get())) {
                Food updatedFood = createEditedFood(food, editedFood);
                model.updateFoodItem(updatedFood);
                return new CommandResult(MESSAGE_EDIT_FOOD_SUCCESS);
            }
        }
        throw new CommandException(MESSAGE_NOT_FOUND);
    }

    /**
     * Creates and returns a {@code Food} with the details of {@code foodToEdit}
     * edited with {@code editFoodDescriptor}.
     */
    private static Food createEditedFood(Food foodToEdit, EditFoodDescriptor editFoodDescriptor) throws
            CommandException {
        assert foodToEdit != null;

        String foodName = editFoodDescriptor.getName().orElse(foodToEdit.getName());
        Double updatedCarbos = editFoodDescriptor.getCarbos().orElse(foodToEdit.getCarbos());
        Double updatedFats = editFoodDescriptor.getFats().orElse(foodToEdit.getFats());
        Double updatedProteins = editFoodDescriptor.getProteins().orElse(foodToEdit.getProteins());

        if (updatedCarbos == foodToEdit.getCarbos() && updatedFats == foodToEdit.getFats()
                && updatedProteins == foodToEdit.getProteins()) {
            throw new CommandException(MESSAGE_NOT_EDITED + foodName + ")");
        }

        return new Food(foodName, updatedCarbos, updatedFats, updatedProteins);
    }

    public static class EditFoodDescriptor {
        private String name;
        private Double carbos;
        private Double fats;
        private Double proteins;

        public EditFoodDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public EditFoodDescriptor(EditFoodDescriptor toCopy) {
            setName(toCopy.name);
            setCarbos(toCopy.carbos);
            setFats(toCopy.fats);
            setProteins(toCopy.proteins);
        }

        /**
         * Returns true if at least one field is edited
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(carbos, fats, proteins);
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
