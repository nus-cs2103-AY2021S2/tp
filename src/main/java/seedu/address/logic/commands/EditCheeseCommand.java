package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHEESE_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MANUFACTURE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATURITY_DATE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CHEESES;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.cheese.Cheese;
import seedu.address.model.cheese.CheeseId;
import seedu.address.model.cheese.CheeseType;
import seedu.address.model.cheese.ExpiryDate;
import seedu.address.model.cheese.ManufactureDate;
import seedu.address.model.cheese.MaturityDate;

/**
 * Edits the details of an existing cheese in the address book.
 */
public class EditCheeseCommand extends EditCommand {

    public static final String COMMAND_WORD = "editcheese";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the cheese identified "
            + "by the index number used in the displayed cheese list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_CHEESE_TYPE + "CHEESE TYPE] "
            + "[" + PREFIX_MANUFACTURE_DATE + "MANUFACTURE_DATE] "
            + "[" + PREFIX_MATURITY_DATE + "MATURITY_DATE] "
            + "[" + PREFIX_EXPIRY_DATE + "EXPIRY_DATE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_CHEESE_TYPE + "Parmesan "
            + PREFIX_MANUFACTURE_DATE + "2020-12-30 "
            + PREFIX_MATURITY_DATE + "2021-01-20 "
            + PREFIX_EXPIRY_DATE + "2021-02-30";

    public static final String MESSAGE_EDIT_CHEESE_SUCCESS = "Edited Cheese: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    protected final Index index;
    protected final EditCheeseDescriptor editCheeseDescriptor;

    /**
     * Creates an AddOrderCommand to add the specified {@code Order}
     */
    public EditCheeseCommand(Index index, EditCheeseDescriptor editCustomerDescriptor) {
        requireNonNull(index);
        requireNonNull(editCustomerDescriptor);

        this.index = index;
        this.editCheeseDescriptor = new EditCheeseDescriptor(editCustomerDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Cheese> lastShownList = model.getFilteredCheeseList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CHEESE_DISPLAYED_INDEX);
        }

        Cheese cheeseToEdit = lastShownList.get(index.getZeroBased());
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
                                                 EditCheeseDescriptor editCheeseDescriptor) {
        assert cheeseToEdit != null;

        CheeseType updatedCheeseType =
            editCheeseDescriptor.getCheeseType().orElse(cheeseToEdit.getCheeseType());
        ManufactureDate updatedManufactureDate =
            editCheeseDescriptor.getManufactureDate().orElse(cheeseToEdit.getManufactureDate());
        MaturityDate updatedMaturityDate =
            editCheeseDescriptor.getMaturityDate().orElse(cheeseToEdit.getMaturityDate().orElse(null));
        ExpiryDate updatedExpiryDate =
            editCheeseDescriptor.getExpiryDate().orElse(cheeseToEdit.getExpiryDate().orElse(null));
        CheeseId id = cheeseToEdit.getCheeseId();
        boolean isAssigned = cheeseToEdit.isCheeseAssigned();

        return new Cheese(updatedCheeseType, updatedManufactureDate, 
                updatedMaturityDate, updatedExpiryDate, id, isAssigned);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCheeseCommand // instanceof handles nulls
                && index.equals(((EditCheeseCommand) other).index)
                && editCheeseDescriptor.equals(((EditCheeseCommand) other).editCheeseDescriptor));
    }

    /**
     * Stores the details to edit the customer with. Each non-empty field value will replace the
     * corresponding field value of the customer.
     */
    public static class EditCheeseDescriptor {
        private CheeseType cheeseType;
        private ManufactureDate manufactureDate;
        private MaturityDate maturityDate;
        private ExpiryDate expiryDate;


        public EditCheeseDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditCheeseDescriptor(EditCheeseDescriptor toCopy) {
            setCheeseType(toCopy.cheeseType);
            setManufactureDate(toCopy.manufactureDate);
            setMaturityDate(toCopy.maturityDate);
            setExpiryDate(toCopy.expiryDate);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(cheeseType, manufactureDate, maturityDate, expiryDate);
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

        public void setMaturityDate(MaturityDate maturityDate) {
            this.maturityDate = maturityDate;
        }

        public Optional<MaturityDate> getMaturityDate() {
            return Optional.ofNullable(maturityDate);
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
                    && getMaturityDate().equals(e.getMaturityDate())
                    && getExpiryDate().equals(e.getExpiryDate());
        }
    }
}
