package seedu.budgetbaby.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.budgetbaby.commons.core.Messages.MESSAGE_TYPICAL_BUDGET_TRACKER_DELETE_LUNCH;
import static seedu.budgetbaby.commons.core.Messages.MESSAGE_TYPICAL_BUDGET_TRACKER_EMPTY;
import static seedu.budgetbaby.logic.commands.CommandTestUtil.showFrAtIndex;
import static seedu.budgetbaby.testutil.TypicalBudgetTracker.getTypicalBudgetTracker;
import static seedu.budgetbaby.testutil.TypicalIndexes.INDEX_FIFTH_FR;
import static seedu.budgetbaby.testutil.TypicalIndexes.INDEX_FIRST_FR;
import static seedu.budgetbaby.testutil.TypicalIndexes.INDEX_FOURTH_FR;
import static seedu.budgetbaby.testutil.TypicalIndexes.INDEX_SECOND_FR;
import static seedu.budgetbaby.testutil.TypicalIndexes.INDEX_THIRD_FR;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.budgetbaby.logic.commands.exceptions.CommandException;
import seedu.budgetbaby.model.BudgetBabyModel;
import seedu.budgetbaby.model.BudgetBabyModelManager;
import seedu.budgetbaby.model.UserPrefs;
import seedu.budgetbaby.model.record.FinancialRecord;

public class DeleteFrCommandTest {
    private BudgetBabyModel model = new BudgetBabyModelManager(getTypicalBudgetTracker(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() throws CommandException {
        FinancialRecord frToDelete = model.getFilteredFinancialRecordList().get(1);
        DeleteFrCommand deleteCommand = new DeleteFrCommand(new ArrayList<>(Arrays.asList(INDEX_SECOND_FR)));

        String expectedMessage = String.format(DeleteFrCommand.MESSAGE_DELETE_FINANCIAL_RECORD_SUCCESS, frToDelete);

        BudgetBabyModelManager expectedModel = new BudgetBabyModelManager(model.getBudgetTracker(), new UserPrefs());
        deleteCommand.execute(expectedModel);
        String actualOutput = expectedModel.getFilteredFinancialRecordList().toString();

        assertEquals(MESSAGE_TYPICAL_BUDGET_TRACKER_DELETE_LUNCH, actualOutput);
    }

    @Test
    public void execute_validIndexFilteredList_success() throws CommandException {
        BudgetBabyModelManager expectedModel = new BudgetBabyModelManager(model.getBudgetTracker(), new UserPrefs());
        showFrAtIndex(expectedModel, new ArrayList<>(Arrays.asList(INDEX_FIRST_FR)));

        DeleteFrCommand deleteCommand = new DeleteFrCommand(new ArrayList<>(Arrays.asList(INDEX_FIRST_FR)));

        deleteCommand.execute(expectedModel);
        String actualOutput = expectedModel.getFilteredFinancialRecordList().toString();

        assertEquals(MESSAGE_TYPICAL_BUDGET_TRACKER_EMPTY, actualOutput);
    }

    @Test
    public void execute_validMultipleIndexFilteredList_throwsCommandException() throws CommandException {
        BudgetBabyModelManager expectedModel = new BudgetBabyModelManager(model.getBudgetTracker(), new UserPrefs());
        showFrAtIndex(expectedModel, new ArrayList<>(Arrays.asList(INDEX_FIRST_FR, INDEX_FOURTH_FR, INDEX_FIFTH_FR)));

        DeleteFrCommand deleteCommand = new DeleteFrCommand(new ArrayList<>(Arrays.asList(INDEX_THIRD_FR,
                INDEX_SECOND_FR, INDEX_FIRST_FR)));

        deleteCommand.execute(expectedModel);
        String actualOutput = expectedModel.getFilteredFinancialRecordList().toString();

        assertEquals(MESSAGE_TYPICAL_BUDGET_TRACKER_EMPTY, actualOutput);
    }

    @Test
    public void equals() {
        DeleteFrCommand deleteFirstCommand = new DeleteFrCommand(new ArrayList<>(Arrays.asList(INDEX_FIRST_FR)));
        DeleteFrCommand deleteSecondCommand = new DeleteFrCommand(new ArrayList<>(Arrays.asList(INDEX_SECOND_FR)));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteFrCommand deleteFirstCommandCopy = new DeleteFrCommand(new ArrayList<>(Arrays.asList(INDEX_FIRST_FR)));
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
    private void showNoPerson(BudgetBabyModel model) {
        model.updateFilteredFinancialRecordList(p -> false);
        assertTrue(model.getFilteredFinancialRecordList().isEmpty());
    }
}
