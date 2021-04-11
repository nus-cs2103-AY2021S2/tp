package seedu.address.logic.commands.inventory;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ingredient.Ingredient;

public class InventoryEditCommand extends Command {

    public static final String COMPONENT_WORD = "inventory";
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMPONENT_WORD + " " + COMMAND_WORD
            + ": Edits the details of the ingredient "
            + "identified by the index number used in the displayed ingredient list.\n"
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "INGREDIENT_NAME] "
            + "[" + PREFIX_QUANTITY + "QUANTITY]\n"
            + "Example: " + COMPONENT_WORD + " " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Sweetcorn";

    public static final String MESSAGE_EDIT_INGREDIENT_SUCCESS = "Edited Ingredient: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_INGREDIENT = "This ingredient already exists in the inventory.";

    private final Index index;
    private final InventoryEditCommand.EditIngredientDescriptor editIngredientDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editIngredientDescriptor details to edit the person with
     */
    public InventoryEditCommand(Index index, InventoryEditCommand.EditIngredientDescriptor editIngredientDescriptor) {
        requireNonNull(index);
        requireNonNull(editIngredientDescriptor);

        this.index = index;
        this.editIngredientDescriptor = new InventoryEditCommand.EditIngredientDescriptor(editIngredientDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Ingredient> lastShownList = model.getFilteredIngredientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, Messages.ITEM_INGREDIENT));
        }

        Ingredient ingredientToEdit = lastShownList.get(index.getZeroBased());
        Ingredient editedIngredient = createEditedIngredient(ingredientToEdit, editIngredientDescriptor);

        InventoryCommandUtil.isValidIngredient(editedIngredient, model);

        if (!ingredientToEdit.isSame(editedIngredient) && model.hasIngredient(editedIngredient)) {
            throw new CommandException(MESSAGE_DUPLICATE_INGREDIENT);
        }

        model.setIngredient(ingredientToEdit, editedIngredient);
        return new CommandResult(String.format(MESSAGE_EDIT_INGREDIENT_SUCCESS, editedIngredient),
                CommandResult.CRtype.PERSON);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Ingredient createEditedIngredient(Ingredient ingredientToEdit,
                                             InventoryEditCommand.EditIngredientDescriptor editIngredientDescriptor) {
        assert ingredientToEdit != null;

        String updatedName = editIngredientDescriptor.getName().orElse(ingredientToEdit.getName());
        Integer updatedQuantity = editIngredientDescriptor.getQuantity().orElse(ingredientToEdit.getQuantity());

        return new Ingredient(updatedName, updatedQuantity);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InventoryEditCommand)) {
            return false;
        }

        // state check
        InventoryEditCommand e = (InventoryEditCommand) other;
        return index.equals(e.index)
                && editIngredientDescriptor.equals(e.editIngredientDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditIngredientDescriptor {
        private String name;
        private Integer quantity;

        public EditIngredientDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditIngredientDescriptor(InventoryEditCommand.EditIngredientDescriptor toCopy) {
            setName(toCopy.name);
            setQuantity(toCopy.quantity);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, quantity);
        }

        public void setName(String name) {
            this.name = name;
        }

        public Optional<String> getName() {
            return Optional.ofNullable(name);
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public Optional<Integer> getQuantity() {
            return Optional.ofNullable(quantity);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof InventoryEditCommand.EditIngredientDescriptor)) {
                return false;
            }

            // state check
            InventoryEditCommand.EditIngredientDescriptor e = (InventoryEditCommand.EditIngredientDescriptor) other;

            return getName().equals(e.getName())
                    && getQuantity().equals(e.getQuantity());
        }
    }
}
