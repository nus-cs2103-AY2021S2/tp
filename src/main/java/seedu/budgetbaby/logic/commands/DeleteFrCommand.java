package seedu.budgetbaby.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.budgetbaby.commons.core.Messages;
import seedu.budgetbaby.commons.core.index.Index;
import seedu.budgetbaby.logic.commands.exceptions.CommandException;
import seedu.budgetbaby.model.BudgetBabyModel;
import seedu.budgetbaby.model.record.FinancialRecord;

/**
 * Deletes a financial record using it's displayed index from the budget tracker.
 */
public class DeleteFrCommand extends BudgetBabyCommand {

    public static final String COMMAND_WORD = "delete-fr";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the financial record identified by the index number used in the displayed list.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_FINANCIAL_RECORD_SUCCESS = "Deleted Financial Record: %1$s";

    private final Index targetIndex;

    public DeleteFrCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(BudgetBabyModel model) throws CommandException {
        requireNonNull(model);
        List<FinancialRecord> lastShownList = model.getFilteredFinancialRecordList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FINANCIAL_RECORD_DISPLAYED_INDEX);
        }

        FinancialRecord toDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteFinancialRecord(toDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_FINANCIAL_RECORD_SUCCESS, toDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteFrCommand // instanceof handles nulls
            && targetIndex.equals(((DeleteFrCommand) other).targetIndex)); // state check
    }
}
