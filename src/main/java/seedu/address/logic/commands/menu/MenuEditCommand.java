package seedu.address.logic.commands.menu;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.Pair;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.dish.Dish;
import seedu.address.model.ingredient.Ingredient;

public class MenuEditCommand extends Command {

    public static final String COMPONENT_WORD = "menu";
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMPONENT_WORD + " " + COMMAND_WORD
            + ": Edits the details of the dish "
            + "identified by the index number used in the displayed dish list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PRICE + "PRICE] "
            + "[" + PREFIX_INGREDIENT + "INGREDIENT_NUMBER] "
            + "[" + PREFIX_QUANTITY + "INGREDIENT_QUANTITY]...\n "
            + "Example: " + COMPONENT_WORD + " " + COMMAND_WORD + " 1 "
            + PREFIX_PRICE + "4.20";

    public static final String MESSAGE_EDIT_DISH_SUCCESS = "Edited Dish: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_DISH = "This dish already exists in the menu.";

    private final Index index;
    private final MenuEditCommand.EditDishDescriptor editDishDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editDishDescriptor details to edit the person with
     */
    public MenuEditCommand(Index index, MenuEditCommand.EditDishDescriptor editDishDescriptor) {
        requireNonNull(index);
        requireNonNull(editDishDescriptor);

        this.index = index;
        this.editDishDescriptor = new MenuEditCommand.EditDishDescriptor(editDishDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Dish> lastShownList = model.getFilteredDishList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, Messages.ITEM_DISH));
        }

        Dish dishToEdit = lastShownList.get(index.getZeroBased());
        Dish editedDish = createEditedDish(dishToEdit, editDishDescriptor, model);

        if (!dishToEdit.isSame(editedDish) && model.hasDish(editedDish)) {
            throw new CommandException(MESSAGE_DUPLICATE_DISH);
        }

        model.setDish(dishToEdit, editedDish);
        return new CommandResult(String.format(MESSAGE_EDIT_DISH_SUCCESS, editedDish),
                CommandResult.CRtype.PERSON);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Dish createEditedDish(Dish dishToEdit,
                                         MenuEditCommand.EditDishDescriptor editDishDescriptor,
                                         Model model) throws CommandException {
        assert dishToEdit != null;

        String updatedName = editDishDescriptor.getName().orElse(dishToEdit.getName());
        Double updatedPrice = editDishDescriptor.getPrice().orElse(dishToEdit.getPrice());

        Optional<List<Pair<Index, Integer>>> updatedIngredientIdsQuantityListOptional =
                editDishDescriptor.getIngredientIdsQuantityList();

        List<Pair<Ingredient, Integer>> updatedIngredientQuantityList;
        if (updatedIngredientIdsQuantityListOptional.isPresent()) {
            updatedIngredientQuantityList =
                     MenuCommandUtil.lookupIngredientIds(updatedIngredientIdsQuantityListOptional.get(), model);
        } else {
            updatedIngredientQuantityList = dishToEdit.getIngredientQuantityList();
        }

        assert updatedIngredientQuantityList != null;

        Dish editedDish = new Dish(updatedName, updatedPrice, updatedIngredientQuantityList);

        return editedDish;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MenuEditCommand)) {
            return false;
        }

        // state check
        MenuEditCommand e = (MenuEditCommand) other;
        return index.equals(e.index)
                && editDishDescriptor.equals(e.editDishDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditDishDescriptor {
        private String name;
        private Double price;
        private List<Pair<Index, Integer>> ingredientIdsQuantityList;

        public EditDishDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditDishDescriptor(MenuEditCommand.EditDishDescriptor toCopy) {
            setName(toCopy.name);
            setPrice(toCopy.price);
            setIngredientIdsQuantityList(toCopy.ingredientIdsQuantityList);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, price, ingredientIdsQuantityList);
        }

        public void setName(String name) {
            this.name = name;
        }

        public Optional<String> getName() {
            return Optional.ofNullable(name);
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Optional<Double> getPrice() {
            return Optional.ofNullable(price);
        }

        public void setIngredientIdsQuantityList(List<Pair<Index, Integer>> ingredientIdsQuantityList) {
            this.ingredientIdsQuantityList = ingredientIdsQuantityList;
        }

        public Optional<List<Pair<Index, Integer>>> getIngredientIdsQuantityList() {
            return Optional.ofNullable(ingredientIdsQuantityList);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof MenuEditCommand.EditDishDescriptor)) {
                return false;
            }

            // state check
            MenuEditCommand.EditDishDescriptor e = (MenuEditCommand.EditDishDescriptor) other;

            return getName().equals(e.getName())
                    && getPrice().equals(e.getPrice())
                    && getIngredientIdsQuantityList().equals(e.getIngredientIdsQuantityList());
        }
    }
}
