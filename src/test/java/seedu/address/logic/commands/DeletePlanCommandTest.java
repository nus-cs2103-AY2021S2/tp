
package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPlanAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PLAN;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PLAN;
import static seedu.address.testutil.TypicalPlans.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.plan.Plan;

public class DeletePlanCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Plan planToDelete = model.getFilteredPlanList().get(INDEX_FIRST_PLAN.getZeroBased());
        DeletePlanCommand deleteCommand = new DeletePlanCommand(INDEX_FIRST_PLAN);

        String expectedMessage = String.format(DeletePlanCommand.MESSAGE_DELETE_PLAN_SUCCESS,
                INDEX_FIRST_PLAN.getOneBased() + planToDelete.toString());

        ModelManager expectedModel = new ModelManager(model.getPlans(), new UserPrefs());
        expectedModel.deletePlan(planToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPlanList().size() + 1);
        DeletePlanCommand deleteCommand = new DeletePlanCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PLAN_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPlanAtIndex(model, INDEX_FIRST_PLAN);

        Plan planToDelete = model.getFilteredPlanList().get(INDEX_FIRST_PLAN.getZeroBased());
        DeletePlanCommand deleteCommand = new DeletePlanCommand(INDEX_FIRST_PLAN);

        String expectedMessage = String.format(DeletePlanCommand.MESSAGE_DELETE_PLAN_SUCCESS,
                INDEX_FIRST_PLAN.getOneBased() + planToDelete.toString());

        Model expectedModel = new ModelManager(model.getPlans(), new UserPrefs());
        expectedModel.deletePlan(planToDelete);
        showNoPlan(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPlanAtIndex(model, INDEX_FIRST_PLAN);

        Index outOfBoundIndex = INDEX_SECOND_PLAN;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPlans().getPersonList().size());

        DeletePlanCommand deleteCommand = new DeletePlanCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PLAN_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeletePlanCommand deleteFirstCommand = new DeletePlanCommand(INDEX_FIRST_PLAN);
        DeletePlanCommand deleteSecondCommand = new DeletePlanCommand(INDEX_SECOND_PLAN);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeletePlanCommand deleteFirstCommandCopy = new DeletePlanCommand(INDEX_FIRST_PLAN);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPlan(Model model) {
        model.updateFilteredPlanList(p -> false);

        assertTrue(model.getFilteredPlanList().isEmpty());
    }
}
