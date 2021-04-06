package seedu.budgetbaby.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.budgetbaby.ablogic.commands.DeleteCommand;
import seedu.budgetbaby.abmodel.Model;
import seedu.budgetbaby.commons.core.Messages;
import seedu.budgetbaby.commons.core.index.Index;
import seedu.budgetbaby.model.BudgetBabyModel;
import seedu.budgetbaby.model.BudgetBabyModelManager;
import seedu.budgetbaby.model.UserPrefs;
import seedu.budgetbaby.model.record.FinancialRecord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.budgetbaby.logic.commands.CommandTestUtil.*;
import static seedu.budgetbaby.testutil.TypicalBudgetTracker.getTypicalBudgetTracker;
import static seedu.budgetbaby.testutil.TypicalIndexes.*;

public class DeleteCommandTest {
    private BudgetBabyModel model = new BudgetBabyModelManager(getTypicalBudgetTracker(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        FinancialRecord frToDelete = model.getFilteredFinancialRecordList().get(0);
        DeleteFrCommand deleteCommand = new DeleteFrCommand(INDEX_FIRST_FR);

        String expectedMessage = String.format(DeleteFrCommand.MESSAGE_DELETE_FINANCIAL_RECORD_SUCCESS, frToDelete);

        BudgetBabyModelManager expectedModel = new BudgetBabyModelManager(model.getBudgetTracker(), new UserPrefs());
        expectedModel.deleteFinancialRecord(frToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        List<Index> outOfBoundIndex = new ArrayList<>(model.getFilteredFinancialRecordList().size() + 1);
        DeleteFrCommand deleteCommand = new DeleteFrCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showFrAtIndex(model, INDEX_FIRST_PERSON);

        FinancialRecord frToDelete = model.getFilteredFinancialRecordList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteFrCommand deleteCommand = new DeleteFrCommand(INDEX_FIRST_FR);

        String expectedMessage = String.format(DeleteFrCommand.MESSAGE_DELETE_FINANCIAL_RECORD_SUCCESS, frToDelete);

        BudgetBabyModelManager expectedModel = new BudgetBabyModelManager(model.getBudgetTracker(), new UserPrefs());
        expectedModel.deleteFinancialRecord(frToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showFrAtIndex(model, INDEX_FIRST_PERSON);

        List<Index> outOfBoundIndex = INDEX_FIRST_FR;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.get(0).getZeroBased() < model.getBudgetTracker().getMonthList().size());

        DeleteFrCommand deleteCommand = new DeleteFrCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_PERSON);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no records.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);
        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
