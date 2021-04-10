package chim.logic.commands;

import static chim.logic.parser.CliSyntax.PREFIX_CHEESE_TYPE;
import static chim.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;
import static chim.logic.parser.CliSyntax.PREFIX_MANUFACTURE_DATE;
import static chim.model.Model.PREDICATE_SHOW_ALL_CHEESES;
import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import chim.commons.core.Messages;
import chim.commons.core.index.Index;
import chim.commons.util.CollectionUtil;
import chim.logic.commands.exceptions.CommandException;
import chim.model.Model;
import chim.model.cheese.Cheese;
import chim.model.cheese.CheeseId;
import chim.model.cheese.CheeseType;
import chim.model.cheese.ExpiryDate;
import chim.model.cheese.ManufactureDate;

/**
 * Edits the details of an existing cheese in CHIM.
 */
public class EditCheeseCommand extends EditCommand {

    public static final String COMMAND_WORD = "editcheese";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the cheese identified "
            + "by the index number used in the displayed cheese list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_CHEESE_TYPE + "CHEESE TYPE] "
            + "[" + PREFIX_MANUFACTURE_DATE + "MANUFACTURE_DATE] "
            + "[" + PREFIX_EXPIRY_DATE + "EXPIRY_DATE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_CHEESE_TYPE + "Parmesan "
            + PREFIX_MANUFACTURE_DATE + "2020-12-30 "
            + PREFIX_EXPIRY_DATE + "2021-02-30";

    public static final String MESSAGE_EDIT_CHEESE_SUCCESS = "Edited Cheese: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_CHEESE_IS_ASSIGNED = "Not allowed to change the fields of an assigned cheese.";

    protected final Index index;
    protected final EditCheeseDescriptor editCheeseDescriptor;

    /**
     * Creates an EditCheeseCommand to edit the specified cheese at {@code index}
     */
    public EditCheeseCommand(Index index, EditCheeseDescriptor editCheeseDescriptor) {
        requireNonNull(index);
        requireNonNull(editCheeseDescriptor);

        this.index = index;
        this.editCheeseDescriptor = new EditCheeseDescriptor(editCheeseDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Cheese> lastShownList = model.getFilteredCheeseList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CHEESE_DISPLAYED_INDEX);
        }

        Cheese cheeseToEdit = lastShownList.get(index.getZeroBased());
        if (cheeseToEdit.isCheeseAssigned()) {
            throw new CommandException(MESSAGE_CHEESE_IS_ASSIGNED);
        }
        Cheese editedCheese = createEditedCheese(cheeseToEdit, editCheeseDescriptor);

        model.setCheese(cheeseToEdit, editedCheese);
        model.updateFilteredCheeseList(PREDICATE_SHOW_ALL_CHEESES);
        model.setPanelToCheeseList();

        return new CommandResult(String.format(MESSAGE_EDIT_CHEESE_SUCCESS, editedCheese));
    }

    /**
     * Creates and returns a {@code Cheese} with the details of {@code cheeseToEdit}
     * edited with {@code editCheeseDescriptor}.
     */
    private static Cheese createEditedCheese(Cheese cheeseToEdit,
                                                 EditCheeseDescriptor editCheeseDescriptor) throws CommandException {
        assert cheeseToEdit != null;

        CheeseType updatedCheeseType =
            editCheeseDescriptor.getCheeseType().orElse(cheeseToEdit.getCheeseType());
        ManufactureDate updatedManufactureDate =
            editCheeseDescriptor.getManufactureDate().orElse(cheeseToEdit.getManufactureDate());
        ExpiryDate updatedExpiryDate =
            editCheeseDescriptor.getExpiryDate().orElse(cheeseToEdit.getExpiryDate().orElse(null));
        CheeseId id = cheeseToEdit.getCheeseId();
        boolean isAssigned = cheeseToEdit.isCheeseAssigned();

        Cheese retCheese;
        try {
            retCheese = new Cheese(updatedCheeseType, updatedManufactureDate, updatedExpiryDate, id, isAssigned);
        } catch (IllegalArgumentException e) {
            throw new CommandException(e.getMessage());
        }

        return retCheese;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditCheeseCommand // instanceof handles nulls
                && index.equals(((EditCheeseCommand) other).index)
                && editCheeseDescriptor.equals(((EditCheeseCommand) other).editCheeseDescriptor));
    }

    /**
     * Stores the details to edit the cheese with. Each non-empty field value will replace the
     * corresponding field value of the cheese.
     */
    public static class EditCheeseDescriptor {
        private CheeseType cheeseType;
        private ManufactureDate manufactureDate;
        private ExpiryDate expiryDate;

        public EditCheeseDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditCheeseDescriptor(EditCheeseDescriptor toCopy) {
            setCheeseType(toCopy.cheeseType);
            setManufactureDate(toCopy.manufactureDate);
            setExpiryDate(toCopy.expiryDate);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(cheeseType, manufactureDate, expiryDate);
        }

        public void setCheeseType(CheeseType cheeseType) {
            this.cheeseType = cheeseType;
        }

        public Optional<CheeseType> getCheeseType() {
            return Optional.ofNullable(cheeseType);
        }

        public void setManufactureDate(ManufactureDate manufactureDate) {
            this.manufactureDate = manufactureDate;
        }

        public Optional<ManufactureDate> getManufactureDate() {
            return Optional.ofNullable(manufactureDate);
        }

        public void setExpiryDate(ExpiryDate expiryDate) {
            this.expiryDate = expiryDate;
        }

        public Optional<ExpiryDate> getExpiryDate() {
            return Optional.ofNullable(expiryDate);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditCheeseDescriptor)) {
                return false;
            }

            // state check
            EditCheeseDescriptor e = (EditCheeseDescriptor) other;

            return getCheeseType().equals(e.getCheeseType())
                    && getManufactureDate().equals(e.getManufactureDate())
                    && getExpiryDate().equals(e.getExpiryDate());
        }
    }
}
