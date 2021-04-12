package seedu.budgetbaby.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.budgetbaby.commons.core.Messages;
import seedu.budgetbaby.commons.core.index.Index;
import seedu.budgetbaby.commons.util.CollectionUtil;
import seedu.budgetbaby.logic.commands.exceptions.CommandException;
import seedu.budgetbaby.model.BudgetBabyModel;
import seedu.budgetbaby.model.record.Amount;
import seedu.budgetbaby.model.record.Category;
import seedu.budgetbaby.model.record.Description;
import seedu.budgetbaby.model.record.FinancialRecord;

/**
 * Edits the details of an existing financial record in the budget tracker.
 */
public class EditFrCommand extends BudgetBabyCommand {

    public static final String COMMAND_WORD = "edit-fr";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the financial record identified "
        + "by the index number used in the displayed financial record list. "
        + "Existing values will be overwritten by the input values.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + "[" + PREFIX_DESCRIPTION + "FR_DESCRIPTION] "
        + "[" + PREFIX_AMOUNT + "FR_AMOUNT] "
        + "[" + PREFIX_TIME + "DATE] "
        + "[" + PREFIX_CATEGORY + "CATEGORY]...\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_DESCRIPTION + "Lunch "
        + PREFIX_AMOUNT + "20.0";

    public static final String MESSAGE_EDIT_FR_SUCCESS = "Edited Financial Record: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final Index index;
    private final EditFrDescriptor editFrDescriptor;

    /**
     * @param index            of the financial record in the filtered financial record list to edit
     * @param editFrDescriptor details to edit the person with
     */
    public EditFrCommand(Index index, EditFrDescriptor editFrDescriptor) {
        requireNonNull(index);
        requireNonNull(editFrDescriptor);

        this.index = index;
        this.editFrDescriptor = new EditFrDescriptor(editFrDescriptor);
    }

    @Override
    public CommandResult execute(BudgetBabyModel model) throws CommandException {
        requireNonNull(model);
        List<FinancialRecord> lastShownList = model.getFilteredFinancialRecordList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FINANCIAL_RECORD_DISPLAYED_INDEX);
        }

        FinancialRecord frToEdit = lastShownList.get(index.getZeroBased());
        FinancialRecord editedFr = createEditedFr(frToEdit, editFrDescriptor);

        model.setFinancialRecord(frToEdit, editedFr);
        model.commitBudgetTracker();
        return new CommandResult(String.format(MESSAGE_EDIT_FR_SUCCESS, editedFr), false, false);
    }

    /**
     * Creates and returns a {@code FinancialRecord} with the details of {@code frToEdit}
     * edited with {@code editFrDescriptor}.
     */
    private static FinancialRecord createEditedFr(FinancialRecord frToEdit, EditFrDescriptor editFrDescriptor) {
        assert frToEdit != null;

        Description updatedDescription = editFrDescriptor.getDescription().orElse(frToEdit.getDescription());
        Amount updatedAmount = editFrDescriptor.getAmount().orElse(frToEdit.getAmount());
        Date updatedTimestamp = editFrDescriptor.getDate().orElse(frToEdit.getTimestamp());
        Set<Category> updatedCategories = editFrDescriptor.getCategories().orElse(frToEdit.getCategories());

        return new FinancialRecord(updatedDescription, updatedAmount, updatedTimestamp, updatedCategories);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditFrCommand)) {
            return false;
        }

        // state check
        EditFrCommand e = (EditFrCommand) other;
        return index.equals(e.index)
            && editFrDescriptor.equals(e.editFrDescriptor);
    }

    /**
     * Stores the details to edit the financial record with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditFrDescriptor {
        private Description description;
        private Amount amount;
        private Date timestamp;
        private Set<Category> categories;

        public EditFrDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code categories} is used internally.
         */
        public EditFrDescriptor(EditFrDescriptor toCopy) {
            setDescription(toCopy.description);
            setAmount(toCopy.amount);
            setDate(toCopy.timestamp);
            setCategories(toCopy.categories);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(description, amount, timestamp, categories);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setAmount(Amount amount) {
            this.amount = amount;
        }

        public Optional<Amount> getAmount() {
            return Optional.ofNullable(amount);
        }

        public void setDate(Date timestamp) {
            this.timestamp = timestamp;
        }

        public Optional<Date> getDate() {
            return Optional.ofNullable(timestamp);
        }

        /**
         * Sets {@code categories} to this object's {@code categories}.
         * A defensive copy of {@code categories} is used internally.
         */
        public void setCategories(Set<Category> categories) {
            this.categories = (categories != null) ? new HashSet<>(categories) : null;
        }

        /**
         * Returns an unmodifiable category set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code categories} is null.
         */
        public Optional<Set<Category>> getCategories() {
            return (categories != null) ? Optional.of(Collections.unmodifiableSet(categories)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditFrDescriptor)) {
                return false;
            }

            // state check
            EditFrDescriptor e = (EditFrDescriptor) other;

            return getDescription().equals(e.getDescription())
                && getAmount().equals(e.getAmount())
                && getCategories().equals(e.getCategories());
        }
    }
}
