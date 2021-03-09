package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.food.Food;
import seedu.address.model.food.UniqueFoodList;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class UpdateFoodItemCommand extends Command {

    public static final String COMMAND_WORD = "food edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates the details of the certain food provided.";

    public static final String MESSAGE_EDIT_FOOD_SUCCESS = "Successfully updated food item";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_NOT_FOUND = "The food item is not found.";

    private final EditFoodDescriptor editedFood;

    /**
     * Creates an Update Food Item command to run the Macronutrients Tracker.
     * @param editedFood updated food data
     */
    public UpdateFoodItemCommand(EditFoodDescriptor editedFood) {
        requireNonNull(editedFood);

        this.editedFood = new EditFoodDescriptor(editedFood);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ArrayList<Food> foodList = model.getAddressBook().getFoodList().getFoodList();

        for(Food food : foodList) {
            if(food.getName().equals(editedFood.getName())) {
                Food updatedFood = createEditedFood(food, editedFood);
                model.updateFoodItem(updatedFood);
                return new CommandResult(MESSAGE_EDIT_FOOD_SUCCESS);
            }
        }
        throw new CommandException(MESSAGE_NOT_FOUND);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Food createEditedFood(Food foodToEdit, EditFoodDescriptor editFoodDescriptor) {
        assert foodToEdit != null;

        String updatedName = editFoodDescriptor.getName().orElse(foodToEdit.getName());
        Double updatedCarbos = editFoodDescriptor.getCarbos().orElse(foodToEdit.getCarbos());
        Double updatedFats = editFoodDescriptor.getFats().orElse(foodToEdit.getFats());
        Double updatedProteins = editFoodDescriptor.getProteins().orElse(foodToEdit.getProteins());

        return new Food(updatedName, updatedCarbos, updatedFats, updatedProteins);
    }

    public static class EditFoodDescriptor {
        private String name;
        private Double carbos;
        private Double fats;
        private Double proteins;

        public EditFoodDescriptor() {}

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
            this.name = name;
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
